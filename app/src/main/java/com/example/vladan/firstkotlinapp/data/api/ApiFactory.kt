package com.example.vladan.firstkotlinapp.data.api

import com.example.vladan.firstkotlinapp.data.model.Recipe
import com.example.vladan.firstkotlinapp.utils.RecipeDeserializer
import com.google.gson.GsonBuilder
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiFactory {

    companion object {
        private const val BASE_URL = "http://46.101.154.198:8080/"
        fun create(): RecipeService = create(HttpUrl.parse(BASE_URL)!!)
        fun create(httpUrl: HttpUrl): RecipeService {

            val gson = GsonBuilder()
                    .serializeNulls()
                    .registerTypeAdapter(Recipe::class.java, RecipeDeserializer())
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .create()

            val client = OkHttpClient.Builder()
                    .build()

            return Retrofit.Builder()
                    .baseUrl(httpUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(RecipeService::class.java)
        }
    }
}