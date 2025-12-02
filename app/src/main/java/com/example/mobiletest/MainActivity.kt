package com.example.mobiletest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobiletest.ui.view.login.LoginScreen
import com.example.mobiletest.ui.view.edit.EditScreen
import com.example.mobiletest.ui.view.edit.EditViewModel
import com.example.mobiletest.ui.view.home.HomeViewModel
import com.example.mobiletest.ui.view.home.TreeScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "login"
                ) {

                    composable("login") {
                        LoginScreen(
                            onLoginClick = {
                                navController.navigate("home") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        )
                    }

                    composable("home") {
                        TreeScreen(
                            navController = navController
                        )
                    }

                    composable("edit") {
                        val vm: EditViewModel = viewModel()

                        EditScreen(
                            viewModel = vm,
                            onSave = {
                                navController.navigate("home")
                            },
                            onCloseClick = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}
