package com.aidul23.programmingheroquiz.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity
import com.aidul23.programmingheroquiz.ui.QuestionActivity.Companion.STRING_EXTRA_SCORE
import com.aidul23.programmingheroquiz.constants.Constant.KEY_HIGHSCORE
import com.aidul23.programmingheroquiz.constants.Constant.REQUEST_CODE_QUIZ
import com.aidul23.programmingheroquiz.constants.Constant.SHARED_PREF
import com.aidul23.programmingheroquiz.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val handler = Handler()
    var highScore: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val loadingDialog =
            LoadingDialog(this)

        loadHighScore()

        binding.buttonStart.setOnClickListener {
            if (hasInternetConnection()) {
                loadingDialog.startLoadingDialog()
                handler.postDelayed({
                    val intent = Intent(this, QuestionActivity::class.java)
                    startActivityForResult(intent, REQUEST_CODE_QUIZ)
                    loadingDialog.dismissDialog()
                }, 1000)
            } else {
                val snackbar = Snackbar.make(
                    view, "Check your internet connection!",
                    Snackbar.LENGTH_LONG
                ).setAction("Retry", null)
                snackbar.show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == RESULT_OK) {
                val score = data?.getIntExtra(STRING_EXTRA_SCORE, 0)
                if (score != null) {
                    if (score > highScore) {
                        updateHighScore(score)
                    }
                }
            }
        }
    }

    private fun loadHighScore() {
        val sharedPref = getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
        highScore = sharedPref.getInt(KEY_HIGHSCORE, 0)
        binding.tvHighScore.text = highScore.toString() + " Point"
    }

    private fun updateHighScore(highScoreNew: Int) {
        highScore = highScoreNew
        binding.tvHighScore.text = highScore.toString() + " Point"

        val sharedPref = getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putInt(KEY_HIGHSCORE, highScore)
        editor.apply()
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ContactsContract.CommonDataKinds.Email.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}



