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

package com.example.proha.yogurt.db

import com.example.proha.yogurt.db.dao.CommentDao
import com.example.proha.yogurt.db.entity.CommentEntity
import com.example.proha.yogurt.utils.runOnIoThread


class CommonRepository private constructor(
        private val commentDao: CommentDao
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
                    instance ?: CommonRepository(commentDao).also { instance = it }
                }
    }
}