import android.content.Context
import contact.data.SqlDelightContactDataSource
import contact.domain.ContactDataSource
import core.data.DatabaseDriverFactory
import org.maaz.ContactsDatabase

actual class Appmodule(
    private val context: Context
) {
    actual val contactDataSource: ContactDataSource by lazy {
        SqlDelightContactDataSource(
            db= ContactsDatabase(
                driver = DatabaseDriverFactory(context).createDriver()
            )
        )
    }

}