package com.example.fitness.database

import androidx.lifecycle.LiveData
import com.example.fitness.model.FeedbackModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllFeedbackLiveData: LiveData<List<FeedbackModel>>() {

    private val database = FirebaseDatabase.getInstance().getReference("Feedback")

    private val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val feedback = mutableListOf<FeedbackModel>()
            snapshot.children.map {
                feedback.add(it.getValue(FeedbackModel::class.java) ?: FeedbackModel())
            }
            value = feedback
        }

        override fun onCancelled(error: DatabaseError) {}

    }

    override fun onActive() {
        database.addValueEventListener(listener)
        super.onActive()
    }

    override fun onInactive() {
        database.removeEventListener(listener)
        super.onInactive()
    }
}