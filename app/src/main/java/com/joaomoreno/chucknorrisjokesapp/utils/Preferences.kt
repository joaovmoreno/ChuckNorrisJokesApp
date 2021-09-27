package com.joaomoreno.chucknorrisjokesapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.joaomoreno.chucknorrisjokesapp.network.Resource
import com.joaomoreno.chucknorrisjokesapp.vo.CategoriesVO
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val prefModule = module {
    single { Preferences(androidContext()) }
}

class Preferences(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    private val gsonInstance : Gson by lazy { GsonBuilder().create()}
    companion object{
        private const val CATEGORIES_KEY = "CATEGORIES_KEY"
        private const val JOKES_KEY = "JOKES_KEY"
    }

    fun storeCategoriesInCache(categories: Resource<CategoriesVO>) {
        val jokes: String = gsonInstance.toJson(categories)
        preferences.edit().putString(CATEGORIES_KEY, jokes).apply()
    }

    fun getCategoriesInCache(): CategoriesVO? {
        val categories =  preferences.getString(CATEGORIES_KEY, "")
        return if (categories.isNullOrEmpty()){
            null
        } else {
            val json = Gson().toJson(categories)
            return gsonInstance.fromJson(json, CategoriesVO::class.java)
//            val collectionType: Type = object : TypeToken<Collection<CategoriesVO?>?>() {}.type
//            return Gson().fromJson(json, collectionType) as CategoriesVO
        }

    }


}