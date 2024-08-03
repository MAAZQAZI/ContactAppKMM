package com.plcoding.contactscomposemultiplatform.contacts.presentation.components

import Contact
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import core.presentation.rememberBitmapFromBytes

@Composable
fun ContactPhoto(
    contact: Contact?,
    modifier: Modifier = Modifier,
    iconSize: Dp = 25.dp
) {
    val bitmap = rememberBitmapFromBytes(contact?.photoByte)
    val photoModifier = modifier.clip(RoundedCornerShape(35))

    if (bitmap != null) {
        Image(
            bitmap = bitmap,
            contentDescription = contact?.firstname,
            modifier = photoModifier,
            contentScale = ContentScale.Crop
        )
    } else {
        Box(
            modifier = photoModifier.background(MaterialTheme.colors.primary),
            contentAlignment = Alignment.Center
        )
        {
            Icon(
                imageVector = Icons.Rounded.Person,
                contentDescription = contact?.firstname,
                modifier = Modifier.size(iconSize),
                tint = MaterialTheme.colors.surface
            )
        }
    }
}


