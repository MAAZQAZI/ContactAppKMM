import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import contact.presentation.ContactListScreen
import contact.presentation.ContactListViewModel

import core.presentation.ImagePicker
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

@Composable
fun App(

    darkTheme: Boolean,
    dynamicColor: Boolean,
    appmodule: Appmodule,
    imagePicker: ImagePicker
) {
//    ContactsTheme(
//        darkTheme = darkTheme,
//        dynamicColor = dynamicColor
//    ) {
        val viewModel= getViewModel(
            key = "contact-list-screen",
            factory = viewModelFactory {
                ContactListViewModel(appmodule.contactDataSource)
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
                onEvent = viewModel::onEvent,
                imagePicker = imagePicker
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