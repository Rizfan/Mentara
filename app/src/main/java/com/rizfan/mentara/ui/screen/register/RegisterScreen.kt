package com.rizfan.mentara.ui.screen.register

import android.widget.Toast
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rizfan.mentara.R
import com.rizfan.mentara.ui.common.AuthState
import com.rizfan.mentara.ui.components.LoadingIndicator
import com.rizfan.mentara.ui.components.MainButton
import com.rizfan.mentara.ui.theme.MentaraTheme

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = hiltViewModel(),
    navigateToLogin : @Composable (() -> Unit),
    onLoginClick: () -> Unit = {}
) {
    var name by rememberSaveable (stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var noTelp by rememberSaveable (stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var email by rememberSaveable (stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var password by rememberSaveable (stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }

    val context = LocalContext.current

    RegisterContent(
        modifier = modifier,
        nameText = name,
        noTelpText = noTelp,
        emailText = email,
        passwordText = password,
        onNameChange = { name = it },
        onNoTelpChange = { noTelp = it },
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        visiblePassword = passwordHidden,
        onVisiblePasswordChange = { passwordHidden = !passwordHidden },
        navigateToLogin = onLoginClick,
        onRegisterClick = { email, noTelp, name, password ->
            viewModel.register(name, noTelp, email, password)
        }
    )

    viewModel.uiState.collectAsState(initial = AuthState.Unauthorized).value.let { uiState ->
        when (uiState) {
            is AuthState.Loading -> {
                LoadingIndicator()
            }
            is AuthState.Success -> {
//                Toast.makeText(context, uiState.data.message, Toast.LENGTH_SHORT).show()
                navigateToLogin()
            }
            is AuthState.Error -> {
                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
            }
            is AuthState.Unauthorized -> {

            }
        }
    }




}

@Composable
fun RegisterContent(
    modifier: Modifier = Modifier,
    nameText: TextFieldValue = TextFieldValue(""),
    noTelpText: TextFieldValue = TextFieldValue(""),
    emailText: TextFieldValue = TextFieldValue(""),
    passwordText: TextFieldValue = TextFieldValue(""),
    onNameChange: (TextFieldValue) -> Unit = {},
    onNoTelpChange: (TextFieldValue) -> Unit = {},
    onEmailChange: (TextFieldValue) -> Unit = {},
    onPasswordChange: (TextFieldValue) -> Unit = {},
    visiblePassword: Boolean = false,
    onVisiblePasswordChange: () -> Unit,
    navigateToLogin : () -> Unit,
    onRegisterClick : (email : String, noTelp : String, name : String, password : String) -> Unit
) {

    Box(
        modifier = modifier.padding(horizontal = 16.dp),
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.Center,
        ) {

            Text(
                text = stringResource(R.string.sign_up),
                // Heading/H3
                style = TextStyle(
                    fontSize = 32.sp,
                    lineHeight = 40.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF0F172A),
                ),
                modifier = modifier.padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = nameText,
                onValueChange = onNameChange,
                label = { Text("Name") },
                placeholder = { Text("Robert...") },
                maxLines = 1,
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

            OutlinedTextField(
                value = noTelpText,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                onValueChange = onNoTelpChange,
                label = { Text("Phone Number") },
                maxLines = 1,
                placeholder = { Text("021....") },
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

            OutlinedTextField(
                value = emailText,
                onValueChange = onEmailChange,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                ),
                label = { Text(stringResource(R.string.email)) },
                placeholder = { Text(stringResource(R.string.example_gmail_com)) },
                maxLines = 1,
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

            OutlinedTextField(
                value = passwordText,
                onValueChange = onPasswordChange,
                singleLine = true,
                label = { Text("Enter password") },
                maxLines = 1,
                visualTransformation =
                if (visiblePassword) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Lock,
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    IconButton(onVisiblePasswordChange) {
                        val visibilityIcon =
                            if (visiblePassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        // Please provide localized description for accessibility services
                        val description = if (visiblePassword) "Show password" else "Hide password"
                        Icon(imageVector = visibilityIcon, contentDescription = description)
                    }
                },
                shape = RoundedCornerShape(10.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp)
            )

            MainButton(
                text = stringResource(R.string.sign_up),
                modifier = modifier
                    .padding(top = 16.dp, bottom = 16.dp),
                onClick = { onRegisterClick(emailText.text, noTelpText.text, nameText.text, passwordText.text) }
            )


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
                TextButton(onClick = { navigateToLogin() }) {
                    Text(
                        text = stringResource(R.string.sign_in),
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
        RegisterScreen(
            navigateToLogin = {}
        )
    }
}