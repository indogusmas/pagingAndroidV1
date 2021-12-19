package com.indogusmas.pagingandroid3_v1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.indogusmas.pagingandroid3_v1.databinding.ItemLoadingStateBinding

class PassengersLoadStateAdapter(
    private val retry: () -> Unit
): LoadStateAdapter<PassengersLoadStateAdapter.ViewHolder>() {
    class ViewHolder(
        private val  binding: ItemLoadingStateBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindPassenger(loadState: LoadState) {
           if (loadState is LoadState.Error){
               binding.textViewError.text = loadState.error.localizedMessage
           }
            if (loadState is LoadState.Loading){
                binding.progressbar.visibility = View.VISIBLE
            }
            if (loadState is LoadState.Error){
                binding.buttonRetry.visibility = View.VISIBLE
                binding.textViewError.visibility = View.VISIBLE
            }
            binding.buttonRetry.setOnClickListener {
                retry
            }

            binding.progressbar.visibility = View.VISIBLE
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bindPassenger(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) = ViewHolder(
        ItemLoadingStateBinding.inflate(LayoutInflater.from(parent.context),parent,false),
        retry
    )
}