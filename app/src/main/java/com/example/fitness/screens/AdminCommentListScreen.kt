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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fitness.MainViewModel
import com.example.fitness.model.CommentModel
import com.example.fitness.model.TrainModel
import com.example.fitness.navigation.NavRoute
import com.example.fitness.utils.EMAIL
import com.example.fitness.utils.NAME
import com.example.fitness.utils.SELECTED_TITLE

@Composable
fun AdminCommentListScreen(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    val comments = viewModel.getAllComments().observeAsState(listOf()).value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NavRoute.AddComment.route)
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
            Button(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                onClick = {
                    navController.navigate(NavRoute.AdminChoose.route)
                }
            ) {
                Text(text = "Back to Choose")
            }
            Text(
                text = "Create Comment",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            LazyColumn{
                items(comments) { comment ->
                    CommentItem(comment = comment, navController = navController)
                }
            }
        }

    }
}

@Composable
fun CommentItem(comment: CommentModel, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .clickable {
//                navController.navigate(NavRoute.AdminDetailTrain.route + "/{${day.id}}")
            },
        elevation = 6.dp
    ) {
        Column(modifier = Modifier.padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = comment.user,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = comment.comment,
                fontSize = 18.sp
            )
            Text(text = comment.trainTitle,
                fontSize = 16.sp
            )
        }
    }
}