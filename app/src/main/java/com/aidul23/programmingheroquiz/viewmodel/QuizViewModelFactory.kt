package com.aidul23.programmingheroquiz.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aidul23.programmingheroquiz.repository.QuizRepository

class QuizViewModelFactory(private val quizRepository: QuizRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuizViewModel(quizRepository) as T
    }
}