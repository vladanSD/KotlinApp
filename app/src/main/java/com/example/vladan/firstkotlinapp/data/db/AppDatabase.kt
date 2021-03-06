package com.example.vladan.firstkotlinapp.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.example.vladan.firstkotlinapp.data.model.Recipe
import com.example.vladan.firstkotlinapp.data.model.Step

/**
 * Created by Vladan on 11.12.2017..
 */
@Database(
        entities = arrayOf(Recipe::class, Step::class),
        version = 6,
        exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        fun getDatabase(context: Context) : AppDatabase =
                Room.databaseBuilder<AppDatabase>(context, AppDatabase::class.java, "db_recipes")
                        .fallbackToDestructiveMigration()
                        .build()
    }
    abstract fun getRecipeDao(): RecipeDao
}