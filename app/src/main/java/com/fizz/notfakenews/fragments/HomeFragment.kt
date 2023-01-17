package com.fizz.notfakenews.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fizz.notfakenews.adapter.NotNewsAdapter
import com.fizz.notfakenews.databinding.FragmentHomeBinding
import com.fizz.notfakenews.model.Article
import com.fizz.notfakenews.overview.OverviewViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel:OverviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        Log.d("list", viewModel.newsData.toString())
        viewModel.newsData.observe(viewLifecycleOwner) {
            binding.recyclerView.adapter =
                NotNewsAdapter(requireContext(), viewModel, it as ArrayList<Article>)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}