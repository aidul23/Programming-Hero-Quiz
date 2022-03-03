package com.aidul23.programmingheroquiz.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.aidul23.programmingheroquiz.model.Quiz
import com.aidul23.programmingheroquiz.repository.QuizRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuizViewModel(app: Application, private val quizRepository: QuizRepository) :
    AndroidViewModel(app) {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            quizRepository.getQuiz()
            Log.d("VIEWMODEL", "called")
        }
    }

    val quiz: LiveData<Quiz>
        get() = quizRepository.quiz

}