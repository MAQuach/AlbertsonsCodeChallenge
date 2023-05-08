package com.example.albertsonescodechallenge.presentationLayer.adapter

import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.albertsonescodechallenge.dataLayer.model.Lf
import com.example.albertsonescodechallenge.dataLayer.model.Var
import com.example.albertsonescodechallenge.utils.AcronymState

@BindingAdapter("variants")
fun bindVariantAdapter(
    recyclerView: RecyclerView,
    acronym: Lf?
) {

    val varAdapter = recyclerView.adapter as AcronymsAdapter
    acronym?.vars?.let {
        varAdapter.updateList(it)
    } ?: varAdapter.updateList<Var>(emptyList())
}

@BindingAdapter("acronymsLf")
fun bindAcronymStateData(
    recyclerView: RecyclerView,
    state: AcronymState
) {

    val adapter = recyclerView.adapter as AcronymsAdapter
    when (state) {
        is AcronymState.ERROR -> {}
        is AcronymState.SUCCESS -> {
            if (state.response.isNotEmpty()) {
                adapter.updateList(state.response[0].lfs ?: emptyList())
            } else {
                adapter.updateList<Lf>(emptyList())
            }
        }
        else -> {}
    }
}

@BindingAdapter("acronym")
fun bindAcronymText(
    textView: TextView,
    acronym: Lf?
) {

    textView.text = acronym?.lf ?: "Unavailable"
}

@BindingAdapter("queryTextListener")
fun setOnQueryTextListener(
    searchView: SearchView,
    listener: OnQueryTextListener
) {

    searchView.setOnQueryTextListener(listener)
}

@BindingAdapter("holderText")
fun bindHolderText(
    textView: TextView,
    viewType: ViewType
) {

    when (viewType) {
        is ViewType.LF_VIEW -> {
            textView.text = viewType.lf.lf ?: "Unavailable"
        }
        is ViewType.VAR_VIEW -> {
            textView.text = viewType.variant.lf ?: "Unavailable"
        }
    }
}
