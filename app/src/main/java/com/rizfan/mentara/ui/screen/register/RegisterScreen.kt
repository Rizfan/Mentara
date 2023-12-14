package com.rizfan.mentara.ui.screen.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rizfan.mentara.ui.components.MainButton
import com.rizfan.mentara.ui.theme.MentaraTheme

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.Center,
        ) {

            Text(
                text = "Sign Up",
                // Heading/H3
                style = TextStyle(
                    fontSize = 32.sp,
                    lineHeight = 40.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF0F172A),
                ),
                modifier = modifier.padding(bottom = 8.dp)
            )

            var name by rememberSaveable { mutableStateOf("") }

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                placeholder = { Text("Robert...") },
                shape = RoundedCornerShape(10.dp),
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Person,
                        contentDescription = null
                    )
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
            var noTelp by rememberSaveable { mutableStateOf("") }

            OutlinedTextField(
                value = noTelp,
                onValueChange = { noTelp = it },
                label = { Text("Phone Number") },
                placeholder = { Text("Robert...") },
                shape = RoundedCornerShape(10.dp),
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Phone,
                        contentDescription = null
                    )
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

            var email by rememberSaveable { mutableStateOf("") }

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                placeholder = { Text("example@gmail.com") },
                shape = RoundedCornerShape(10.dp),
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Email,
                        contentDescription = null
                    )
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

            var password by rememberSaveable { mutableStateOf("") }
            var passwordHidden by rememberSaveable { mutableStateOf(true) }
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                singleLine = true,
                label = { Text("Enter password") },
                visualTransformation =
                if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Lock,
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { passwordHidden = !passwordHidden }) {
                        val visibilityIcon =
                            if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        // Please provide localized description for accessibility services
                        val description = if (passwordHidden) "Show password" else "Hide password"
                        Icon(imageVector = visibilityIcon, contentDescription = description)
                    }
                },
                shape = RoundedCornerShape(10.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp)
            )

            MainButton(
                text = "Sign In",
                modifier = modifier
                    .padding(top = 16.dp, bottom = 16.dp)
            ) {

            }


        }
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Already have Account?",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 26.4.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF0F172A),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.4.sp,
                    )
                )
                TextButton(onClick = {}) {
                    Text(
                        text = "Sign in",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 26.4.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFFFB2528),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.4.sp,
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    MentaraTheme {
        RegisterScreen()
    }
}