package com.plcoding.contactscomposemultiplatform.contacts.presentation

import Contact


data class ContactListState(
    val contacts: List<Contact> = emptyList(),
    val recentAddedContacts: List<Contact> = emptyList(),
    val isAddContactSheetOpen: Boolean = false,
    val isSelectedContactSheetOpen: Boolean = false,
    val firstNameError: String? = null,
    val lastNameError: String? = null,
    val emailError: String? = null,
    val phoneNumberError: String? = null,
)