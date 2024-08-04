package core.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.maaz.ContactsDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
       return NativeSqliteDriver(ContactsDatabase.Schema, "contact.db")
    }

}