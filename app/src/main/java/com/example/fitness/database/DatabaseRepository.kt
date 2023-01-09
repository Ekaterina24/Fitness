package com.example.fitness.database

import androidx.lifecycle.LiveData
import com.example.fitness.model.CommentModel
import com.example.fitness.model.TrainModel

interface DatabaseRepository {

    val readAllTrains: LiveData<List<TrainModel>>
    val readAllComments: LiveData<List<CommentModel>>

    fun signOut() {}

    fun registerUser(onSuccess: ()-> Unit, onFail: (String)-> Unit) {}

    fun loginUser(onSuccess: ()-> Unit, onFail: (String)-> Unit) {}

    suspend fun createTrain(train: TrainModel, onSuccess: ()-> Unit)

    suspend fun updateTrain(train: TrainModel, onSuccess: ()-> Unit)

    suspend fun deleteTrain(train: TrainModel, onSuccess: ()-> Unit)

    suspend fun createComment(comment: CommentModel, onSuccess: ()-> Unit)

}