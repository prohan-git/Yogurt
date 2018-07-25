package com.example.proha.yogurt.viewmodel;

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.MutableLiveData


class MainFragmetViewModle : ViewModel() {

    var wordOfTheDay: LiveData<String> = MutableLiveData<String>()
//
//    val gardenPlantings = gardenPlantingRepository.getGardenPlantings()
//
//    val plantAndGardenPlantings: LiveData<List<PlantAndGardenPlantings>> =
//            Transformations.map(gardenPlantingRepository.getPlantAndGardenPlantings()) {
//                it.filter { it.gardenPlantings.isNotEmpty() }
//            }
}
