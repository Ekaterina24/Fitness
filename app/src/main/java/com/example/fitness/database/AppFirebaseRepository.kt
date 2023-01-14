package com.example.fitness.database

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.fitness.model.FeedbackModel
import com.example.fitness.model.TrainModel
import com.example.fitness.utils.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AppFirebaseRepository : DatabaseRepository {
    private val mAuth = FirebaseAuth.getInstance()
    private val database = Firebase.database.reference
        .child(mAuth.currentUser?.uid.toString())

    override val readAllTrains: LiveData<List<TrainModel>> = AllTrainsLiveData()
    override val readAllFeedback: LiveData<List<FeedbackModel>> = AllFeedbackLiveData()

    override fun loginUser(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        mAuth.signInWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                Log.d("@@@", "Error to login user")
            }

    }

    override fun registerUser(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        mAuth.createUserWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener {
                updateUserInfo(onSuccess)
            }
            .addOnFailureListener {
                Log.d("@@@", "Error to create user")
            }
    }

    private fun updateUserInfo(onSuccess: () -> Unit) {
        val uid = database.push().key.toString()
        val timestamp = System.currentTimeMillis()

        val hashMap: HashMap<String, Any?> = HashMap()
        hashMap["uid"] = uid
        hashMap["email"] = EMAIL
        hashMap["name"] = NAME
        hashMap["userType"] = "user"
        hashMap["timestamp"] = timestamp

        val ref = FirebaseDatabase.getInstance().getReference("Users")
        ref.child(uid)
            .setValue(hashMap)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                Log.d("@@@", "Failed to created account...")
            }
    }

    override suspend fun createTrain(train: TrainModel, onSuccess: () -> Unit) {
        val timestamp = System.currentTimeMillis()
        val trainId = database.push().key.toString()

        val hashMap = HashMap<String, Any>()
        hashMap["id"] = trainId
        hashMap["train"] = TRAIN
        hashMap["time"] = TIME
        hashMap["timestamp"] = "$timestamp"

        val ref = FirebaseDatabase.getInstance().getReference("Trains")
        ref.child(trainId)
            .setValue(hashMap)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                Log.d("checkData", "Failed to add new train")
            }
    }

    override suspend fun updateTrain(train: TrainModel, onSuccess: () -> Unit) {
        val timestamp = System.currentTimeMillis()

        val hashMap = HashMap<String, Any>()
        hashMap["train"] = train.train
        hashMap["time"] = train.time
        hashMap["timestamp"] = "$timestamp"

        val ref = FirebaseDatabase.getInstance().getReference("Trains")
        ref.child(train.id)
            .updateChildren(hashMap)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                Log.d("checkData", "Failed to add update train")
            }
    }

    override suspend fun deleteTrain(train: TrainModel, onSuccess: () -> Unit) {
        val ref = FirebaseDatabase.getInstance().getReference("Trains")
        ref.child(train.id).removeValue()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { Log.d("checkData", "Failed to delete train") }
    }

    override suspend fun createFeedback(feedback: FeedbackModel, onSuccess: () -> Unit) {
        val timestamp = System.currentTimeMillis()

        val hashMap = HashMap<String, Any>()
        hashMap["id"] = "$timestamp"
        hashMap["trainId"] = SELECTED_TRAIN
        hashMap["trainTitle"] = SELECTED_TITLE
        hashMap["user"] = EMAIL
        hashMap["text"] = FEEDBACK
        hashMap["timestamp"] = timestamp

        val ref = FirebaseDatabase.getInstance().getReference("Feedback")
        ref.child("$timestamp")
            .setValue(hashMap)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                Log.d("checkData", "Failed to add new feedback")
            }
    }
}