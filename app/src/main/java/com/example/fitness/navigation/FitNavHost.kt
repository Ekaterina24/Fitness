package com.example.fitness.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fitness.MainViewModel
import com.example.fitness.screens.*
import com.example.fitness.utils.TRAIN_ID

sealed class NavRoute(val route: String) {
    object Login : NavRoute("login_screen")
    object Register : NavRoute("register_screen")
    object ListTrain : NavRoute("list_train")
    object AdminDetailTrain : NavRoute("admin_detail_train")
    object AdminChoose : NavRoute("admin_choose")
    object AddTrain : NavRoute("add_train")
    object FeedbackList : NavRoute("feedback_list")
    object AddFeedback : NavRoute("add_feedback")
    object UserChoose : NavRoute("user_choose")
}

@Composable
fun FitNavHost(
    mViewModel: MainViewModel,
    navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavRoute.Login.route) {
        composable(NavRoute.Login.route) {
            LoginScreen(
                navController = navController,
                viewModel = mViewModel
            )
        }
        composable(NavRoute.Register.route) {
            RegisterScreen(
                navController = navController,
                viewModel = mViewModel
            )
        }
        composable(NavRoute.ListTrain.route) {
            ListTrainScreen(
                navController = navController,
                viewModel = mViewModel
            )
        }
        composable(NavRoute.AddFeedback.route) {
            AddFeedbackScreen(
                navController = navController,
                viewModel = mViewModel
            )
        }
        composable(NavRoute.AddTrain.route) {
            AddTrainScreen(
                navController = navController,
                viewModel = mViewModel
            )
        }
        composable(NavRoute.FeedbackList.route) {
            FeedbackListScreen(
                navController = navController,
                viewModel = mViewModel
            )
        }
        composable(NavRoute.AdminDetailTrain.route + "/{${TRAIN_ID}}") { backStackEntry ->
            AdminDetailTrainScreen(
                navController = navController,
                viewModel = mViewModel,
                trainId = backStackEntry.arguments?.getString(TRAIN_ID)
            )
        }
        composable(NavRoute.AdminChoose.route) {
            AdminChooseScreen(
                navController = navController
            )
        }
        composable(NavRoute.UserChoose.route) {
            UserChooseScreen(
                navController = navController
            )
        }

    }
}