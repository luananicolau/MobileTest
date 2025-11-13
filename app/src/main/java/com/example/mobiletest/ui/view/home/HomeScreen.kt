package com.example.mobiletest.ui.view.home
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    val pinkBackground = Color(0xFFFF4081)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    color = pinkBackground,
                    shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp)
                )
                .align(Alignment.TopCenter)

        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.height(40.dp))
            Text(
                text = "Hello",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.White,
                    fontSize = 28.sp
                )
            )
            Spacer(Modifier.height(30.dp))
            Text(
                text = "Username",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.White,
                    fontSize = 28.sp
                )
            )


        }
    }
}