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
import com.fizz.notfakenews.databinding.FragmentSavedBinding
import com.fizz.notfakenews.model.ArticleLocal
import com.fizz.notfakenews.overview.OverviewViewModel

class SavedFragment : Fragment() {

    private var _binding: FragmentSavedBinding? = null
    val binding get() = _binding!!
    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentSavedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
        viewModel.readBookmarks().observe(viewLifecycleOwner){
            Log.d("Saved","Currently I am in Saved and inside news data observer")
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