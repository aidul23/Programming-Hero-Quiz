package com.aidul23.programmingheroquiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aidul23.programmingheroquiz.api.QuizApi
import com.aidul23.programmingheroquiz.api.RetrofitHelper
import com.aidul23.programmingheroquiz.databinding.ActivityMainBinding
import com.aidul23.programmingheroquiz.model.Quiz
import com.aidul23.programmingheroquiz.repository.QuizRepository
import com.aidul23.programmingheroquiz.viewmodel.QuizViewModel
import com.aidul23.programmingheroquiz.viewmodel.QuizViewModelFactory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var quizViewModel: QuizViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val intent = Intent(this, QuestionActivity::class.java)

        binding.buttonStart.setOnClickListener {
            startActivity(intent)
        }

    }
}



