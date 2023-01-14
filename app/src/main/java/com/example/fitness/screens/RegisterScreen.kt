package com.example.fitness.screens

import android.util.Log
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
import com.example.fitness.utils.NAME
import com.example.fitness.utils.PASSWORD

@Composable
fun RegisterScreen(navController: NavHostController,
                   viewModel: MainViewModel
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var cPassword by remember { mutableStateOf("") }

    Surface {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 32.dp)
        ) {
            Text(
                text = "Создать новый аккаунт",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                label = { Text(text = "Name") },
                isError = name.isEmpty()
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
            OutlinedTextField(
                value = cPassword,
                onValueChange = {
                    cPassword = it
                },
                label = { Text(text = "Confirm Password") },
                isError = cPassword.isEmpty()
            )
            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = {
                    EMAIL = email
                    NAME = name
                    PASSWORD = password
                    viewModel.connectToRegisterUser {
                        navController.navigate(route = NavRoute.Login.route)
                    }
                },
                enabled = name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && cPassword.isNotEmpty()
            ) {
                Text(text = "Зарегистрироваться")
            }
            Button(
                modifier = Modifier
                    .padding(top = 16.dp),
                onClick = {
                    navController.navigate(NavRoute.Login.route)
                }
            ) {
                Text(text = "Назад")
            }
        }
    }
}