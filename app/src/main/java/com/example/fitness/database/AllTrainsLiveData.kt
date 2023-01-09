package com.example.fitness.database


import androidx.lifecycle.LiveData
import com.example.fitness.model.TrainModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllTrainsLiveData: LiveData<List<TrainModel>>() {

    private val database = FirebaseDatabase.getInstance().getReference("Trains")

    private val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val trains = mutableListOf<TrainModel>()
            snapshot.children.map {
                trains.add(it.getValue(TrainModel::class.java) ?: TrainModel())
            }
            value = trains
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