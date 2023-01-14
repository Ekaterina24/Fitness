package com.example.fitness.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fitness.MainViewModel
import com.example.fitness.model.TrainModel
import com.example.fitness.navigation.NavRoute
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AdminDetailTrainScreen(
    navController: NavHostController,
    viewModel: MainViewModel,
    trainId: String?
) {
    val trains = viewModel.getAllTrains().observeAsState(listOf()).value
    val trainModel = trains.firstOrNull { it.id == trainId } ?: TrainModel()

    var train by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var isButtonEnabled by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 32.dp)
                ) {
                    Text(
                        text = "Изменить тренировку",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = train,
                        onValueChange = {
                            train = it
                        },
                        label = { Text(text = "Название") },
                        isError = train.isEmpty()
                    )
                    OutlinedTextField(
                        value = time,
                        onValueChange = {
                            time = it
                        },
                        label = { Text(text = "Время") },
                        isError = time.isEmpty()
                    )
                    Button(
                        modifier = Modifier.padding(top = 16.dp),
                        onClick = {
                            viewModel.updateTrain(train = TrainModel(id = trainModel.id, train = train, time = time)) {
                                navController.navigate(NavRoute.ListTrain.route)
                            }
                        }) {
                        Text(text = "Обновить")
                    }
                }
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = trainModel.train,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 32.dp)
                        )
                        Text(text = trainModel.time,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier.padding(top = 16.dp))
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                train = trainModel.train
                                time = trainModel.time
                                bottomSheetState.show()
                            }
                        }
                    ) {
                        Text(text = "Изменить")
                    }
                    Button(
                        modifier = Modifier
                            .padding(horizontal = 8.dp),
                        onClick = {
                            viewModel.deleteTrain(train = trainModel) {
                                navController.navigate(NavRoute.ListTrain.route)
                            }
                        }
                    ) {
                        Text(text = "Удалить")
                    }
                    Button(
                        modifier = Modifier
                            .padding(horizontal = 8.dp),
                        onClick = {
                            navController.navigate(NavRoute.ListTrain.route)
                        }
                    ) {
                        Text(text = "Назад")
                    }
                }
            }
        }
    }
}