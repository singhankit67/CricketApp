package com.example.cricketapp

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabsdAdapter(private val myContext: Context, fm: FragmentManager, tabCount: Int) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                //  val homeFragment: HomeFragment = HomeFragment()
                TeamAFragment()
            }
            1 -> {
                TeamBFragment()
            }
            2 ->{
                BothTeamFragment()
            }
            // else -> null
            else -> return   BothTeamFragment()
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return 3
    }
}



