package contact.presentation.components

import Contact
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.plcoding.contactscomposemultiplatform.contacts.presentation.ContactListEvent
import com.plcoding.contactscomposemultiplatform.contacts.presentation.ContactListState
import com.plcoding.contactscomposemultiplatform.contacts.presentation.components.ContactPhoto
import core.presentation.BottomSheetFromWish

@Composable
fun AddContactSheet(
    state: ContactListState,
    newContact: Contact?,
    isOpen: Boolean,
    onEvent: (ContactListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    BottomSheetFromWish(
        visible = isOpen,
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(60.dp))
                if(newContact?.photoByte == null) {
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(40))
                            .background(MaterialTheme.colors.primary)
                            .clickable {
                                onEvent(ContactListEvent.OnAddPhotoClick)
                            }
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colors.onSecondary,
                                shape = RoundedCornerShape(40)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = "Add photo",
                            tint = MaterialTheme.colors.onSecondary,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                } else {
                    ContactPhoto(
                        contact = newContact,
                        modifier = Modifier
                            .size(150.dp)
                            .clickable {
                                onEvent(ContactListEvent.OnAddPhotoClick)
                            }
                    )
                }
                Spacer(Modifier.height(16.dp))
                ContactTextField(
                    value = newContact?.firstname ?: "",
                    placeholder = "First name",
                    error = state.firstNameError,
                    onValueChanged = {
                        onEvent(ContactListEvent.OnFirstNameChange(it))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                ContactTextField(
                    value = newContact?.lastname ?: "",
                    placeholder = "Last name",
                    error = state.lastNameError,
                    onValueChanged = {
                        onEvent(ContactListEvent.OnLastNameChange(it))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                ContactTextField(
                    value = newContact?.email ?: "",
                    placeholder = "Email",
                    error = state.emailError,
                    onValueChanged = {
                        onEvent(ContactListEvent.OnEmailChange(it))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                ContactTextField(
                    value = newContact?.phoneNumber ?: "",
                    placeholder = "Phone number",
                    error = state.phoneNumberError,
                    onValueChanged = {
                        onEvent(ContactListEvent.OnPhoneNumberChange(it))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = {
                        onEvent(ContactListEvent.OnSaveClick)
                    }
                ) {
                    Text(text = "Save")
                }
            }
            IconButton(
                onClick = {
                    onEvent(ContactListEvent.ContactSheetDismissed)
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Close"
                )
            }
        }
    }
}

@Composable
private fun ContactTextField(
    value: String,
    placeholder: String,
    error: String?,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        OutlinedTextField(

            value = value,
            placeholder = {
                Text(text = placeholder)
            },
            onValueChange = onValueChanged,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = MaterialTheme.colors.primaryVariant)
        )
        if(error != null) {
            Text(
                text = error,
                color = MaterialTheme.colors.error
            )
        }
    }
}