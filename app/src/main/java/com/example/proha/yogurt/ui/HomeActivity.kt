package com.example.proha.yogurt.ui

import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.LayoutInflater

import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.v4.view.ViewPager
import android.widget.TextView
import com.example.proha.yogurt.R
import com.example.proha.yogurt.ui.home.MainFragment
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity(){

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.navigation_home -> {
                container.setCurrentItem(0, false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                container.setCurrentItem(1, false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                container.setCurrentItem(2, false)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)//设置viewpager的adapter
        container.adapter = mSectionsPagerAdapter
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        container.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                navigation.selectedItemId = when (position) {
                    0 -> R.id.navigation_home
                    1 -> R.id.navigation_dashboard
                    2 -> R.id.navigation_notifications
                    else -> 0
                }

            }
        })
    }

    /**
     * 添加角标
     */
    fun addBadge(index: Int, string: String) {
        val bottomNavigationMenuView: BottomNavigationMenuView = navigation.getChildAt(0) as BottomNavigationMenuView
        val itemView = bottomNavigationMenuView.getChildAt(index) as BottomNavigationItemView
        val badge = LayoutInflater.from(this)
                .inflate(R.layout.badge, bottomNavigationMenuView, false)
        findViewById<TextView>(R.id.msg).text = string

        itemView.addView(badge)
    }

    /**
     * 删除角标
     */
    fun removeBadge(navigationView: BottomNavigationView, index: Int) {
        val bottomNavigationMenuView = navigationView.getChildAt(0) as BottomNavigationMenuView
        val v = bottomNavigationMenuView.getChildAt(index)
        val itemView = v as BottomNavigationItemView
        itemView.removeViewAt(itemView.childCount - 1)
    }

    /**
     * viewPagerAdapter类
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {

            return when (position) {
                0 -> MainFragment.newInstance()
                1 -> MainFragment.newInstance()
                2 -> MainFragment.newInstance()
                else -> MainFragment.newInstance()
            }


        }

        override fun getCount(): Int {
            return 3
        }


    }


}
