package com.aidul23.programmingheroquiz.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Quiz(
    val questions: List<Question>
): Parcelable