package com.example.fitness.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fitness.MainViewModel
import com.example.fitness.model.FeedbackModel
import com.example.fitness.navigation.NavRoute
import com.example.fitness.utils.EMAIL

@Composable
fun FeedbackListScreen(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    val feedback = viewModel.getAllFeedback().observeAsState(listOf()).value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NavRoute.AddFeedback.route)
                }
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Icons", tint = Color.White)
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 32.dp)
        ) {
            if (EMAIL == "admin@gmail.com") {
                Button(
                    modifier = Modifier
                        .padding(top = 16.dp),
                    onClick = {
                        navController.navigate(NavRoute.AdminChoose.route)
                    }
                ) {
                    Text(text = "На главную")
                }
            } else {
                Button(
                    modifier = Modifier
                        .padding(top = 16.dp),
                    onClick = {
                        navController.navigate(NavRoute.UserChoose.route)
                    }
                ) {
                    Text(text = "На главную")
                }
            }

            Text(
                text = "Список отзывов",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            LazyColumn{
                items(feedback) { item ->
                    FeedbackItem(feedback = item)
                }
            }
        }

    }
}

@Composable
fun FeedbackItem(feedback: FeedbackModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp),
        elevation = 6.dp
    ) {
        Column(modifier = Modifier.padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = feedback.user,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = feedback.text,
                fontSize = 18.sp
            )
            Text(text = feedback.trainTitle,
                fontSize = 16.sp
            )
        }
    }
}