package com.example.fitness.model

data class CommentModel(
    var id: String = "",
    var trainId: String = "",
    var trainTitle: String = "",
    var user: String = "",
    var timestamp: Long = 0,
    var comment: String = "",
    var uid: String = ""
)
