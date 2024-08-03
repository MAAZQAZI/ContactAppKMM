package contact

import Contact
import org.maaz.sqldelight.hockey.data.ContactEntity

fun ContactEntity.toContact(): Contact {
    return Contact(
        id = id,
        firstname = firstName,
        lastname = lastName,
        phoneNumber = phoneNumber,
        email = email,
        photoByte = null // TODO: Get the image
    )
}