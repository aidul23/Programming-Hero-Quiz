package com.aidul23.programmingheroquiz.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aidul23.programmingheroquiz.repository.QuizRepository

class QuizViewModelFactory(val app: Application, private val quizRepository: QuizRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuizViewModel(app, quizRepository) as T
    }
}