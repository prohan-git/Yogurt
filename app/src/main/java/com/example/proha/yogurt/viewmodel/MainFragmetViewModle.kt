package com.example.proha.yogurt.viewmodel;

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.MutableLiveData
import com.example.proha.yogurt.db.AppDatabase
import com.example.proha.yogurt.db.CommonRepository


class MainFragmetViewModle(ommonRepository: CommonRepository) : ViewModel() {
    var wordOfTheDay: LiveData<String> = MutableLiveData<String>()

    init {
        wordOfTheDay.value
    }

    val gardenPlantings = ommonRepository.getGardenPlantings()

//    val plantAndGardenPlantings: LiveData<List<CommonRepository>> =
//            Transformations.map(ommonRepository.getPlantAndGardenPlantings()) {
//                it.filter { it.gardenPlantings.isNotEmpty() }
//            }
}



