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
import com.example.fitness.model.TrainModel
import com.example.fitness.navigation.NavRoute
import com.example.fitness.utils.TIME
import com.example.fitness.utils.TRAIN

@Composable
fun AddTrainScreen(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    var train by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var isButtonEnabled by remember { mutableStateOf(false) }

    Surface {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 32.dp)
        ) {
            Text(
                text = "Add Train",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = train,
                onValueChange = {
                    train = it
                    isButtonEnabled = train.isNotEmpty()
                },
                label = { Text(text = "Train") },
                isError = train.isEmpty()
            )
            OutlinedTextField(
                value = time,
                onValueChange = {
                    time = it
                    isButtonEnabled = time.isNotEmpty()
                },
                label = { Text(text = "Time") },
                isError = time.isEmpty()
            )

            Button(
                modifier = Modifier.padding(top = 16.dp),
                enabled = isButtonEnabled,
                onClick = {
                    TRAIN = train
                    TIME = time
                    viewModel.addTrain(train = TrainModel(train = train, time = time)) {
                        navController.navigate(NavRoute.AdminListTrain.route)
                    }
                }) {
                Text(text = "Add train")
            }
        }
    }
}