package com.joaomoreno.chucknorrisjokesapp.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoriesVO(
    val categories : List<String>
) : Parcelable
