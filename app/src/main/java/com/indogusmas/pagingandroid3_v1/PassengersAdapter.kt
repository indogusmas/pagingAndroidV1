package com.indogusmas.pagingandroid3_v1


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.indogusmas.pagingandroid3_v1.databinding.ItemPassengerBinding
import com.squareup.picasso.Picasso

class PassengersAdapter: PagingDataAdapter<Passenger, PassengersAdapter.PassingViewHolder>(PassengersComparator) {


    class PassingViewHolder(
        private val  itemBinding: ItemPassengerBinding
    ): RecyclerView.ViewHolder(itemBinding.root) {
        fun bindPassenger(it: Passenger) {
            itemBinding.textViewNameWithTrips.text = it.name
            itemBinding.textViewHeadquarters.text = it.airline.get(0).head_quaters
            Picasso.get().load(it.airline.get(0).logo).into(itemBinding.imageViewAirlinesLogo)
        }
    }


    object PassengersComparator : DiffUtil.ItemCallback<Passenger>() {
        override fun areItemsTheSame(oldItem: Passenger, newItem: Passenger): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Passenger, newItem: Passenger): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: PassingViewHolder, position: Int) {
        val  item = getItem(position)
        item?.let {
            holder.bindPassenger(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassingViewHolder {
        return  PassingViewHolder(
            ItemPassengerBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }
}