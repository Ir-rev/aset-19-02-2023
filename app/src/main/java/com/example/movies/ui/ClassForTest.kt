package com.example.movies.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ClassForTest(
    val text: String
) : Parcelable