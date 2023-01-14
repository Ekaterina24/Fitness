package com.example.fitness.model

data class FeedbackModel(
    var id: String = "",
    var trainId: String = "",
    var trainTitle: String = "",
    var user: String = "",
    var timestamp: Long = 0,
    var text: String = "",
    var uid: String = ""
)
