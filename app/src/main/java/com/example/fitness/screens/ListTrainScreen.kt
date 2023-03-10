package com.example.fitness.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fitness.MainViewModel
import com.example.fitness.model.TrainModel
import com.example.fitness.navigation.NavRoute
import com.example.fitness.utils.EMAIL

@Composable
fun ListTrainScreen(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    val trains = viewModel.getAllTrains().observeAsState(listOf()).value

    Scaffold(
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 32.dp)
        ) {
            Button(
                onClick = {
                    navController.navigate(NavRoute.AddTrain.route)
                },
                modifier = Modifier
                    .defaultMinSize(minWidth = 56.dp, minHeight = 56.dp)
                    .alpha(if (EMAIL == "admin@gmail.com") 1f else 0f),

                shape = CircleShape

            ){
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Icons", tint = Color.White)
            }

            if (EMAIL == "admin@gmail.com") {
                Button(
                    modifier = Modifier
                        .padding(top = 16.dp),
                    onClick = {
                        navController.navigate(NavRoute.AdminChoose.route)
                    }
                ) {
                    Text(text = "???? ??????????????")
                }
            } else {
                Button(
                    modifier = Modifier
                        .padding(top = 16.dp),
                    onClick = {
                        navController.navigate(NavRoute.UserChoose.route)
                    }
                ) {
                    Text(text = "???? ??????????????")
                }
            }

            Text(
                text = "???????????? ????????????????????",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            LazyColumn{
                items(trains) { train ->
                    TrainItem(train = train, navController = navController)
                }
            }
        }

    }
}

@Composable
fun TrainItem(train: TrainModel, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .clickable {
                if (EMAIL == "admin@gmail.com") {
                    navController.navigate(NavRoute.AdminDetailTrain.route + "/${train.id}")
                }
            },
        elevation = 6.dp
    ) {
        Column(modifier = Modifier.padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = train.train,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = train.time,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}