package com.aidul23.programmingheroquiz.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aidul23.programmingheroquiz.api.QuizApi
import com.aidul23.programmingheroquiz.model.Quiz

class QuizRepository(val quizApi: QuizApi) {
    private val quizLiveData = MutableLiveData<Quiz>()

    val quiz: LiveData<Quiz>
        get() = quizLiveData

    suspend fun getQuiz() {
        val results = quizApi.getQuiz()
        if (results.body() != null) {
            quizLiveData.postValue(results.body())
        }
    }
}