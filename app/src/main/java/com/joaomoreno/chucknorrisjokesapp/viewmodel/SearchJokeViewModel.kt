package com.joaomoreno.chucknorrisjokesapp.viewmodel

import androidx.lifecycle.*
import com.joaomoreno.chucknorrisjokesapp.network.ChuckNorrisRepository
import com.joaomoreno.chucknorrisjokesapp.network.Resource
import com.joaomoreno.chucknorrisjokesapp.utils.Preferences
import com.joaomoreno.chucknorrisjokesapp.vo.CategoriesVO
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchViewModelModule = module {
    viewModel { SearchJokeViewModel(get(),get()) }
}
class SearchJokeViewModel(private val repository: ChuckNorrisRepository,private val  cachePreferences : Preferences): ViewModel(){

    private val category = MutableLiveData<String>()
    private val query = MutableLiveData<String>()

    var categoriesResponse : LiveData<Resource<CategoriesVO>> =
        liveData {
                emit(repository.getCategories())
                emit(Resource.loading(null))
                saveCacheCategories()
        }

    var categoriesCache : LiveData<CategoriesVO> = liveData {
        if (cachePreferences.getCategoriesInCache() != null ){
            cachePreferences.getCategoriesInCache()?.let { emit(it) }
        }
    }

    var jokeByCategory = category.switchMap { category ->
        liveData {
            emit(Resource.loading(null))
            emit(repository.getRandomJokeByCategory(category))
        }
    }

    var jokeByQuery = query.switchMap { query ->
        liveData {
            emit(Resource.loading(null))
            emit(repository.getJokeByQuery(query))
        }
    }

    fun getCategory(input: String) {
        category.value = input
    }

    fun getQueryForSearchJoke(input : String){
        query.value = input
    }

    private fun saveCacheCategories() = categoriesResponse.value?.let { cachePreferences.storeCategoriesInCache(it) }

}