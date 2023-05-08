package com.example.albertsonescodechallenge.presentationLayer.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.albertsonescodechallenge.presentationLayer.viewmodel.AcronymsViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * [Class] BaseFragment
 * Defines shared variables for every fragment
 * - ViewModel
 * - View Data Binding
 */
@AndroidEntryPoint
open class BaseFragment : Fragment() {

    protected val acronymsViewModel by lazy {
        ViewModelProvider(requireActivity())[AcronymsViewModel::class.java]
    }
}
