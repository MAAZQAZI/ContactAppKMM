package contact.domain

import Contact

object ContactValidator {

    fun validateContact(contact: Contact) : ValidateResult {

        var result = ValidateResult()
        if (contact.firstname.isBlank()) {
            result = result.copy(firstNameError = "First name cannot be empty")
        }
        if (contact.lastname.isBlank()) {
            result = result.copy(lastNameError = "Last name cannot be empty")
        }
        if (contact.email.isBlank()) {
            result = result.copy(emailError = "Email cannot be empty")
        }
        if (contact.phoneNumber.isBlank()) {
            result = result.copy(phoneNumberError = "Phone number cannot be empty")
        }
        return result
    }

    data class ValidateResult(
        val firstNameError: String? =null,
        val lastNameError: String? = null,
        val emailError: String? = null,
        val phoneNumberError: String? = null

    )
}