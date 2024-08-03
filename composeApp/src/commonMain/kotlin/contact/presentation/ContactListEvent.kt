package com.plcoding.contactscomposemultiplatform.contacts.presentation

import Contact


sealed interface ContactListEvent {
    object OnAddNewContactClick : ContactListEvent
    object ContactSheetDismissed : ContactListEvent
    data class OnFirstNameChange(val firstName: String) : ContactListEvent
    data class OnLastNameChange(val lastName: String) : ContactListEvent
    data class OnEmailChange(val email: String) : ContactListEvent
    data class OnPhoneNumberChange(val phoneNumber: String) : ContactListEvent
    class OnPhotoPicked(val photo: ByteArray) : ContactListEvent
    object OnAddPhotoClick: ContactListEvent
    object OnSaveClick: ContactListEvent
    data class SelectContact(val contact: Contact) : ContactListEvent
    data class EditContact(val contact: Contact) : ContactListEvent
    object OnDeleteContactClick : ContactListEvent

}