package com.fizz.notfakenews.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fizz.notfakenews.adapter.NotNewsAdapter
import com.fizz.notfakenews.databinding.FragmentTechnologyHomeBinding
import com.fizz.notfakenews.model.Article
import com.fizz.notfakenews.model.ArticleLocal
import com.fizz.notfakenews.overview.OverviewViewModel


class TechnologyHomeFragment : Fragment() {
    private var _binding: FragmentTechnologyHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentTechnologyHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TechnologyHomeFragment","Currently I am in TechnologyFragment")
        binding.recyclerView.layoutManager=LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
        viewModel.newsData.observe(viewLifecycleOwner){
            Log.d("TechnologyHomeFragment","Currently I am in TechnologyFragment and inside news data observer")
            binding.recyclerView.adapter=NotNewsAdapter(requireContext(),viewModel,it as ArrayList<ArticleLocal>)
        }
    }
}