package com.aidul23.programmingheroquiz.api

import com.aidul23.programmingheroquiz.model.Quiz
import retrofit2.Response
import retrofit2.http.GET

//https://herosapp.nyc3.digitaloceanspaces.com/quiz.json
interface QuizApi {
    @GET("quiz.json")
    suspend fun getQuiz() : Response<Quiz>
}