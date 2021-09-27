package com.joaomoreno.chucknorrisjokesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joaomoreno.chucknorrisjokesapp.databinding.ItemChuckNorrisLastSearchedBinding
import com.joaomoreno.chucknorrisjokesapp.vo.JokeVO

class ChuckNorrisJokeLastSearchedAdapter(private var latestResearchedJokes: List<JokeVO>) :
    RecyclerView.Adapter<ChuckNorrisJokeLastSearchedAdapter.ChuckNorrisJokeLastSearchedViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChuckNorrisJokeLastSearchedViewHolder {
        val view: ItemChuckNorrisLastSearchedBinding = ItemChuckNorrisLastSearchedBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChuckNorrisJokeLastSearchedViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChuckNorrisJokeLastSearchedViewHolder, position: Int) {
        var jokeByPosition : JokeVO = latestResearchedJokes[position]
    }

    override fun getItemCount(): Int = latestResearchedJokes.size

    inner class ChuckNorrisJokeLastSearchedViewHolder(itemView: ItemChuckNorrisLastSearchedBinding) :
        RecyclerView.ViewHolder(itemView.root) {

    }
}