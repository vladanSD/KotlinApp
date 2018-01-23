package com.example.vladan.firstkotlinapp.data.api

import android.util.Log
import com.example.vladan.firstkotlinapp.data.model.Recipe
import com.example.vladan.firstkotlinapp.utils.RecipeDeserializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Vladan on 11.12.2017..
 */
class ApiFactory {

    companion object {
        private const val BASE_URL = "http://46.101.154.198:8080/"
        fun create(): RecipeService = create(HttpUrl.parse(BASE_URL)!!)
        fun create(httpUrl: HttpUrl): RecipeService {
            val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                Log.d("api", it)
            })
            logger.level = HttpLoggingInterceptor.Level.BODY

            val gson = GsonBuilder()
                    .serializeNulls()
                    .registerTypeAdapter(Recipe::class.java, RecipeDeserializer() )
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .create()

            val client = OkHttpClient.Builder()
                    .addInterceptor(logger)
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