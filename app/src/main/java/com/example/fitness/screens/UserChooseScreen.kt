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
import com.example.fitness.MainViewModel
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
                    .padding(top = 16.dp)
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                onClick = {
                    navController.navigate(NavRoute.Login.route)
                }
            ) {
                Text(text = "Back")
            }
            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = {
                    navController.navigate(route = NavRoute.AdminListTrain.route)
                }
            ) {
                Text(text = "Show Timetable")
            }
            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = {
                    navController.navigate(route = NavRoute.AdminCommentList.route)
                }
            ) {
                Text(text = "Comments")
            }
        }
    }
}