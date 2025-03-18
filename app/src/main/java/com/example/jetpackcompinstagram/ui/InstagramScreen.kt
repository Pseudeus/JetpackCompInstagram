package com.example.jetpackcompinstagram.ui

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompinstagram.R

@Preview(showBackground = true)
@Composable
fun MyInstagramScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Header(Modifier.align(Alignment.TopEnd))
        Body(Modifier.align(Alignment.Center))
    }
}

@Composable
fun Body(modifier: Modifier) {
    Image(
        bitmap = ImageBitmap.imageResource(R.drawable.insta),
        contentDescription = "instaLogo",
        modifier = modifier.size(200.dp)
    )

}


@Composable
fun Header(modifier: Modifier) {
    val localContext = LocalActivity.current as Activity

    IconButton(
        onClick = { localContext.finish() },
        colors = IconButtonDefaults.iconButtonColors(contentColor = Color.LightGray),
        modifier = modifier.padding(8.dp)
    ) {
        Icon(imageVector = Icons.Default.Close, contentDescription = "close")
    }
}