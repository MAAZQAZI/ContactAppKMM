package core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

expect class ImagePickerFactory {

    @Composable
    fun createPicker(): ImagePicker

}