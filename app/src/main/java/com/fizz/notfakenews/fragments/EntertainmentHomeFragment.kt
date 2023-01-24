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
import com.fizz.notfakenews.databinding.FragmentEntertainmentHomeBinding
import com.fizz.notfakenews.model.ArticleLocal
import com.fizz.notfakenews.overview.OverviewViewModel

private const val TAGE="EntertainFragment"
class EntertainmentHomeFragment : Fragment() {

    private var _binding: FragmentEntertainmentHomeBinding? = null
    val binding get() = _binding!!
    private val viewModel: OverviewViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentEntertainmentHomeBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAGE,"View Create Fragment")

        binding.refreshEntertainment.setOnRefreshListener {
            viewModel.deleteAllCategory("entertainment")
            viewModel.getNewsByCategory("entertainment")
            binding.refreshEntertainment.isRefreshing = false
        }

        binding.recyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)

        viewModel.getNewsByCategory("entertainment").observe(viewLifecycleOwner){
            Log.d("SportsFragment","Currently I am in SportsFragment and inside news data observer")
            val recyclerViewState = binding.recyclerView.layoutManager?.onSaveInstanceState()
            // Restore state
            binding.recyclerView.layoutManager?.onRestoreInstanceState(recyclerViewState)
            val adapter = NotNewsAdapter(requireContext(),viewModel)
            adapter.setDataset(it)
            binding.recyclerView.adapter = adapter
            adapter.setDataset(it)
            binding.recyclerView.adapter = adapter
        }
    }

}