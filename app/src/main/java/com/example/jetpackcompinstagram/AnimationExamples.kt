package com.example.jetpackcompinstagram

import android.graphics.drawable.Icon
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.EaseInElastic
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.EaseInOutBounce
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.EaseOutBounce
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SensorDoor
import androidx.compose.material.icons.filled.SensorsOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.w3c.dom.Text
import kotlin.random.Random.Default.nextInt

@Composable
fun ColorAnimationSimple(modifier: Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        var firstColor by rememberSaveable { mutableStateOf(false) }
        var showBox by rememberSaveable { mutableStateOf(true) }

        val realColor by animateColorAsState(
            targetValue = if (firstColor) Color.Red else Color.Yellow,
            animationSpec = tween(2_000, easing = EaseOutBounce),
            finishedListener = { showBox = false })


        if (showBox) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(realColor)
                    .clickable {
                        firstColor = !firstColor
                    })
        }
    }
}

@Composable
fun SizeAnimation(modifier: Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        val realColor = Color.Cyan
        var smallSize by rememberSaveable { mutableStateOf(true) }
        val size by animateDpAsState(
            if (smallSize) 50.dp else 100.dp,
            animationSpec = tween(durationMillis = 500, easing = EaseOutBounce),
            finishedListener = {
                if (!smallSize) {
                }
            })

        Box(
            modifier = Modifier
                .size(size)
                .background(realColor)
                .clickable {
                    smallSize = !smallSize
                })
    }
}

@Composable
fun VisibilityAnimation(modifier: Modifier) {
    var isVisible by remember { mutableStateOf(true) }

    Column(modifier = modifier.fillMaxSize()) {
        Button(onClick = { isVisible = !isVisible }) {
            Text("Mostrar/Ocultar")
        }
        Spacer(Modifier.height(50.dp))

        AnimatedVisibility(
            isVisible,
            enter = slideInHorizontally() + fadeIn(),
            exit = slideOutHorizontally() + fadeOut()
        ) {
            Box(Modifier
                .size(150.dp)
                .background(Color.Red))
        }
    }
}

@Composable
fun CrossfadeExampleAnimation(modifier: Modifier) {
    var myComponentType: ComponentType by remember { mutableStateOf(ComponentType.Text) }

    Column(modifier = modifier.fillMaxSize()) {
        Button(onClick = { myComponentType = getComponentTypeRandom() }) {
            Text("Cambiar componente")
        }
        Crossfade(targetState = myComponentType) {
            when (it) {
                ComponentType.Image -> Icon(Icons.Default.SensorDoor, contentDescription = "")
                ComponentType.Text -> Text("SOY UN COMPONENTE")
                ComponentType.Box -> Box(Modifier
                    .size(150.dp)
                    .background(Color.Red))
                ComponentType.Error -> Text("ERRORR")
            }
        }
    }
}

fun getComponentTypeRandom(): ComponentType {
    return ComponentType.entries[nextInt(from = 0, until = ComponentType.entries.size - 2)]
}

enum class ComponentType() {
    Image, Text, Box, Error
}