package com.aidul23.programmingheroquiz

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aidul23.programmingheroquiz.api.QuizApi
import com.aidul23.programmingheroquiz.api.RetrofitHelper
import com.aidul23.programmingheroquiz.databinding.ActivityQuestionBinding
import com.aidul23.programmingheroquiz.model.Question
import com.aidul23.programmingheroquiz.repository.QuizRepository
import com.aidul23.programmingheroquiz.viewmodel.QuizViewModel
import com.aidul23.programmingheroquiz.viewmodel.QuizViewModelFactory
import com.bumptech.glide.Glide

class QuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionBinding
    lateinit var quizViewModel: QuizViewModel

    private var correctAnswer: String? = null

    private var totalScore: Int = 0

    private var noOfQuestion: Int = 0
    private var totalNoOfQuestion: Int ?= null
    private lateinit var countDownTimer: CountDownTimer
    private var timeLeftInMillis: Long? = 0
    val handler = Handler()
    var myList = ArrayList<Question>()
    var mutableLiveData = MutableLiveData<Question>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuestionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val quizService = RetrofitHelper.getInstance().create(QuizApi::class.java)
        val repository = QuizRepository(quizService)


        quizViewModel =
            ViewModelProvider(this, QuizViewModelFactory(repository)).get(QuizViewModel::class.java)

        quizViewModel.quiz.observe(this, Observer {
            if (it != null) {
                myList = it.questions as ArrayList<Question>
                totalNoOfQuestion = it.questions.size
                mutableLiveData.value = myList.get(noOfQuestion)
                nextQuiz(mutableLiveData)
//                correctAnswer = myList.get(noOfQuestion).correctAnswer
                Log.d("MY_LIST", "onCreate: " + myList)
            }
        })



        binding.buttonOption1.setOnClickListener {
            if (mutableLiveData.value?.correctAnswer == "A") {
                Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show()
                binding.buttonOption1.setBackgroundColor(Color.GREEN)
                totalScore += mutableLiveData.value!!.score
            } else {
                Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
                binding.buttonOption1.setBackgroundColor(Color.RED)
                if (correctAnswer == "B") {
                    binding.buttonOption2.setBackgroundColor(Color.GREEN)
                } else if (correctAnswer == "C") {
                    binding.buttonOption3.setBackgroundColor(Color.GREEN)
                } else {
                    binding.buttonOption4.setBackgroundColor(Color.GREEN)
                }
            }
            makeButtonUnclickable()

            handler.postDelayed({
                makeButtonClickable()
                resetButton()
                noOfQuestion++
                if(noOfQuestion <= totalNoOfQuestion!!) {
                    mutableLiveData.value = myList.get(noOfQuestion)
                    nextQuiz(mutableLiveData)
                }
                else {
                    handler.postDelayed({
                        this.finish()
                    },1000)
                }
            }, 2000)

        }

        binding.buttonOption2.setOnClickListener {
            if (mutableLiveData.value?.correctAnswer == "B") {
                Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show()
                binding.buttonOption2.setBackgroundColor(Color.GREEN)
                totalScore += mutableLiveData.value!!.score
            } else {
                Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
                binding.buttonOption2.setBackgroundColor(Color.RED)
                if (correctAnswer == "A") {
                    binding.buttonOption1.setBackgroundColor(Color.GREEN)
                } else if (correctAnswer == "C") {
                    binding.buttonOption3.setBackgroundColor(Color.GREEN)
                } else {
                    binding.buttonOption4.setBackgroundColor(Color.GREEN)
                }
            }
            makeButtonUnclickable()
            handler.postDelayed({
                makeButtonClickable()
                resetButton()
                noOfQuestion++
                if(noOfQuestion <= totalNoOfQuestion!!) {
                    mutableLiveData.value = myList.get(noOfQuestion)
                    nextQuiz(mutableLiveData)
                } else {
                    handler.postDelayed({
                        this.finish()
                    },1000)
                }
            }, 2000)
        }

        binding.buttonOption3.setOnClickListener {
            if (mutableLiveData.value?.correctAnswer == "C") {
                Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show()
                binding.buttonOption3.setBackgroundColor(Color.GREEN)
                totalScore += mutableLiveData.value!!.score

            } else {
                Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
                binding.buttonOption3.setBackgroundColor(Color.RED)
                if (correctAnswer == "A") {
                    binding.buttonOption1.setBackgroundColor(Color.GREEN)
                } else if (correctAnswer == "B") {
                    binding.buttonOption2.setBackgroundColor(Color.GREEN)
                } else {
                    binding.buttonOption4.setBackgroundColor(Color.GREEN)
                }
            }
            handler.postDelayed({
                makeButtonClickable()
                resetButton()
                noOfQuestion++
                if(noOfQuestion <= totalNoOfQuestion!!) {
                    mutableLiveData.value = myList.get(noOfQuestion)
                    nextQuiz(mutableLiveData)
                } else {
                    handler.postDelayed({
                        this.finish()
                    },1000)
                }
            }, 2000)
        }

        binding.buttonOption4.setOnClickListener {
            if (mutableLiveData.value?.correctAnswer == "D") {
                Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show()
                binding.buttonOption4.setBackgroundColor(Color.GREEN)
                totalScore += mutableLiveData.value!!.score
            } else {
                Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
                binding.buttonOption4.setBackgroundColor(Color.RED)
                if (correctAnswer == "A") {
                    binding.buttonOption1.setBackgroundColor(Color.GREEN)
                } else if (correctAnswer == "B") {
                    binding.buttonOption2.setBackgroundColor(Color.GREEN)
                } else {
                    binding.buttonOption3.setBackgroundColor(Color.GREEN)
                }
            }
            handler.postDelayed({
                makeButtonClickable()
                resetButton()
                noOfQuestion++
                if(noOfQuestion <= totalNoOfQuestion!!) {
                    mutableLiveData.value = myList.get(noOfQuestion)
                    nextQuiz(mutableLiveData)
                }
                else {
                    handler.postDelayed({
                        this.finish()
                    },1000)
                }
            }, 2000)
        }

    }

    private fun makeButtonUnclickable() {
        binding.buttonOption1.isClickable = false
        binding.buttonOption2.isClickable = false
        binding.buttonOption3.isClickable = false
        binding.buttonOption4.isClickable = false
    }
    private fun makeButtonClickable() {
        binding.buttonOption1.isClickable = true
        binding.buttonOption2.isClickable = true
        binding.buttonOption3.isClickable = true
        binding.buttonOption4.isClickable = true
    }
    private fun resetButton() {
        binding.buttonOption1.setBackgroundColor(Color.WHITE)
        binding.buttonOption2.setBackgroundColor(Color.WHITE)
        binding.buttonOption3.setBackgroundColor(Color.WHITE)
        binding.buttonOption4.setBackgroundColor(Color.WHITE)
    }

    private fun nextQuiz(myList: MutableLiveData<Question>) {
        binding.tvQuizQuestion.text = myList.value?.question

        binding.tvQuizPoint.text =
            myList.value?.score.toString() + " Point"

        binding.buttonOption1.text = myList.value?.answers?.A
        binding.buttonOption2.text = myList.value?.answers?.B
        binding.buttonOption3.text = myList.value?.answers?.C
        binding.buttonOption4.text = myList.value?.answers?.D

        if (myList.value?.questionImageUrl != null) {
            binding.imageConstrainLayout.visibility = View.VISIBLE
            Glide.with(this)
                .load(myList.value?.questionImageUrl)
                .into(binding.questionImage)
        } else {
            binding.imageConstrainLayout.visibility = View.GONE
        }

        binding.tvQuestionNo.text = "${this.noOfQuestion + 1}/" + totalNoOfQuestion
        binding.tvScoreNo.text = totalScore.toString()

//        myList[noOfQuestion].answers.A?.let {
//            if (it.hashCode() == null) {
//                binding.buttonOption1.visibility = View.GONE
//            } else {
//                binding.buttonOption1.text = it
//            }
//
//        }
//        myList[noOfQuestion].answers.B?.let {
//            if (it.hashCode() == null) {
//                binding.buttonOption2.visibility = View.GONE
//            } else {
//                binding.buttonOption2.text = it
//            }
//        }
//        myList[noOfQuestion].answers.C?.let {
//            if (it.hashCode() == null) {
//                binding.buttonOption3.visibility = View.GONE
//                Log.d("Button", "nextQuiz: buttonC")
//            } else {
//                binding.buttonOption3.text = it
//            }
//        }
//        myList[noOfQuestion].answers.D?.let {
//            if (it.hashCode() == null) {
//                binding.buttonOption4.visibility = View.GONE
////                Log.d("Button", "nextQuiz: buttonD")
////            } else {
////                binding.buttonOption4.text = it
////            }
////        }
//
//

//
//
//

//
//
//                //countdown
////                timeLeftInMillis = COUNTDOWN_IN_MILLIS as Long
////
////                countDownTimer = object: CountDownTimer(timeLeftInMillis!!, 1000) {
////                    override fun onTick(millisUntilFinished: Long) {
////                        timeLeftInMillis = millisUntilFinished
////                        updateCountDownText()
////                    }
////
////                    override fun onFinish() {
////                        timeLeftInMillis = 0
////                        updateCountDownText()
////                    }
////                }
////                countDownTimer.start()
//            }
//        }

//    private fun updateCountDownText() {
//        val min = (timeLeftInMillis?.div(1000))?.div(60)
//        val sec = (timeLeftInMillis?.div(1000))?.rem(60)
//
//        val timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", min, sec)
//        binding.tvCountDown.text = timeFormatted
//    }

    }

}