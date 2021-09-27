package com.joaomoreno.chucknorrisjokesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joaomoreno.chucknorrisjokesapp.vo.JokeVO
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var jokeViewModel = module {
    viewModel { JokeViewModel() }
}
class JokeViewModel : ViewModel() {

    var jokes : MutableLiveData<MutableList<JokeVO?>> = MutableLiveData()
}