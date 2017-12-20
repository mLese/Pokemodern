package com.commissionsinc.pokemodern.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.commissionsinc.pokemodern.databinding.ItemResourceBinding
import com.commissionsinc.pokemodern.model.Resource


class ResourceRecyclerViewAdapter(private var items: List<Resource>,
        private var listener: OnItemClickListener)
    : RecyclerView.Adapter<ResourceRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding = ItemResourceBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    fun replaceData(updatedItems: List<Resource>) {
        items = updatedItems
        notifyDataSetChanged()
    }

    fun getResource(position: Int): Resource {
        return items[position]
    }

    class ViewHolder(private var binding: ItemResourceBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(res: Resource, listener: OnItemClickListener) {
            binding.apply {
                resource = res
                number.text = "#" + String.format("%03d", layoutPosition)
                favorite.setOnClickListener({ _ -> listener.onItemClick(layoutPosition)})
                notFavorite.setOnClickListener({ _ -> listener.onItemClick(layoutPosition)})
                executePendingBindings()
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}