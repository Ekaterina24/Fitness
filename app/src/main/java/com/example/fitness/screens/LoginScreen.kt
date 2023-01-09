package com.example.fitness.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fitness.MainViewModel
import com.example.fitness.navigation.NavRoute
import com.example.fitness.utils.EMAIL
import com.example.fitness.utils.PASSWORD

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 32.dp)
        ) {
            Text(
                text = "Please Login",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                label = { Text(text = "Email") },
                isError = email.isEmpty()
            )
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = { Text(text = "Password") },
                isError = password.isEmpty()
            )
            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = {
                    EMAIL = email
                    PASSWORD = password
                    viewModel.connectToLoginUser {
                        when(EMAIL) {
                            "admin@gmail.com" -> navController.navigate(route = NavRoute.AdminChoose.route)
                            else -> navController.navigate(route = NavRoute.UserChoose.route)
                        }

                    }
                },
                enabled = email.isNotEmpty() && password.isNotEmpty()
            ) {
                Text(text = "Login")
            }
            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = {
                    navController.navigate(route = NavRoute.Register.route)
                }
            ) {
                Text(text = "Create new account")
            }
        }
    }
}
