package com.aidul23.programmingheroquiz.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
@Entity(tableName = "quiz_table")
data class Question(
    val answers: Answers,
    val correctAnswer: String,
    val question: String,
    val questionImageUrl: @RawValue Any,
    val score: Int
): Parcelable