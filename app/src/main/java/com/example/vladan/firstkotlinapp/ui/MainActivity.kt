package com.example.vladan.firstkotlinapp.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.vladan.firstkotlinapp.R
import com.example.vladan.firstkotlinapp.ServiceLocator

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = RecipeAdapter()
        val recycler: RecyclerView = findViewById(R.id.recyclerview_recepti)

        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this )
        recycler.isNestedScrollingEnabled = false


        ServiceLocator.instance(applicationContext).getRepository().postsOfSubreddit(50).pagedList.observe(this, Observer { t ->
            adapter.setList(t) })
    }
}
