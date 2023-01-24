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
import com.fizz.notfakenews.databinding.FragmentBusinessHomeBinding
import com.fizz.notfakenews.overview.OverviewViewModel

class BusinessHomeFragment : Fragment() {

    private var _binding: FragmentBusinessHomeBinding? = null
    val binding get() = _binding!!
    private val viewModel: OverviewViewModel by viewModels()
    lateinit var adapter: NotNewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBusinessHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.refreshBusiness.setOnRefreshListener {
            viewModel.deleteAllCategory("business")
            viewModel.getNewsByCategory("business")
            binding.refreshBusiness.isRefreshing = false
        }

        Log.d("BusinessHomeFragment", "Currently I am in BusinessFragment")
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)

        viewModel.getNewsByCategory("business").observe(viewLifecycleOwner) {
            val recyclerViewState = binding.recyclerView.layoutManager?.onSaveInstanceState()
            // Restore state
            binding.recyclerView.layoutManager?.onRestoreInstanceState(recyclerViewState)
            val adapter = NotNewsAdapter(requireContext(), viewModel)
            adapter.setDataset(it)
            binding.recyclerView.adapter = adapter
        }
    }


/*        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText != null) {
                    adapter?.performSearch(newText,business)
                    return true
                }
            }
        }*/
}
