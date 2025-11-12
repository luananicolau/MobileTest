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
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen() {
    val pinkBackground = Color(0xFFFF4081)
    val white = Color.White
    val lightGray = Color(0xFFF5F5F5)

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
    ) {
        //fundo rosa
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(
                    color = pinkBackground,
                    shape = RoundedCornerShape(bottomStart = 60.dp, bottomEnd = 60.dp)
                ),
            contentAlignment = Alignment.TopCenter
        ) {
            //  icone no fundo rosa em cima
            Box(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(color = white),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "User Icon",
                    tint = pinkBackground,
                    modifier = Modifier.size(48.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .padding(top = 240.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome",
                style = MaterialTheme.typography.headlineSmall,
                color = pinkBackground
            )

            Spacer(Modifier.height(50.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("User") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(lightGray, RoundedCornerShape(25.dp)),
                singleLine = true,
                shape = RoundedCornerShape(25.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedLabelColor = pinkBackground,
                    unfocusedLabelColor = pinkBackground.copy(alpha = 0.7f),
                )
            )

            Spacer(Modifier.height(25.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(lightGray, RoundedCornerShape(25.dp)),
                singleLine = true,
                shape = RoundedCornerShape(25.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedLabelColor = pinkBackground,
                    unfocusedLabelColor = pinkBackground.copy(alpha = 0.7f),
                )
            )

            Spacer(Modifier.height(100.dp))

            Button(
                onClick = { },
                modifier = Modifier
                    .width(350.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = pinkBackground),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text("LOGIN")
            }
        }
    }
}
