package com.example.vladan.firstkotlinapp.data.db

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.vladan.firstkotlinapp.data.model.Recipe

/**
 * Created by Vladan on 11.12.2017..
 */
@Dao
interface RecipeDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<Recipe>)

    @Query("select * from recipes order by indexInResponse ASC")
    fun getAllRecipes() : DataSource.Factory<Int, Recipe>

    @Query("SELECT MAX(indexInResponse) + 1 FROM recipes")
    fun getNextIndexInSubreddit() : Int
}