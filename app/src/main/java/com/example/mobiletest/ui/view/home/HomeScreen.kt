package com.example.mobiletest.ui.view.home
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobiletest.ui.view.edit.EditViewModel

@Composable
fun HomeScreen(onEditClick: () -> Unit, viewModel: HomeViewModel =  HomeViewModel(), ) {
    val pinkBackground = Color(0xFFFF4081)
    val horizontalPadding = 32.dp

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

        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Spacer(Modifier.height(40.dp))

            Text(
                text = "Hello",
                modifier = Modifier.fillMaxWidth().padding(horizontal = horizontalPadding),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.White,
                    fontSize = 28.sp
                )
            )
            Spacer(Modifier.height(10.dp))

            Text(
                text = "Username",
                modifier = Modifier.fillMaxWidth().padding(horizontal = horizontalPadding),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.White,
                    fontSize = 28.sp
                )
            )

            Spacer(Modifier.height(70.dp))
            Spacer(Modifier.height(70.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = horizontalPadding)
                    .clickable { onEditClick() }
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "motor",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color.Black,
                        fontSize = 28.sp
                    )
                )
                Spacer(Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar motor",
                    tint = Color.Gray,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}