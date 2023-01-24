package com.fizz.notfakenews.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fizz.notfakenews.fragments.*

private const val NUM_TABS = 8

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TopUSFragment()
            1 -> GeneralHomeFragment()
            2 -> TechnologyHomeFragment()
            3 -> EntertainmentHomeFragment()
            4 -> HealthHomeFragment()
            5 -> SportsHomeFragment()
            6 -> ScienceHomeFragment()
            else -> BusinessHomeFragment()
        }
    }
}