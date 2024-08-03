package com.plcoding.contactscomposemultiplatform.contacts.presentation

import Contact
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ContactListViewModel :ViewModel(){
    fun onEvent(contactListEvent: ContactListEvent) {

    }


    private val _state = MutableStateFlow(ContactListState(
        contacts=  contacts
    ))

    val state=_state.asStateFlow()


    var newContact: Contact? by mutableStateOf(null)
    private set

}

private val contacts= (1..50).map{
    Contact(
        id=it.toLong(),
        firstname="John",
        lastname="Doe",
        phoneNumber="1234567890",
        email="Test@gmail.com",
        photoByte = null,
    )
}
