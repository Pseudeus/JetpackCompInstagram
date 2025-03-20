package com.example.jetpackcompinstagram.login.ui

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.jetpackcompinstagram.R
import com.example.jetpackcompinstagram.ui.theme.AuxiliaryText
import com.example.jetpackcompinstagram.ui.theme.PrimaryButton
import com.example.jetpackcompinstagram.ui.theme.TextFieldBg

@Composable
fun MyInstagramScreen(loginViewModel: LoginViewModel, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Header(Modifier.align(Alignment.TopEnd))
        Body(loginViewModel, Modifier.align(Alignment.Center))
        Footer(Modifier.align(Alignment.BottomCenter)) {  }
    }
}

@Composable
fun Footer(modifier: Modifier, onClick: () -> Unit) {
    Column(modifier.fillMaxWidth()) {
        HorizontalDivider(color = Color.LightGray)
        Spacer(Modifier.height(32.dp))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Don't have an account?",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Light,
                color = Color.Gray
            )
            AuxiliaryClickableText("Sign up.", Modifier.padding(start = 4.dp)) { onClick() }
        }
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
fun Body(loginViewModel: LoginViewModel, modifier: Modifier) {
    val email: String by loginViewModel.email.observeAsState(initial = "")
    val passwd: String by loginViewModel.password.observeAsState(initial = "")
    val isLoginEnabled: Boolean by loginViewModel.isLoginEnabled.observeAsState(initial = false)

    Column(modifier = modifier.padding(32.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.insta),
            contentDescription = "instaLogo",
            modifier = modifier.width(240.dp),
            contentScale = ContentScale.FillWidth
        )
        Spacer(Modifier.height(16.dp))
        EmailTextField(email) { loginViewModel.onLoginChanged(email = it, passwd = passwd) }
        Spacer(Modifier.height(16.dp))
        PasswordTextField(passwd) { loginViewModel.onLoginChanged(email = email, passwd = it) }
        Spacer(Modifier.height(16.dp))
        AuxiliaryClickableText("Forgot password?", Modifier.align(Alignment.End)) { }
        Spacer(Modifier.height(16.dp))
        LoginButton("Log In", isLoginEnabled) { }
        Spacer(Modifier.height(24.dp))
        SeparatorWai()
        Spacer(Modifier.height(24.dp))
        AlternativeLogin("Aris") { }
    }
}

@Composable
fun AlternativeLogin(logAs: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.fb),
            contentDescription = "fb",
            modifier = Modifier.size(20.dp)
        )
        Text(
            "Continue as $logAs",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = AuxiliaryText,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
fun SeparatorWai() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        HorizontalDivider(Modifier.weight(1f), color = Color.LightGray)
        Text(
            "OR",
            modifier = Modifier.padding(horizontal = 8.dp),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
        )
        HorizontalDivider(Modifier.weight(1f), color = Color.LightGray)
    }
}

@Composable
fun LoginButton(text: String, isEnabled: Boolean, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = PrimaryButton),
        shape = MaterialTheme.shapes.small,
        enabled = isEnabled
    ) {
        Text(
            text,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}

@Composable
fun AuxiliaryClickableText(text: String, modifier: Modifier, onClick: () -> Unit) {
    Text(
        text,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold,
        color = AuxiliaryText,
        modifier = modifier.clickable {
            onClick()
        })
}

@Composable
fun PasswordTextField(passwd: String, onTextChanged: (String) -> Unit) {
    var hideText by remember { mutableStateOf(true) }

    TextField(
        value = passwd,
        onValueChange = { onTextChanged(it) },
        placeholder = { Text("Password", color = Color.Gray) },
        singleLine = true,
        shape = MaterialTheme.shapes.medium,
        trailingIcon = {
            if (hideText) {
                Icon(
                    imageVector = Icons.Filled.Visibility,
                    contentDescription = "show",
                    tint = Color.Gray,
                    modifier = Modifier.clickable {
                        hideText = !hideText
                    }
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.VisibilityOff,
                    contentDescription = "hide",
                    tint = Color.Gray,
                    modifier = Modifier.clickable {
                        hideText = !hideText
                    })
            }
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = TextFieldBg,
            focusedContainerColor = TextFieldBg,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = Color(0xFFB2B2B2),
            unfocusedTextColor = Color(0xFF2B2B2B)
        ),
        visualTransformation = if (hideText) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun EmailTextField(user: String, onTextChanged: (String) -> Unit) {
    TextField(
        value = user,
        onValueChange = { onTextChanged(it) },
        placeholder = { Text("Email", color = Color.Gray) },
        singleLine = true,
        shape = MaterialTheme.shapes.medium,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = TextFieldBg,
            focusedContainerColor = TextFieldBg,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = Color(0xFFB2B2B2),
            unfocusedTextColor = Color(0xFF2B2B2B)
        ),
        modifier = Modifier.fillMaxWidth()
    )
}


@Composable
fun Header(modifier: Modifier) {
    val localContext = LocalActivity.current as Activity

    IconButton(
        onClick = { localContext.finish() },
        colors = IconButtonDefaults.iconButtonColors(contentColor = Color.Gray),
        modifier = modifier.padding(8.dp)
    ) {
        Icon(imageVector = Icons.Default.Close, contentDescription = "close")
    }
}