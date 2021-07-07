/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
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
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import android.support.annotation.VisibleForTesting
import androidx.work.WorkManager

import com.example.prohan.yogurt.db.converter.DateConverter
import com.example.prohan.yogurt.db.dao.CommentDao
import com.example.prohan.yogurt.db.entity.CommentEntity

@Database(entities = [(CommentEntity::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun commentDao(): CommentDao

    companion object {
        @Volatile
        private var sInstance: AppDatabase? = null

        @VisibleForTesting
        val DATABASE_NAME = "basic-sample-db"


        fun getInstance(context: Context): AppDatabase {
            return sInstance ?: synchronized(this) {
                sInstance ?: buildDatabase(context).also { sInstance = it }
            }
        }


        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
//                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
//                            WorkManager.getInstance()?.enqueue(request)
                        }
                    })
                    .build()
        }
    }
}
