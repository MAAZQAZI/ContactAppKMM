package core.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.maaz.ContactsDatabase

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun createDriver(): SqlDriver {
       return AndroidSqliteDriver(ContactsDatabase.Schema, context, "contact.db")
    }

}