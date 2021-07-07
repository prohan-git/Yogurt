package com.example.prohan.yogurt.repository

import android.os.Looper
import android.util.Log

class DayWordRepository {

    fun getDayWord() {

    }

    fun getDayWordFromNetWork() {

    }

    fun getDayWordFromDB() {

    }


    companion object {
        @Volatile
        private var instance: DayWordRepository? = null

        fun getInstance(): DayWordRepository {
            return instance ?: synchronized(this) {
                instance ?: DayWordRepository()
            }.also { instance = it }
        }

    }

}



