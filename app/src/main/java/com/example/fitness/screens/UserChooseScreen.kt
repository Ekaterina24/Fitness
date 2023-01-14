package com.example.fitness.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fitness.navigation.NavRoute

@Composable
fun UserChooseScreen(
    navController: NavHostController
) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 32.dp)
        ) {
            Button(
                modifier = Modifier
                    .padding(top = 16.dp),
                onClick = {
                    navController.navigate(NavRoute.Login.route)
                }
            ) {
                Text(text = "Назад")
            }
            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = {
                    navController.navigate(route = NavRoute.ListTrain.route)
                }
            ) {
                Text(text = "Показать расписание")
            }
            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = {
                    navController.navigate(route = NavRoute.FeedbackList.route)
                }
            ) {
                Text(text = "Отзывы")
            }
        }
    }
}