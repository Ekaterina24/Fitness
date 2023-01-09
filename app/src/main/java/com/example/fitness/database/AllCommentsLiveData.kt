package com.example.fitness.database

import androidx.lifecycle.LiveData
import com.example.fitness.model.CommentModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllCommentsLiveData: LiveData<List<CommentModel>>() {

    private val database = FirebaseDatabase.getInstance().getReference("Comments")

    private val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val comments = mutableListOf<CommentModel>()
            snapshot.children.map {
                comments.add(it.getValue(CommentModel::class.java) ?: CommentModel())
            }
            value = comments
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