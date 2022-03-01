package com.aidul23.programmingheroquiz.model

data class Question(
    val answers: Answers,
    val correctAnswer: String,
    val question: String,
    val questionImageUrl: Any,
    val score: Int
)