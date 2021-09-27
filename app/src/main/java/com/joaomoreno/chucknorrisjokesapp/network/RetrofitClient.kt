package com.joaomoreno.chucknorrisjokesapp.network

import com.joaomoreno.chucknorrisjokesapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { provideOkHttpClient() }
    factory { provideChuckNorrisApi(get()) }
    single { provideRetrofit(get()) }
    factory { ResponseHandler() }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(): OkHttpClient {
    val loggingHttp = HttpLoggingInterceptor()
    loggingHttp.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient().newBuilder().addInterceptor(loggingHttp).build()
}

fun provideChuckNorrisApi(retrofit: Retrofit): ChuckNorrisJokeApi = retrofit.create(ChuckNorrisJokeApi::class.java)
