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
import com.fizz.notfakenews.overview.OverviewViewModel


class TechnologyHomeFragment : Fragment() {
    private var _binding: FragmentTechnologyHomeBinding? = null
    val binding get() = _binding!!
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

        binding.refreshTechnology.setOnRefreshListener {
            viewModel.deleteAllCategory("technology")
            viewModel.getNewsByCategory("technology")
            binding.refreshTechnology.isRefreshing = false
        }
        Log.d("TAG", "onViewCreated ")
        binding.recyclerView.layoutManager=LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)


        viewModel.getNewsByCategory("technology").observe(viewLifecycleOwner){
            Log.d("TechnologyFragment","Currently I am in technology and inside news data observer")
            // Save state
            val recyclerViewState = binding.recyclerView.layoutManager?.onSaveInstanceState()
            // Restore state
            binding.recyclerView.layoutManager?.onRestoreInstanceState(recyclerViewState)
            val adapter = NotNewsAdapter(requireContext(),viewModel)
            adapter.setDataset(it)
            binding.recyclerView.adapter = adapter
        }
    }

}