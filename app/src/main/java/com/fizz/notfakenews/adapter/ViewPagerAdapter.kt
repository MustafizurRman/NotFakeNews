package com.fizz.notfakenews.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fizz.notfakenews.fragments.*

private const val NUM_TABS = 7

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TechnologyHomeFragment()
            1 -> EntertainmentHomeFragment()
            3 -> GeneralHomeFragment()
            4 -> HealthHomeFragment()
            5 -> SportsHomeFragment()
            6 -> ScienceHomeFragment()
            else -> BusinessHomeFragment()
        }
    }
}