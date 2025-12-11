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
import com.example.mobiletest.ui.view.TreeScreen
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

                    // LOGIN
                    composable("login") {
                        LoginScreen(
                            onLoginClick = { token ->
                                navController.navigate("home/$token") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        )
                    }

                    composable("home/{token}") { backStackEntry ->
                        val token = backStackEntry.arguments?.getString("token") ?: ""
                        TreeScreen(
                            navController = navController,
                            token = token
                        )
                    }


                    composable("edit/{id}") { backStackEntry ->
                        val id = backStackEntry.arguments?.getString("id")
                        val vm: EditViewModel = viewModel()

                        EditScreen(
                            viewModel = vm,
                            onSave = {
                                navController.popBackStack()
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
