package com.example.albertsonescodechallenge.presentationLayer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.albertsonescodechallenge.R
import com.example.albertsonescodechallenge.databinding.FragmentSearchPageBinding
import com.example.albertsonescodechallenge.presentationLayer.adapter.AcronymsAdapter

private const val TAG = "SearchPage"

/**
 * [Fragment] SearchPage
 * Main starting fragment
 * Contains a SearchView and a RecyclerView
 */
class SearchPage : BaseFragment() {

    // Instantiate adapter
    private val searchAdapter by lazy {
        AcronymsAdapter(
            mutableListOf()
        ) {
            acronymsViewModel.selectedAcronyms = it
            findNavController().navigate(R.id.action_search_page_to_details_page)
        }
    }

    // Create binding
    private lateinit var binding: FragmentSearchPageBinding

    /**
     * [Function] onCreateView
     * initialize the binding
     * call the function to update the layout
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate<FragmentSearchPageBinding?>(
            inflater,
            R.layout.fragment_search_page,
            container,
            false
        ).apply {
            this.lifecycleOwner = this@SearchPage
            this.viewModel = acronymsViewModel
        }
        binding.initialize()

        return binding.root

    }

    /**
     * [Function] initialize
     * Define logic to create view in fragment
     */
    private fun FragmentSearchPageBinding.initialize() {

        // Initialize recycler view
        this.acronymsListRecyclerview.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        }

        this.shortFormSearchview.isActivated = true
        this.shortFormSearchview.onActionViewExpanded()
        this.shortFormSearchview.clearFocus()
    }
}
