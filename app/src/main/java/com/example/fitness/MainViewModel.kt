package com.example.fitness

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fitness.database.AppFirebaseRepository
import com.example.fitness.model.FeedbackModel
import com.example.fitness.model.TrainModel
import com.example.fitness.utils.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val context = application

    fun getAllTrains() = REPOSITORY.readAllTrains
    fun getAllFeedback() = REPOSITORY.readAllFeedback

    fun connectToRegisterUser(onSuccess: () -> Unit) {
        REPOSITORY = AppFirebaseRepository()
        REPOSITORY.registerUser(
            { onSuccess() },
            { Log.d("@@@", "Error: $it") }
        )
    }

    fun connectToLoginUser(onSuccess: () -> Unit) {
        REPOSITORY = AppFirebaseRepository()
        REPOSITORY.loginUser(
            { onSuccess() },
            { Log.d("@@@", "Error: $it") }
        )
    }

    fun addTrain(train: TrainModel, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.createTrain(train = train) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun updateTrain(train: TrainModel, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.updateTrain(train = train) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun deleteTrain(train: TrainModel, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.deleteTrain(train = train) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun addFeedback(feedback: FeedbackModel, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.createFeedback(feedback = feedback) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }
}

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application = application) as T
        }
        throw IllegalAccessException("Unknown ViewModel Class")
    }
}