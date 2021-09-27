package com.joaomoreno.chucknorrisjokesapp.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.joaomoreno.chucknorrisjokesapp.R
import com.joaomoreno.chucknorrisjokesapp.adapter.ChuckNorrisJokeAdapter
import com.joaomoreno.chucknorrisjokesapp.databinding.ActivityJokeBinding
import com.joaomoreno.chucknorrisjokesapp.viewmodel.JokeViewModel
import com.joaomoreno.chucknorrisjokesapp.vo.JokeVO
import org.koin.androidx.viewmodel.ext.android.viewModel

class JokeActivity : AppCompatActivity() {

    private val jokeViewModel: JokeViewModel by viewModel()

    private lateinit var binding: ActivityJokeBinding


    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data.let {
                    var jokes =
                        it?.getParcelableArrayListExtra<JokeVO>(JOKE_RESULT) as MutableList<JokeVO?>
                    hideEmptyState()
                    jokeViewModel.jokes.postValue(jokes)

                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_joke)
        initView()
    }

    private fun initView() {
        setupToolbar()
        setupRecyclerJoke()

    }

    private fun setupToolbar() = binding.toolbar.setOnMenuItemClickListener {
        when (it.itemId) {
            R.id.menu_search -> {
                startForResult.launch(Intent(this, SearchJokeActivity::class.java))
                true
            }
            else -> false
        }
    }

    private fun setupRecyclerJoke() {
        jokeViewModel.jokes.observe(this, {
            binding.rvJokes.adapter = ChuckNorrisJokeAdapter(it) {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, it)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
//            binding.notifyChange()
        })

    }

    private fun hideEmptyState() {
        binding.layoutJokeEmpty.visibility = View.GONE
        binding.rvJokes.visibility = View.VISIBLE
    }

    companion object {
        const val JOKE_RESULT: String = "JOKE_RESULT"
    }


}