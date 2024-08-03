package contact.data


import Contact
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList


import contact.domain.ContactDataSource
import contact.toContact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import kotlinx.datetime.Clock
import org.maaz.ContactsDatabase
import org.maaz.sqldelight.hockey.data.ContactEntity

class SqlDelightContactDataSource(
    db: ContactsDatabase? = null,

    ): ContactDataSource {
    private val contactQueries= db?.contactQueries


    override fun getContacts(): Flow<List<Contact>> {
        return contactQueries?.getContacts()?.asFlow()?.mapToList(context = Dispatchers.IO)
            ?.map { contactEntities ->
                contactEntities.map { contactEntities ->
                    contactEntities.toContact()
                }


            } ?: throw IllegalStateException("ContactQueries is null")
    }

    override fun getRecentContacts(amount: Int): Flow<List<Contact>> {
        return contactQueries?.getRecentContacts(amount.toLong())?.asFlow()?.mapToList(context = Dispatchers.IO)
            ?.map { contactEntities ->
                contactEntities.map { contactEntities ->
                    contactEntities.toContact()
                }


            } ?: throw IllegalStateException("ContactQueries is null")

    }

    override suspend fun insertContact(contact: Contact) {
        contactQueries?.insertContactEntity(
            id = contact.id,
            firstName = contact.firstname,
            lastName = contact.lastname,
            phoneNumber = contact.phoneNumber,
            email = contact.email,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            imagePath = null
        )
    }

    override suspend fun deleteContact(id: Long) {
        contactQueries?.deleteContact(id)
    }


}

