package com.aidul23.programmingheroquiz.ui

import android.content.Intent
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
import kotlin.collections.ArrayList

class QuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionBinding
    lateinit var quizViewModel: QuizViewModel
    private var totalScore = 0
    private var noOfQuestion: Int = 0
    private var totalNoOfQuestion: Int? = null
    val handler = Handler()
    var myList = ArrayList<Question>()
    var mutableLiveData = MutableLiveData<Question>()
    var backPressedTime: Long = 0L
    var resultIntent = Intent()

    companion object {
        const val STRING_EXTRA_SCORE = "extraScore"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuestionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val quizService = RetrofitHelper.getInstance().create(QuizApi::class.java)
        val repository = QuizRepository(quizService)

        val loadingDialog =
            LoadingDialog(this)

        loadingDialog.startLoadingDialog()

        handler.postDelayed({
            loadingDialog.dismissDialog()
        }, 2000)

        quizViewModel =
            ViewModelProvider(
                this,
                QuizViewModelFactory(application, repository)
            ).get(QuizViewModel::class.java)

        quizViewModel.quiz.observe(this, Observer {
            if (it != null) {
                myList = it.questions as ArrayList<Question>
                totalNoOfQuestion = it.questions.size
                mutableLiveData.value = myList.get(noOfQuestion)
                nextQuiz(mutableLiveData)
            }
        })

//        callCountDown()

        binding.buttonOption1.setOnClickListener {
            if (mutableLiveData.value?.correctAnswer == "A") {
                Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show()
                binding.buttonOption1.setBackgroundColor(Color.GREEN)
                totalScore += mutableLiveData.value!!.score

            } else {
                Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
                binding.buttonOption1.setBackgroundColor(Color.RED)
                if (mutableLiveData.value?.correctAnswer == "B") {
                    binding.buttonOption2.setBackgroundColor(Color.GREEN)
                } else if (mutableLiveData.value?.correctAnswer == "C") {
                    binding.buttonOption3.setBackgroundColor(Color.GREEN)
                } else {
                    binding.buttonOption4.setBackgroundColor(Color.GREEN)
                }
            }
            makeButtonUnclickable()

            handler.postDelayed({
                if (noOfQuestion <= totalNoOfQuestion!! - 1) {
                    noOfQuestion++
                    if (noOfQuestion > totalNoOfQuestion!! - 1) {
                        finishQuiz()
                    } else {
                        mutableLiveData.value = myList.get(noOfQuestion)
                        nextQuiz(mutableLiveData)
                    }
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
                if (mutableLiveData.value?.correctAnswer == "A") {
                    binding.buttonOption1.setBackgroundColor(Color.GREEN)
                } else if (mutableLiveData.value?.correctAnswer == "C") {
                    binding.buttonOption3.setBackgroundColor(Color.GREEN)
                } else {
                    binding.buttonOption4.setBackgroundColor(Color.GREEN)
                }
            }
            makeButtonUnclickable()

            handler.postDelayed({
                if (noOfQuestion <= totalNoOfQuestion!! - 1) {
                    noOfQuestion++
                    if (noOfQuestion > totalNoOfQuestion!! - 1) {
                        finishQuiz()
                    } else {
                        mutableLiveData.value = myList.get(noOfQuestion)
                        nextQuiz(mutableLiveData)
                    }
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
                if (mutableLiveData.value?.correctAnswer == "A") {
                    binding.buttonOption1.setBackgroundColor(Color.GREEN)
                } else if (mutableLiveData.value?.correctAnswer == "B") {
                    binding.buttonOption2.setBackgroundColor(Color.GREEN)
                } else {
                    binding.buttonOption4.setBackgroundColor(Color.GREEN)
                }
            }
            makeButtonUnclickable()


            handler.postDelayed({
                if (noOfQuestion <= totalNoOfQuestion!! - 1) {
                    noOfQuestion++
                    if (noOfQuestion > totalNoOfQuestion!! - 1) {
                        finishQuiz()
                    } else {
                        mutableLiveData.value = myList.get(noOfQuestion)
                        nextQuiz(mutableLiveData)
                    }
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
                if (mutableLiveData.value?.correctAnswer == "A") {
                    binding.buttonOption1.setBackgroundColor(Color.GREEN)
                } else if (mutableLiveData.value?.correctAnswer == "B") {
                    binding.buttonOption2.setBackgroundColor(Color.GREEN)
                } else {
                    binding.buttonOption3.setBackgroundColor(Color.GREEN)
                }
            }
            makeButtonUnclickable()

            handler.postDelayed({
                if (noOfQuestion <= totalNoOfQuestion!! - 1) {
                    noOfQuestion++
                    if (noOfQuestion > totalNoOfQuestion!! - 1) {
                        finishQuiz()
                    } else {
                        mutableLiveData.value = myList.get(noOfQuestion)
                        nextQuiz(mutableLiveData)
                    }
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

        binding.buttonOption1.visibility = View.VISIBLE
        binding.buttonOption2.visibility = View.VISIBLE
        binding.buttonOption3.visibility = View.VISIBLE
        binding.buttonOption4.visibility = View.VISIBLE
    }

    private fun nextQuiz(myList: MutableLiveData<Question>) {
        binding.tvQuizQuestion.text = myList.value?.question

        binding.tvQuizPoint.text =
            myList.value?.score.toString() + " Point"

        makeButtonClickable()
        resetButton()

        binding.buttonOption1.text = myList.value?.answers?.A
        binding.buttonOption2.text = myList.value?.answers?.B

        if (!myList.value?.answers?.C.isNullOrBlank()) {
            binding.buttonOption3.text = myList.value?.answers?.C
        } else {
            binding.buttonOption3.visibility = View.GONE
        }

        if (!myList.value?.answers?.D.isNullOrBlank()) {
            binding.buttonOption4.text = myList.value?.answers?.D
        } else {
            binding.buttonOption4.visibility = View.GONE
        }

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
    }

    private fun finishQuiz() {
        resultIntent.putExtra(STRING_EXTRA_SCORE, totalScore)
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    private fun callCountDown() {
        object : CountDownTimer(20000, 1000) {
            override fun onTick(p0: Long) {
                binding.tvCountDown.text = ((p0 / 1000).toString() + "s")
            }

            override fun onFinish() {
                binding.tvCountDown.visibility = View.GONE
                Toast.makeText(applicationContext, "Times Up!", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz()
        } else {
            Toast.makeText(this, "Press Again to Exit", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }

}