package com.example.mobiletest.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditBottomSheet (
    treeViewModel: TreeViewModel
) {

    ModalBottomSheet(
        onDismissRequest = { treeViewModel.showEditBottomSheet.value = false },
        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            Text(
                text = "Edit equipment name",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.size(20.dp))

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        drawLine(
                            Color(0xFF575757),
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = 2.dp.toPx()
                        )
                    },
                value = treeViewModel.equipmentName.value ,
                onValueChange = { treeViewModel.equipmentName.value = it },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        tint = Color(0xFFFF325F),
                        contentDescription = null
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
                )
            )

            Spacer(modifier = Modifier.size(32.dp))

            Button(
                onClick = {},
                shape = RoundedCornerShape(40.dp),
                modifier = Modifier
                    .size(
                        width = 320.dp,
                        height = 56.dp
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF325F),
                    disabledContainerColor = Color(0xFFFF325F),
                    disabledContentColor = Color(0xFFFF325F)
                )
            ) {
                Text(
                    text = "Confirm",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
                    )
                )
            }

            Spacer(modifier = Modifier.size(8.dp))

            Button(
                onClick = { treeViewModel.showEditBottomSheet.value = false },
                shape = RoundedCornerShape(40.dp),
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .size(
                        width = 320.dp,
                        height = 56.dp
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF0F0F0),
                    disabledContainerColor = Color(0xFFF0F0F0),
                    disabledContentColor = Color(0xFFF0F0F0)
                )
            ) {
                Text(
                    text = "Cancel",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                        color = Color(0xFFFF325F)
                    )
                )
            }
        }
    }
}