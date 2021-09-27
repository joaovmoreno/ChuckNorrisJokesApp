package com.joaomoreno.chucknorrisjokesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joaomoreno.chucknorrisjokesapp.databinding.ItemChuckNorrisJokeBinding
import com.joaomoreno.chucknorrisjokesapp.vo.JokeVO

class ChuckNorrisJokeAdapter(
    private var listJokes: MutableList<JokeVO?>,
    val shareListener: ((String) -> Unit)
) :
    RecyclerView.Adapter<ChuckNorrisJokeAdapter.ChuckNorrisJokeViewHolder>() {

    private lateinit var binding: ItemChuckNorrisJokeBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChuckNorrisJokeViewHolder {
        binding = ItemChuckNorrisJokeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChuckNorrisJokeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChuckNorrisJokeViewHolder, position: Int) {
        val jokeByPosition: JokeVO? = listJokes[position]
        jokeByPosition?.let { joke ->
            joke.value?.let { jokeValue ->
                binding.labelJoke.text = jokeValue
                if (jokeValue.length >= 80) {
                    binding.labelJoke.textSize = 14F
                } else {
                    binding.labelJoke.textSize = 16F
                }
            }
            if (joke.categories.isNullOrEmpty()) {
                binding.labelCategory.text = "UNCATEGORIZED"
            } else {
                joke.categories?.let { categories ->
                    binding.labelCategory.text = categories[0].uppercase()
                }

            }
            joke.url?.let { url -> binding.imageView.setOnClickListener { shareListener.invoke(url) } }


        }

    }

    override fun getItemCount(): Int = listJokes.size

    inner class ChuckNorrisJokeViewHolder(itemView: ItemChuckNorrisJokeBinding) :
        RecyclerView.ViewHolder(itemView.root)
}