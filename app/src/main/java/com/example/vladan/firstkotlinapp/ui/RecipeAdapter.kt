package com.example.vladan.firstkotlinapp.ui

import android.arch.paging.PagedListAdapter
import android.support.v7.recyclerview.extensions.DiffCallback
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.vladan.firstkotlinapp.R
import com.example.vladan.firstkotlinapp.data.model.Recipe

class RecipeAdapter: PagedListAdapter<Recipe, RecipeAdapter.ViewHolder>(POST_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_adapter_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe: Recipe? = getItem(position)
        holder.bind(recipe?.title, recipe?.imageFileName)
    }

    companion object {
        val POST_COMPARATOR = object : DiffCallback<Recipe>() {
            override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
                    oldItem == newItem

            override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
                    oldItem.id == newItem.id
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tv_name)
        val imageView: ImageView = view.findViewById(R.id.iv_recipe)

        fun bind(name: String?, img: String?) {
            textView.text = name
            Glide.with(itemView.context).load(img).into(imageView)
        }
    }
}
