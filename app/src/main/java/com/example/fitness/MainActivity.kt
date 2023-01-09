package com.example.fitness

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.fitness.navigation.FitNavHost
import com.example.fitness.ui.theme.FitnessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessTheme {
                val context = LocalContext.current
                val navController = rememberNavController()
                val mViewModel: MainViewModel =
                    viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FitNavHost(mViewModel, navController)

//                    val context = LocalContext.current
//                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//
//                        Button(onClick = {
//
//                            val createNotification = CreateNotification(context, "My title", "This is the content of notification. It's not so important right now!")
//                            createNotification.showNotification()
//
//                        }) {
//
//                            Text(text = "Show notification")
//                        }
//                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FitnessTheme {
        Greeting("Android")
    }
}