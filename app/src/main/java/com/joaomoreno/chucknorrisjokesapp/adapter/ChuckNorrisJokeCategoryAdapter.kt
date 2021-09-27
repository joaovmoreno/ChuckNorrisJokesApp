package com.joaomoreno.chucknorrisjokesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joaomoreno.chucknorrisjokesapp.databinding.ItemChuckNorrisJokeCategoryBinding
import com.joaomoreno.chucknorrisjokesapp.vo.CategoriesVO

class ChuckNorrisJokeCategoryAdapter(private var listCategoryJoke : CategoriesVO, val clickCategory : ((String) -> Unit)) :
    RecyclerView.Adapter<ChuckNorrisJokeCategoryAdapter.ChuckNorrisJokeCategoryViewHolder>() {

    lateinit var binding: ItemChuckNorrisJokeCategoryBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChuckNorrisJokeCategoryViewHolder {
        binding = ItemChuckNorrisJokeCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChuckNorrisJokeCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChuckNorrisJokeCategoryViewHolder, position: Int) {
        val categoryJokeByPosition : String = listCategoryJoke.categories[position]
        binding.labelCategoryName.text = categoryJokeByPosition
        binding.clContent.setOnClickListener { clickCategory.invoke(categoryJokeByPosition) }
    }

    override fun getItemCount(): Int = 8

    inner class ChuckNorrisJokeCategoryViewHolder(itemView: ItemChuckNorrisJokeCategoryBinding) :
        RecyclerView.ViewHolder(itemView.root)

}