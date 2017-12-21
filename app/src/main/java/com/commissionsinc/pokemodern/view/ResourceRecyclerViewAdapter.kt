package com.commissionsinc.pokemodern.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.commissionsinc.pokemodern.databinding.ItemResourceBinding
import com.commissionsinc.pokemodern.model.Resource

// instantiate our adapter with a list and callback for item clicking
class ResourceRecyclerViewAdapter(private var items: List<Resource>,
        private var listener: OnItemClickListener)
    : RecyclerView.Adapter<ResourceRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)

        // obtain our binding and create view holder with this binding
        val binding = ItemResourceBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    // bind view holder with resource and listener
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    // used to update the recycler view with a new resource list once fetched from view model
    fun replaceData(updatedItems: List<Resource>) {
        items = updatedItems
        notifyDataSetChanged()
    }

    // get resource at specific position so we can modify list from main activity click interface
    fun getResource(position: Int): Resource {
        return items[position]
    }

    // "binding.root" refers to top level view of binding
    class ViewHolder(private var binding: ItemResourceBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(res: Resource, listener: OnItemClickListener) {
            // set our binding values based on the resource, "name" is set automatically through binding
            binding.apply {
                resource = res
                number.text = "#" + String.format("%03d", layoutPosition)

                // we are ignoring the "view" param in the OnClickListener interface so we can use _ as param
                favorite.setOnClickListener({ _ -> listener.onItemClick(layoutPosition)})
                notFavorite.setOnClickListener({ _ -> listener.onItemClick(layoutPosition)})
            }
        }
    }

    // simple on click interface
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}