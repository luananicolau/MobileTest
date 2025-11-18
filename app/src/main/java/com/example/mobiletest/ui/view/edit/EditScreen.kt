package com.example.mobiletest.ui.view.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

@Composable
fun EditScreen(onCloseClick: () -> Unit) {
    val pinkBackground = Color(0xFFFF4081)
    var text by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray.copy(alpha = 0.3f)),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(Color.White)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Edit equipment name",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(24.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "editar equip",
                    tint = Color(color = 0xFFFF4081),
                    modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
                )

                TextField(
                    value = text,
                    onValueChange = { text = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray.copy(alpha = 0.1f), RoundedCornerShape(12.dp)),
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                    )
                )
            }


            Spacer(Modifier.height(32.dp))

            Button(
                onClick = {
                    onCloseClick() // fecha a tela após a confirmacao
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = pinkBackground),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text("Confirm", style = MaterialTheme.typography.titleMedium.copy(color = Color.White))
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = {
                    onCloseClick() // fecha a tela no cancelamento
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = pinkBackground)
            ) {
                Text("Cancel", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}