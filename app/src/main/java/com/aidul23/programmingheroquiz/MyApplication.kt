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

class MyApplication: Application()