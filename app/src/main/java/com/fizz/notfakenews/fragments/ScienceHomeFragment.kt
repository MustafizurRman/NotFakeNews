package com.fizz.notfakenews.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fizz.notfakenews.R
import com.fizz.notfakenews.adapter.NotNewsAdapter
import com.fizz.notfakenews.databinding.FragmentScienceHomeBinding
import com.fizz.notfakenews.databinding.FragmentSportsHomeBinding
import com.fizz.notfakenews.overview.OverviewViewModel

class ScienceHomeFragment : Fragment() {

    private var _binding: FragmentScienceHomeBinding? = null
    val binding get() = _binding!!
    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentScienceHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.refreshScience.setOnRefreshListener {
            viewModel.deleteAllCategory("science")
            viewModel.getNewsByCategory("science")
            binding.refreshScience.isRefreshing = false
        }

        Log.d("SportsFragment","Currently I am in SportsFragment")
        binding.recyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
        viewModel.getNewsByCategory("science").observe(viewLifecycleOwner){
            val recyclerViewState = binding.recyclerView.layoutManager?.onSaveInstanceState()
            // Restore state
            binding.recyclerView.layoutManager?.onRestoreInstanceState(recyclerViewState)
            val adapter = NotNewsAdapter(requireContext(),viewModel)
            adapter.setDataset(it)
            binding.recyclerView.adapter = adapter
        }

    }
}