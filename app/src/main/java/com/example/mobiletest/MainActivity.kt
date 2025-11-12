package com.example.mobiletest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.example.mobiletest.ui.view.edit.EditScreen
import com.example.mobiletest.ui.view.home.HomeScreen
import com.example.mobiletest.ui.view.login.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                HomeScreen()
                LoginScreen()
                EditScreen()
            }
        }
    }
}