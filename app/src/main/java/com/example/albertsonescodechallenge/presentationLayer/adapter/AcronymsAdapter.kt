package com.example.albertsonescodechallenge.presentationLayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.albertsonescodechallenge.dataLayer.model.Lf
import com.example.albertsonescodechallenge.dataLayer.model.Var
import com.example.albertsonescodechallenge.databinding.ListItemBinding

private const val TAG = "AcronymsAdapter"

class AcronymsAdapter(
    private val itemsList: MutableList<ViewType>,
    private val onItemClick: ((Lf) -> Unit)? = null
) : Adapter<ViewHolder>() {

    fun <T> updateList(newList: List<T>) {
        itemsList.clear()
        newList.forEach {
            when (it) {
                is Lf -> {
                    itemsList.add(ViewType.LF_VIEW(it))
                }
                is Var -> {
                    itemsList.add(ViewType.VAR_VIEW(it))
                }
                else -> {}
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            0 -> {
                LfViewHolder(
                    ListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                VarViewHolder(
                    ListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }


    override fun getItemCount() = itemsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            0 -> {
                onItemClick?.let {
                    (holder as LfViewHolder).bindView(
                        (itemsList[position] as ViewType.LF_VIEW).lf,
                        it
                    )
                }
            }
            else -> {
                (holder as VarViewHolder).bindView(
                    (itemsList[position] as ViewType.VAR_VIEW).variant
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (itemsList[position]) {
            is ViewType.LF_VIEW -> 0
            is ViewType.VAR_VIEW -> 1
        }
}

class LfViewHolder(
    private val binding: ListItemBinding
) : ViewHolder(binding.root) {

    fun bindView(
        item: Lf,
        onItemClick: (Lf) -> Unit
    ) {

        binding.apply {
            this.view = ViewType.LF_VIEW(item)
        }
        itemView.setOnClickListener {
            onItemClick(item)
        }
    }
}

class VarViewHolder(
    private val binding: ListItemBinding
) : ViewHolder(binding.root) {

    fun bindView(
        item: Var
    ) {
        binding.apply {
            this.view = ViewType.VAR_VIEW(item)
        }
    }
}
