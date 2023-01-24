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
import com.fizz.notfakenews.databinding.FragmentSportsHomeBinding
import com.fizz.notfakenews.databinding.FragmentTopUSBinding
import com.fizz.notfakenews.overview.OverviewViewModel

class TopUSFragment : Fragment() {

    private var _binding: FragmentTopUSBinding? = null
    val binding get() = _binding!!
    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentTopUSBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.refreshTopUS.setOnRefreshListener {
            viewModel.deleteAllCategory("")
            viewModel.getNewsByCategory("")
            binding.refreshTopUS.isRefreshing = false
        }

        Log.d("TopNews","Currently I am in TopNews")
        binding.recyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)

        viewModel.getNewsByCategory("").observe(viewLifecycleOwner){
            Log.d("TopNews","Currently I am in TopNews and inside news data observer")
            val recyclerViewState = binding.recyclerView.layoutManager?.onSaveInstanceState()
            // Restore state
            binding.recyclerView.layoutManager?.onRestoreInstanceState(recyclerViewState)
            val adapter = NotNewsAdapter(requireContext(),viewModel)
            adapter.setDataset(it)
            binding.recyclerView.adapter = adapter
        }
    }

}