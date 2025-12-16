package com.example.mobiletest.ui.view.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mobiletest.ui.view.LoginViewModel
import com.example.mobiletest.states.LoginState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel(),
    onLoginClick: (String, String) -> Unit

) {

    val pink = Color(0xFFFF3E73)
    val lightGray = Color(0xFFF2F2F2)
    val white = Color.White

    var loginErrorMessage by remember { mutableStateOf<String?>(null) }


    var showUsernameError by remember { mutableStateOf(false) }
    var showPasswordError by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }


    val username = loginViewModel.username.collectAsState()
    val password = loginViewModel.password.collectAsState()
    val state = loginViewModel.state.collectAsState()

    // quando login der certo aí navega
    LaunchedEffect(state.value) {
        when (state.value) {
            is LoginState.Success<*> -> {
                val token = (state.value as LoginState.Success<String>).data
                onLoginClick(token, username.value)
                loginViewModel.updateState(LoginState.Idle)
            }

            is LoginState.Error -> {
                val message = (state.value as LoginState.Error).message
                loginErrorMessage = message
                loginViewModel.updateState(LoginState.Idle)
            }

            else -> {}
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(pink)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(185.dp)
                .background(pink)
        ) {}

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 230.dp)
                .background(
                    color = white,
                    shape = RoundedCornerShape(topStart = 120.dp)
                )
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(
                            topStart = 50.dp,
                            topEnd = 10.dp,
                            bottomStart = 10.dp,
                            bottomEnd = 50.dp
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "User Icon",
                    tint = pink,
                    modifier = Modifier.size(65.dp)
                )
            }

            Spacer(modifier = Modifier.height(130.dp))

            Text(
                text = "Welcome",
                color = pink,
                style = TextStyle(
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(50.dp))

            Column(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "User",
                    color = pink,
                    modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
                )

                TextField(
                    value = username.value,
                    onValueChange = { loginViewModel.setUsernameValue(it) },
                    singleLine = true,
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .background(lightGray, RoundedCornerShape(30.dp)),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    )
                )
                if (showUsernameError) {
                    Text(
                        modifier = Modifier.padding(start = 40.dp, top = 4.dp),
                        text = "Insira o nome de usuário antes de efetuar o login",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFFFF325F)
                        )
                    )
                }

            }

            Spacer(modifier = Modifier.height(25.dp))

            Column(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Password",
                    color = pink,
                    modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
                )

                TextField(
                    value = password.value,
                    onValueChange = { loginViewModel.setPasswordValue(it) },
                    singleLine = true,
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .background(lightGray, RoundedCornerShape(30.dp)),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    ),
                    visualTransformation = if (passwordVisible)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),

                    trailingIcon = {
                        val icon = if (passwordVisible) {
                            Icons.Default.Visibility
                        } else {
                            Icons.Default.VisibilityOff
                        }

                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = icon,
                                contentDescription = if (passwordVisible) "Esconder senha" else "Mostrar senha"
                            )
                        }
                    }
                )

                if (showPasswordError) {
                    Text(
                        modifier = Modifier.padding(start = 40.dp, top = 4.dp),
                        text = "Insira a senha antes de efetuar o login",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFFFF325F)
                        )
                    )
                }

            }

            Spacer(modifier = Modifier.height(60.dp))

            Button(
                onClick = {
                    val usernameEmpty = username.value.isEmpty()
                    val passwordEmpty = password.value.isEmpty()

                    showUsernameError = usernameEmpty
                    showPasswordError = passwordEmpty

                    if (!usernameEmpty && !passwordEmpty) {
                        loginViewModel.getToken()
                    }
                },
                modifier = Modifier
                    .width(300.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = pink),
                shape = RoundedCornerShape(30.dp)
            ) {
                if (state.value == LoginState.Loading) {
                    CircularProgressIndicator(color = white)
                } else {
                    Text("LOGIN", color = white)
                }
            }

            if (loginErrorMessage != null) {
                Text(
                    text = loginErrorMessage!!,
                    color = Color(0xFFFF325F),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }

        }
    }
}



