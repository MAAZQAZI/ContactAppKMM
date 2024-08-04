import contact.data.SqlDelightContactDataSource
import contact.domain.ContactDataSource
import core.data.DatabaseDriverFactory
import org.maaz.ContactsDatabase

actual class Appmodule {
    actual val contactDataSource: ContactDataSource by lazy {
        SqlDelightContactDataSource(
            db = ContactsDatabase(
                driver = DatabaseDriverFactory().createDriver()
            )
        )
    }

}

