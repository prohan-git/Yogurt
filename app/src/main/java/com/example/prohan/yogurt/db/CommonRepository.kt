/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.prohan.yogurt.db

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.support.annotation.MainThread
import com.android.example.paging.pagingwithnetwork.reddit.api.RedditApi
import com.example.prohan.yogurt.db.dao.CommentDao
import com.example.prohan.yogurt.db.entity.CommentEntity
import com.example.prohan.yogurt.net.NetworkState
import com.example.prohan.yogurt.utils.runOnIoThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CommonRepository private constructor(
        private val commentDao: CommentDao,
        val networkPageSize: Int
) {

    fun createGardenPlanting(plantId: String) {
        runOnIoThread {
            val gardenPlanting = CommentEntity()
            commentDao.insertGardenPlanting(gardenPlanting)
        }
    }

    fun getGardenPlantingForPlant(plantId: String) =
            commentDao.getGardenPlantingForPlant(plantId)

    fun getGardenPlantings() = commentDao.getGardenPlantings()

    fun getPlantAndGardenPlantings() = commentDao.getPlantAndGardenPlantings()

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: CommonRepository? = null

        fun getInstance(commentDao: CommentDao) =
                instance ?: synchronized(this) {
                    instance ?: CommonRepository(commentDao, 1).also { instance = it }
                }
    }


    /**
     * When refresh is called, we simply run a fresh network request and when it arrives, clear
     * the database table and insert all new items in a transaction.
     * <p>
     * Since the PagedList already uses a database bound data source, it will automatically be
     * updated after the database transaction is finished.
     */
    @MainThread
    private fun refresh(subredditName: String): LiveData<NetworkState> {
        val networkState = MutableLiveData<NetworkState>()
        networkState.value = NetworkState.LOADING
        RedditApi.create().getTop(subredditName, networkPageSize).enqueue(
                object : Callback<RedditApi.ListingResponse> {
                    override fun onFailure(call: Call<RedditApi.ListingResponse>, t: Throwable) {
                        // retrofit calls this on main thread so safe to call set value
                        networkState.value = NetworkState.error(t.message)
                    }

                    override fun onResponse(
                            call: Call<RedditApi.ListingResponse>,
                            response: Response<RedditApi.ListingResponse>) {
                        runOnIoThread {
//                            db.runInTransaction {
//                                db.posts().deleteBySubreddit(subredditName)
//                                insertResultIntoDb(subredditName, response.body())
//                            }
                            // since we are in bg thread now, post the result.
                            networkState.postValue(NetworkState.LOADED)
                        }
                    }
                }
        )
        return networkState
    }


}