package com.joaomoreno.chucknorrisjokesapp.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.joaomoreno.chucknorrisjokesapp.R
import com.joaomoreno.chucknorrisjokesapp.adapter.ChuckNorrisJokeCategoryAdapter
import com.joaomoreno.chucknorrisjokesapp.databinding.ActivitySearchJokeBinding
import com.joaomoreno.chucknorrisjokesapp.viewmodel.SearchJokeViewModel
import com.joaomoreno.chucknorrisjokesapp.vo.JokeVO
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchJokeActivity : AppCompatActivity() {

    private val searchJokeViewModel: SearchJokeViewModel by viewModel()

    private lateinit var binding: ActivitySearchJokeBinding

    private var listOfResearchJoke = mutableListOf<JokeVO>()

    private var getCategoriesFromResponse = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_joke)
        initView()
    }

    private fun initView() {
        setupToolbar()
        setupRecyclerCategories()
        getJokeByCategory()
        getInputJokeQueryForResearch()
        getJokeByQuery()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }

    private fun setupRecyclerCategories() {
        searchJokeViewModel.categoriesCache.observe(this, {
            binding.rvCategories.adapter = ChuckNorrisJokeCategoryAdapter(
                it,
                clickCategory = { category -> searchJokeViewModel.getCategory(category) })
            getCategoriesFromResponse = false
        })
        if (getCategoriesFromResponse) {
            searchJokeViewModel.categoriesResponse.observe(this, {
                binding.rvCategories.adapter = it.data?.let { categories ->
                    ChuckNorrisJokeCategoryAdapter(
                        categories,
                        clickCategory = { category -> searchJokeViewModel.getCategory(category) })
                }
                it.message?.let { errorMessage ->
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    private fun getJokeByCategory() {

        searchJokeViewModel.jokeByCategory.observe(this, {
            it.data?.let { joke ->
                listOfResearchJoke.add(joke)
                setResult(Activity.RESULT_OK, Intent().apply {
                    putParcelableArrayListExtra(JOKE_RESULT, ArrayList(listOfResearchJoke))

                })
                finish()
            }
        })

    }

    private fun getInputJokeQueryForResearch() {
        var queryForSearch: String = ""
        binding.etSearchJoke.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editable: Editable) {
                queryForSearch = editable.toString()
            }

        })

        binding.etSearchJoke.setOnEditorActionListener { _, actionId, _ ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                searchJokeViewModel.getQueryForSearchJoke(queryForSearch)
                handled = true
            }
            handled
        }
    }

    private fun getJokeByQuery() {
        searchJokeViewModel.jokeByQuery.observe(this, {
            it.data?.let { jokes ->
                listOfResearchJoke.addAll(jokes.result)
                setResult(Activity.RESULT_OK, Intent().apply {
                    putParcelableArrayListExtra(JOKE_RESULT, ArrayList(listOfResearchJoke))
                })
                finish()
            }
        })
    }

    companion object {
        const val JOKE_RESULT: String = "JOKE_RESULT"
    }

}