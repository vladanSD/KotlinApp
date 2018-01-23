package com.example.vladan.firstkotlinapp.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.vladan.firstkotlinapp.data.model.Step

/**
 * Created by Vladan on 19.12.2017..
 */
@Dao
interface StepDao {

    @Query ("select * from Step where id=:id")
    fun getRecipes(id: Int): LiveData<List<Step>>
}