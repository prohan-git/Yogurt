package com.example.proha.yogurt.ui.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.proha.yogurt.R
import com.example.proha.yogurt.viewmodel.MainFragmetViewModle
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        subscribeUi()
        return view
    }

    private fun subscribeUi() {

        val viewModel = ViewModelProviders.of(this).get(MainFragmetViewModle::class.java)
        viewModel.wordOfTheDay.observe(this, Observer { result ->
            if (result != null && result.isNotEmpty()) {
                word_of_the_day.text = result
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }


}

