package com.example.vladan.firstkotlinapp.ui

import android.arch.paging.PagedListAdapter
import android.support.v7.recyclerview.extensions.DiffCallback
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.vladan.firstkotlinapp.data.model.Recipe
import com.example.vladan.firstkotlinapp.databinding.RecipeAdapterItemBinding

/**
 * Created by Vladan on 12.12.2017..
 */
class RecipeAdapter : PagedListAdapter<Recipe, RecipeAdapter.ViewHolder>(POST_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(RecipeAdapterItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe: Recipe? = getItem(position)
        holder.binding.recipe = recipe
    }

    companion object {
        val POST_COMPARATOR = object : DiffCallback<Recipe>() {
            override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
                     oldItem == newItem

            override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
                    oldItem.id == newItem.id
        }
    }

    class ViewHolder(binding: RecipeAdapterItemBinding) : RecyclerView.ViewHolder(binding.root){
        val binding = binding
    }
}
