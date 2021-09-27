package com.joaomoreno.chucknorrisjokesapp.vo

import android.os.Parcelable
import android.webkit.WebView
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class JokesVO(
    @SerializedName("total")
    val total : Int,
    @SerializedName("result")
    val result: List<JokeVO>
):Parcelable
