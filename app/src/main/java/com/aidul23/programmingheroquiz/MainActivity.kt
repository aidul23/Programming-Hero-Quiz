package com.aidul23.programmingheroquiz

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aidul23.programmingheroquiz.api.QuizApi
import com.aidul23.programmingheroquiz.api.RetrofitHelper
import com.aidul23.programmingheroquiz.constants.Constant
import com.aidul23.programmingheroquiz.constants.Constant.KEY_HIGHSCORE
import com.aidul23.programmingheroquiz.constants.Constant.REQUEST_CODE_QUIZ
import com.aidul23.programmingheroquiz.constants.Constant.SHARED_PREF
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
    var highScore: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        loadHighScore()

        val intent = Intent(this, QuestionActivity::class.java)

        binding.buttonStart.setOnClickListener {
            startActivityForResult(intent,REQUEST_CODE_QUIZ)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE_QUIZ) {
            if(resultCode == RESULT_OK) {
                var score = data?.getIntExtra(Constant.STRING_EXTRA_SCORE, 0)
                if (score != null) {
                    if(score > highScore) {
                        updateHighScore(score)
                    }
                }
            }
        }
    }

    private fun loadHighScore() {
        val sharedPref = getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
        highScore = sharedPref.getInt(SHARED_PREF,0)
        binding.tvHighScore.text = highScore.toString() + " Point"
    }

    private fun updateHighScore(highScoreNew: Int) {
        highScore = highScoreNew
        binding.tvHighScore.text = highScore.toString() + " Point"

        val sharedPref = getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
        var editor = sharedPref.edit()
        editor.putInt(KEY_HIGHSCORE, highScore)
        editor.apply()
    }
}



