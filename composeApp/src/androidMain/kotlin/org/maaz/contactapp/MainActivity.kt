package org.maaz.contactapp

import App
import Appmodule
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import core.presentation.ImagePickerFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(
                darkTheme = false,
                dynamicColor = true,
                appmodule = Appmodule(LocalContext.current),
                imagePicker = ImagePickerFactory().createPicker()
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    App(
        darkTheme = false,
        dynamicColor = true,
        appmodule = Appmodule(LocalContext.current),
        imagePicker = ImagePickerFactory().createPicker()
    )
}
