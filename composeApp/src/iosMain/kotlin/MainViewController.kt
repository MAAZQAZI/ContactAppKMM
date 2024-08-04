import androidx.compose.ui.interop.LocalUIViewController
import androidx.compose.ui.window.ComposeUIViewController
import core.presentation.ImagePickerFactory
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

fun MainViewController() = ComposeUIViewController { val isDarkTheme=
    UIScreen.mainScreen.traitCollection.userInterfaceStyle== UIUserInterfaceStyle.UIUserInterfaceStyleDark
    App(
        darkTheme = isDarkTheme,
        dynamicColor = true,
        appmodule = Appmodule(),
        imagePicker = ImagePickerFactory(LocalUIViewController.current).createPicker()
    ) }