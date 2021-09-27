package com.joaomoreno.chucknorrisjokesapp.network

import com.joaomoreno.chucknorrisjokesapp.vo.CategoriesVO
import com.joaomoreno.chucknorrisjokesapp.vo.JokeVO
import com.joaomoreno.chucknorrisjokesapp.vo.JokesVO
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckNorrisJokeApi {

    @GET("jokes/random")
    suspend fun getRandomJokeByCategory(@Query("category") category : String) : JokeVO

    @GET("jokes/categories")
    suspend fun  getCategories() : List<String>

    @GET("jokes/search")
    suspend fun getJokeByQuery(@Query("query") query : String) : JokesVO


}