package com.example.prohan.yogurt.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.MutableLiveData


class MainFragmetViewModle : ViewModel() {

    var wordOfTheDay: LiveData<String> = MutableLiveData<String>()
}



