package com.aidul23.programmingheroquiz.quizDao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aidul23.programmingheroquiz.model.Question

@Dao
interface quizDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(question: Question)

    @Query("SELECT * FROM quiz_table")
    fun getAllQuiz(): LiveData<List<Question>>
}