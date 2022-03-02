package com.aidul23.programmingheroquiz

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aidul23.programmingheroquiz.api.QuizApi
import com.aidul23.programmingheroquiz.api.RetrofitHelper
import com.aidul23.programmingheroquiz.model.Quiz
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

    }

//    companion object {
//
//        fun get(): Quiz{
//            var quiz = Quiz(emptyList())
//            val quizService = RetrofitHelper.getInstance().create(QuizApi::class.java)
//            GlobalScope.launch {
//                quiz = quizService.getQuiz().let {
//                    it.body()!!
//                }
//            }
//            Log.d("APPLICATION", "get: "+quiz)
//            return quiz
//        }
//    }
}