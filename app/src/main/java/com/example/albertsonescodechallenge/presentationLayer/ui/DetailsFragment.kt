package com.example.albertsonescodechallenge.presentationLayer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.albertsonescodechallenge.R
import com.example.albertsonescodechallenge.databinding.FragmentDetailsBinding
import com.example.albertsonescodechallenge.presentationLayer.adapter.AcronymsAdapter

private const val TAG = "DetailsFragment"

/**
 * [Fragment] Details Fragment
 * Details view for each Acronym variant
 */
class DetailsFragment : BaseFragment() {

    // Instantiate adapter
    private val varAdapter by lazy {
        AcronymsAdapter(mutableListOf())
    }

    // Create binding
    private lateinit var binding: FragmentDetailsBinding

    /**
     * [Function] onCreateView
     * initialize the binding
     * update the layout
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Initialize the binding
        binding = DataBindingUtil.inflate<FragmentDetailsBinding?>(
            inflater,
            R.layout.fragment_details,
            container,
            false
        ).apply {
            this.lifecycleOwner = this@DetailsFragment
            this.viewModel = acronymsViewModel
        }
        binding.initialize()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun FragmentDetailsBinding.initialize() {
        this.acronymsListRecyclerview.apply {
            adapter = varAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }
}
