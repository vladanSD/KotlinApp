package com.example.vladan.firstkotlinapp.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import com.example.vladan.firstkotlinapp.data.model.Recipe
import com.example.vladan.firstkotlinapp.data.model.Step

/**
 * Created by Vladan on 11.12.2017..
 */
interface RecipeService{

    @GET("v3/recipes/get-recipes")
    @Headers("Accept-Language: SRB")
    fun getRecipes(@Query("offset") offset: Int) : Call<RecipeResponse>

    class RecipeResponse(
            val recipes: List<Recipe>){
        companion object {
            fun settup(recipes: List<Recipe>) {
                for (recipe in recipes){
                    for(step in recipe.listOfSteps!!)
                    {
                        step.recipeId = recipe.id
                    }
                }
            }
        }
    }

}