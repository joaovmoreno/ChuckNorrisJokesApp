package com.joaomoreno.chucknorrisjokesapp.network

import com.joaomoreno.chucknorrisjokesapp.vo.CategoriesVO
import com.joaomoreno.chucknorrisjokesapp.vo.JokeVO
import com.joaomoreno.chucknorrisjokesapp.vo.JokesVO
import org.koin.dsl.module

val repositoryModule = module {
    factory { ChuckNorrisRepository(get(), get()) }
}

class ChuckNorrisRepository(
    private val chuckNorrisApi: ChuckNorrisJokeApi,
    private val responseHandler: ResponseHandler
) {

    suspend fun getRandomJokeByCategory(category: String): Resource<JokeVO> {
        return try {
            val response = chuckNorrisApi.getRandomJokeByCategory(category = category)
            return responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    suspend fun getCategories():
            Resource<CategoriesVO> {
        return try {
            val response = chuckNorrisApi.getCategories()
            val categories = CategoriesVO(response)
            return responseHandler.handleSuccess(categories)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    suspend fun getJokeByQuery(query: String): Resource<JokesVO> {
        return try {
            val response = chuckNorrisApi.getJokeByQuery(query = query)
            return responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

}