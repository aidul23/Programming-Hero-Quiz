package com.aidul23.programmingheroquiz

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aidul23.programmingheroquiz.api.QuizApi
import com.aidul23.programmingheroquiz.api.RetrofitHelper
import com.aidul23.programmingheroquiz.databinding.ActivityMainBinding
import com.aidul23.programmingheroquiz.databinding.ActivityQuestionBinding
import com.aidul23.programmingheroquiz.repository.QuizRepository
import com.aidul23.programmingheroquiz.viewmodel.QuizViewModel
import com.aidul23.programmingheroquiz.viewmodel.QuizViewModelFactory
import com.bumptech.glide.Glide

class QuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionBinding

    lateinit var quizViewModel: QuizViewModel

    private var correctAnswer: String?= null
    private var totalScore: Int = 0
    private var noOfQuestion: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuestionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val quizService = RetrofitHelper.getInstance().create(QuizApi::class.java)
        val repository = QuizRepository(quizService)

        quizViewModel = ViewModelProvider(this, QuizViewModelFactory(repository)).get(QuizViewModel::class.java)

        quizViewModel.quiz.observe(this, Observer {
            if(it != null) {
                Log.d("quiz", "onCreate: "+it.questions)
                binding.tvQuizQuestion.text = it.questions.get(1).question
                binding.tvQuizPoint.text = it.questions.get(1).score.toString() + " Point"

                binding.buttonOption1.text = it.questions.get(1).answers.A
                binding.buttonOption2.text = it.questions.get(1).answers.B
                binding.buttonOption3.text = it.questions.get(1).answers.C
                binding.buttonOption4.text = it.questions.get(1).answers.D

                Glide.with(this)
                    .load(it.questions.get(1).questionImageUrl)
                    .into(binding.questionImage)

                binding.tvQuestionNo.text ="$noOfQuestion/" +  it.questions.size.toString()
                binding.tvScoreNo.text = totalScore.toString()

                correctAnswer = it.questions.get(1).correctAnswer
            }
        })

        binding.buttonOption1.setOnClickListener {
            if(correctAnswer == "A") {
                Toast.makeText(this,"Correct Answer",Toast.LENGTH_SHORT).show()
                binding.buttonOption1.setBackgroundColor(Color.GREEN)
                binding.buttonOption2.setBackgroundColor(Color.RED)
                binding.buttonOption3.setBackgroundColor(Color.RED)
                binding.buttonOption4.setBackgroundColor(Color.RED)
            }
            else {
                Toast.makeText(this,"Wrong Answer",Toast.LENGTH_SHORT).show()
                binding.buttonOption1.setBackgroundColor(Color.RED)
                binding.buttonOption1.isClickable = false
                binding.buttonOption2.isClickable = false
                binding.buttonOption3.isClickable = false
                binding.buttonOption4.isClickable = false
            }
        }

        binding.buttonOption2.setOnClickListener {
            if(correctAnswer == "B") {
                Toast.makeText(this,"Correct Answer",Toast.LENGTH_SHORT).show()
                binding.buttonOption2.setBackgroundColor(Color.GREEN)
                binding.buttonOption1.setBackgroundColor(Color.RED)
                binding.buttonOption3.setBackgroundColor(Color.RED)
                binding.buttonOption4.setBackgroundColor(Color.RED)
            }
            else {
                Toast.makeText(this,"Wrong Answer",Toast.LENGTH_SHORT).show()
                binding.buttonOption2.setBackgroundColor(Color.RED)

                binding.buttonOption1.isClickable = false
                binding.buttonOption2.isClickable = false
                binding.buttonOption3.isClickable = false
                binding.buttonOption4.isClickable = false
            }
        }

        binding.buttonOption3.setOnClickListener {
            if(correctAnswer == "C") {
                Toast.makeText(this,"Correct Answer",Toast.LENGTH_SHORT).show()
                binding.buttonOption3.setBackgroundColor(Color.GREEN)
                binding.buttonOption2.setBackgroundColor(Color.RED)
                binding.buttonOption1.setBackgroundColor(Color.RED)
                binding.buttonOption4.setBackgroundColor(Color.RED)
            }
            else {
                Toast.makeText(this,"Wrong Answer",Toast.LENGTH_SHORT).show()
                binding.buttonOption3.setBackgroundColor(Color.RED)
                binding.buttonOption1.isClickable = false
                binding.buttonOption2.isClickable = false
                binding.buttonOption3.isClickable = false
                binding.buttonOption4.isClickable = false
            }
        }

        binding.buttonOption4.setOnClickListener {
            if(correctAnswer == "D") {
                Toast.makeText(this,"Correct Answer",Toast.LENGTH_SHORT).show()
                binding.buttonOption4.setBackgroundColor(Color.GREEN)
                binding.buttonOption2.setBackgroundColor(Color.RED)
                binding.buttonOption3.setBackgroundColor(Color.RED)
                binding.buttonOption1.setBackgroundColor(Color.RED)
            }
            else {
                Toast.makeText(this,"Wrong Answer",Toast.LENGTH_SHORT).show()
                binding.buttonOption4.setBackgroundColor(Color.RED)
                binding.buttonOption1.isClickable = false
                binding.buttonOption2.isClickable = false
                binding.buttonOption3.isClickable = false
                binding.buttonOption4.isClickable = false
            }
        }


    }
}