package com.example.vladan.firstkotlinapp.data

import android.arch.paging.PagedList
import android.support.annotation.MainThread
import android.util.Log
import com.example.vladan.firstkotlinapp.data.api.RecipeService
import com.example.vladan.firstkotlinapp.data.model.Recipe
import com.example.vladan.firstkotlinapp.utils.PagingRequestHelper
import com.example.vladan.firstkotlinapp.utils.createStatusLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

/**
 * Created by Vladan on 11.12.2017..
 */
class RecipeBoundaryCallback(
        private val service: RecipeService,
        private val handleResponse: (RecipeService.RecipeResponse?) -> Unit,
        private val executor: Executor,
        private var offset: Int = 0
) : PagedList.BoundaryCallback<Recipe>() {

    val helper = PagingRequestHelper(executor)
    val networkState = helper.createStatusLiveData()

    @MainThread
    override fun onZeroItemsLoaded() {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) {
            service.getRecipes(offset).enqueue(createWebserviceCallback(it))
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: Recipe) {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) {
            service.getRecipes(itemAtEnd.indexInResponse).enqueue(createWebserviceCallback(it))
        }
    }

    private fun insertItemsIntoDb(
            response: Response<RecipeService.RecipeResponse>,
            it: PagingRequestHelper.Request.Callback) {
            executor.execute {
            handleResponse(response.body())
            it.recordSuccess()
        }
    }

    private fun createWebserviceCallback(it: PagingRequestHelper.Request.Callback)
            : Callback<RecipeService.RecipeResponse> {
        return object : Callback<RecipeService.RecipeResponse> {
            override fun onFailure(call: Call<RecipeService.RecipeResponse>?, t: Throwable) {
                it.recordFailure(t)
            }

            override fun onResponse(call: Call<RecipeService.RecipeResponse>?, response: Response<RecipeService.RecipeResponse>) {
                insertItemsIntoDb(response, it)
            }

        }
    }
}