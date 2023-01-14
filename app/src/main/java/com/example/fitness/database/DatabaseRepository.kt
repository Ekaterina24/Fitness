package com.example.fitness.database

import androidx.lifecycle.LiveData
import com.example.fitness.model.FeedbackModel
import com.example.fitness.model.TrainModel

interface DatabaseRepository {

    val readAllTrains: LiveData<List<TrainModel>>
    val readAllFeedback: LiveData<List<FeedbackModel>>

    fun registerUser(onSuccess: ()-> Unit, onFail: (String)-> Unit) {}

    fun loginUser(onSuccess: ()-> Unit, onFail: (String)-> Unit) {}

    suspend fun createTrain(train: TrainModel, onSuccess: ()-> Unit)

    suspend fun updateTrain(train: TrainModel, onSuccess: ()-> Unit)

    suspend fun deleteTrain(train: TrainModel, onSuccess: ()-> Unit)

    suspend fun createFeedback(feedback: FeedbackModel, onSuccess: ()-> Unit)

}