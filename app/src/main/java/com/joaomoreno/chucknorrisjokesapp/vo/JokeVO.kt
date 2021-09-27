package com.joaomoreno.chucknorrisjokesapp.vo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class JokeVO(
    @SerializedName("categories")
    var categories : List<String>? = mutableListOf(),
    @SerializedName("created_at")
    val createdAt : String?,
    @SerializedName("icon_url")
    val iconUrl : String?,
    @SerializedName("id")
    val id : String?,
    @SerializedName("updated_at")
    val updatedAt : String?,
    @SerializedName("url")
    val url : String?,
    @SerializedName("value")
    val value : String?
): Parcelable