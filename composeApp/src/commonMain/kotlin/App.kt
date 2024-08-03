import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.plcoding.contactscomposemultiplatform.contacts.presentation.ContactListScreen
import com.plcoding.contactscomposemultiplatform.contacts.presentation.ContactListViewModel

import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import contactapp.composeapp.generated.resources.Res
import contactapp.composeapp.generated.resources.compose_multiplatform
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

@Composable
fun App(

    darkTheme: Boolean,
    dynamicColor: Boolean,
) {
//    ContactsTheme(
//        darkTheme = darkTheme,
//        dynamicColor = dynamicColor
//    ) {
        val viewModel= getViewModel(
            key = "contact-list-screen",
            factory = viewModelFactory {
                ContactListViewModel()
            }
        )
       val state by viewModel.state.collectAsState()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.primary
        ) {
            ContactListScreen(
                state =state,
                newContact = viewModel.newContact,
                onEvent = viewModel::onEvent
            )
        }


//    Scaffold {
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            var showText by remember { mutableStateOf(true) }
//            Button(onClick = { showText = !showText }) {
//                Text("Toggle Text")
//            }
//            AnimatedVisibility(visible = showText) {
//                Text("Hello, World!")
//            }
//
//        }
//    }

}