package com.example.proha.yogurt.viewmodel;

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.MutableLiveData


class MainFragmetViewModle : ViewModel() {
    var wordOfTheDay: LiveData<String> = MutableLiveData<String>()


//    val plantAndGardenPlantings: LiveData<List<CommonRepository>> =
//            Transformations.map(ommonRepository.getPlantAndGardenPlantings()) {
//                it.filter { it.gardenPlantings.isNotEmpty() }
//            }
}



