package contact.presentation

import Contact
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.plcoding.contactscomposemultiplatform.contacts.presentation.ContactListEvent
import com.plcoding.contactscomposemultiplatform.contacts.presentation.ContactListState
import contact.domain.ContactDataSource
import contact.domain.ContactValidator
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContactListViewModel(
    private val contactDataSource: ContactDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(ContactListState())

    val state = combine(
        _state,
        contactDataSource.getContacts(),
        contactDataSource.getRecentContacts(20)
    ) { state, contacts, recentContacts ->
        state.copy(
            contacts = contacts,
            recentAddedContacts = recentContacts
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), ContactListState())


    var newContact: Contact? by mutableStateOf(null)
        private set

    fun onEvent(event: ContactListEvent) {
        when (event) {
            ContactListEvent.OnDeleteContactClick -> {
                //delete contact
                viewModelScope.launch {
                    _state.value.selectedContact?.id?.let { id ->
                        _state.update {
                            it.copy(
                                isSelectedContactSheetOpen = false
                            )
                        }
                        contactDataSource.deleteContact(id)
                        delay(300L)
                        _state.update {
                            it.copy(
                                selectedContact = null
                            )
                        }
                    }
                }
            }

            ContactListEvent.ContactSheetDismissed -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            isSelectedContactSheetOpen = false,
                            isAddContactSheetOpen = false,
                            firstNameError = null,
                            lastNameError = null,
                            emailError = null,
                            phoneNumberError = null,

                        )
                    }
                    delay(300L)
                    newContact = null
                }
            }
            is ContactListEvent.EditContact -> {
                _state.update {
                    it.copy(
                        selectedContact = null,
                        isAddContactSheetOpen = true,
                        isSelectedContactSheetOpen = false
                    )

                }
                newContact=event.contact
            }
            ContactListEvent.OnAddNewContactClick -> {
                _state.update {
                    it.copy(
                        isAddContactSheetOpen = true,
                    )

                }
                newContact=Contact(
                    id = null,
                    firstname = "",
                    lastname = "",
                    phoneNumber = "",
                    email = "",
                    photoByte = null
                )
            }

            is ContactListEvent.OnEmailChange -> {
                newContact = newContact?.copy(email = event.email)
            }
            is ContactListEvent.OnFirstNameChange -> {
                newContact = newContact?.copy(firstname = event.firstName)
            }
            is ContactListEvent.OnLastNameChange ->{
                newContact = newContact?.copy(lastname = event.lastName)
            }
            is ContactListEvent.OnPhoneNumberChange -> {
                newContact = newContact?.copy(phoneNumber = event.phoneNumber)
            }
            is ContactListEvent.OnPhotoPicked -> {
                newContact = newContact?.copy(photoByte = event.photo)
            }
            ContactListEvent.OnSaveClick -> {
                newContact?.let { contact ->
                    val result = ContactValidator.validateContact(contact)
                    val errors = listOfNotNull(
                        result.firstNameError,
                        result.lastNameError,
                        result.emailError,
                        result.phoneNumberError
                    )

                    if(errors.isEmpty()) {
                        _state.update { it.copy(
                            isAddContactSheetOpen = false,
                            firstNameError = null,
                            lastNameError = null,
                            emailError = null,
                            phoneNumberError = null
                        ) }
                        viewModelScope.launch {
                            contactDataSource.insertContact(contact)
                            delay(300L) // Animation delay
                            newContact = null
                        }
                    } else {
                        _state.update { it.copy(
                            firstNameError = result.firstNameError,
                            lastNameError = result.lastNameError,
                            emailError = result.emailError,
                            phoneNumberError = result.phoneNumberError
                        ) }
                    }
                }


            }
            is ContactListEvent.SelectContact -> {
                _state.update {
                    it.copy(
                        selectedContact = event.contact,
                        isSelectedContactSheetOpen = true,
                    )
                }
            }
            else -> {
                // do nothing
            }
        }

    }


}

private val contacts = (1..50).map {
    Contact(
        id = it.toLong(),
        firstname = "John",
        lastname = "Doe",
        phoneNumber = "1234567890",
        email = "Test@gmail.com",
        photoByte = null,
    )
}
