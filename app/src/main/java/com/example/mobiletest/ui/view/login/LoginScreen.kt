package com.example.mobiletest.ui.view.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(onLoginClick: () -> Unit) {
    val pink = Color(0xFFFF3E73)
    val lightGray = Color(0xFFF2F2F2)
    val white = Color.White

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(pink)
    ) {

        // 🔄 ALTERADO: Box -> Column
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(185.dp)
                .background(pink)
        ) {
            // vazio mesmo, igual ao Box antes
        }

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
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF325F),
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily

            ))

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
                    value = username,
                    onValueChange = { username = it },
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
                    value = password,
                    onValueChange = { password = it },
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
            }

            Spacer(modifier = Modifier.height(60.dp))

            Button(
                onClick = { onLoginClick() },
                modifier = Modifier
                    .width(300.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = pink),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text("LOGIN", color = white)
            }
        }
    }
}
