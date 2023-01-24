package com.fizz.notfakenews.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fizz.notfakenews.R
import com.fizz.notfakenews.adapter.ViewPagerAdapter
import com.fizz.notfakenews.databinding.FragmentHomeBinding
import com.fizz.notfakenews.overview.OverviewViewModel
import com.google.android.material.tabs.TabLayoutMediator

val TabArray = arrayOf(
    "BreakingNews",
    "General",
    "Technology",
    "Entertainment",
    "Health",
    "Sports",
    "Science",
    "Business"
)
val drawable= arrayOf(
    R.drawable.fake,
    R.drawable.generalicon,
    R.drawable.technologyicon,
    R.drawable.entertainmenticon,
    R.drawable.healthicon,
    R.drawable.sporticon,
    R.drawable.scienceicon,
    R.drawable.business
)

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNews()
        Log.d("HomeFragment","Currently I am in Home Fragment")
        val viewPager = binding.homeViewPager
        val tabLayout = binding.tabMode

        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = TabArray[position]
            tab.setIcon(drawable[position])
        }.attach()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}