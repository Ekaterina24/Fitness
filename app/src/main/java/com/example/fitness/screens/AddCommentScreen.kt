package com.example.fitness.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fitness.MainViewModel
import com.example.fitness.model.CommentModel
import com.example.fitness.model.TrainModel
import com.example.fitness.navigation.NavRoute
import com.example.fitness.utils.*

@Composable
fun AddCommentScreen(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    var comment by remember { mutableStateOf("") }
    val trains = viewModel.getAllTrains().observeAsState(listOf()).value

    var isButtonEnabled by remember { mutableStateOf(false) }

    var mExpanded by remember { mutableStateOf(false) }
    var mSelectedText by remember { mutableStateOf("") }
    var mSelectedId by remember { mutableStateOf("") }

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Surface {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 32.dp)
        ) {
            Text(
                text = "Add Comment",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = comment,
                onValueChange = {
                    comment = it
                    isButtonEnabled = comment.isNotEmpty()
                },
                label = { Text(text = "Comment") },
                isError = comment.isEmpty()
            )

            OutlinedTextField(
                value = mSelectedText,
                onValueChange = { mSelectedText = it },
                modifier = Modifier
                    .fillMaxWidth(),
//                    .onGloballyPositioned { coordinates ->
//                        // This value is used to assign to
//                        // the DropDown the same width
//                    },
                label = {Text("Label")},
                trailingIcon = {
                    Icon(icon,"contentDescription",
                        Modifier.clickable { mExpanded = !mExpanded })
                }
            )

            DropdownMenu(
                expanded = mExpanded,
                onDismissRequest = { mExpanded = false },
            ) {
                trains.forEach { train ->
                    DropdownMenuItem(onClick = {
                        mSelectedText = train.train
                        mSelectedId = train.id.toString()
                        mExpanded = false
                    }) {
                        Text(text = train.train)
                    }
                }
            }

            Button(
                modifier = Modifier.padding(top = 16.dp),
                enabled = isButtonEnabled,
                onClick = {
                    COMMENT = comment
                    SELECTED_TRAIN = mSelectedId
                    SELECTED_TITLE = mSelectedText
                    viewModel.addComment(comment = CommentModel(comment = comment, trainId = SELECTED_TRAIN)) {
                        navController.navigate(NavRoute.AdminCommentList.route)
                    }
                }) {
                Text(text = "Add train")
            }
        }
    }
}