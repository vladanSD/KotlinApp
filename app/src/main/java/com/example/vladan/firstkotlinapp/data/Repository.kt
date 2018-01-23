package com.example.vladan.firstkotlinapp.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import com.example.vladan.firstkotlinapp.data.api.RecipeService
import com.example.vladan.firstkotlinapp.data.db.AppDatabase
import com.example.vladan.firstkotlinapp.data.model.Recipe
import com.example.vladan.firstkotlinapp.utils.Listing
import com.example.vladan.firstkotlinapp.utils.NetworkState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

/**
 * Created by Vladan on 11.12.2017..
 */
class Repository(
        val db: AppDatabase,
        private val service: RecipeService,
        private val executor: Executor,
        private val networkPageSize: Int,
        private val startingPoint: Int = 0
)  {

    private fun refresh(): LiveData<NetworkState> {
        val networkState = MutableLiveData<NetworkState>()
        networkState.value = NetworkState.LOADING
        service.getRecipes(startingPoint).enqueue(
                object : Callback<RecipeService.RecipeResponse> {

                    override fun onFailure(call: Call<RecipeService.RecipeResponse>?, t: Throwable) {
                        networkState.value = NetworkState.error(t.message)
                    }

                    override fun onResponse(call: Call<RecipeService.RecipeResponse>?, response: Response<RecipeService.RecipeResponse>) {
                        executor.execute {
                            db.runInTransaction {
                                insertResultIntoDb(response.body())
                            }
                            networkState.value = NetworkState.LOADED
                        }
                    }
                }
        )
        return networkState
    }

    private fun insertResultIntoDb(body: RecipeService.RecipeResponse?) {
        body!!.recipes.let { recipes ->
            db.runInTransaction {
                val start = db.getRecipeDao().getNextIndexInSubreddit()
                val items = recipes.mapIndexed { index, recipe ->
                    recipe.indexInResponse = start + index
                    recipe
                }
                db.getRecipeDao().insert(items)
            }
        }
    }

     fun postsOfSubreddit(pageSize: Int): Listing<Recipe> {
        val boundaryCallback = RecipeBoundaryCallback(
                service = service,
                handleResponse = this::insertResultIntoDb,
                executor = executor,
                offset = startingPoint
        )
        val dataSourceFactory = db.getRecipeDao().getAllRecipes()
        val builder = LivePagedListBuilder(dataSourceFactory, pageSize)
                .setBoundaryCallback(boundaryCallback)

        val refreshTrigger = MutableLiveData<Unit>()
        val refreshState = Transformations.switchMap(refreshTrigger, {
            refresh()
        })

        return Listing(
                pagedList = builder.build(),
                networkState = boundaryCallback.networkState,
                retry = {
                    boundaryCallback.helper.retryAllFailed()
                },
                refresh = { refreshTrigger.value = null },
                refreshState = refreshState
        )
    }

}