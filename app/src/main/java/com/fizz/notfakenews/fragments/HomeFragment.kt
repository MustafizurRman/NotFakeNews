package com.fizz.notfakenews.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fizz.notfakenews.adapter.ViewPagerAdapter
import com.fizz.notfakenews.databinding.FragmentHomeBinding
import com.fizz.notfakenews.overview.OverviewViewModel
import com.google.android.material.tabs.TabLayoutMediator

val TabArray = arrayOf(
    "Technology",
    "Entertainment",
    "General",
    "Health",
    "Sports",
    "Science",
    "Business"
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
        binding.refreshHome.setOnRefreshListener {

            binding.refreshHome.isRefreshing = false
        }
        Log.d("HomeFragment","Currently I am in Home Fragmment")
        val viewPager = binding.homeViewPager
        val tabLayout = binding.tabMode

        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = TabArray[position]
        }.attach()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}