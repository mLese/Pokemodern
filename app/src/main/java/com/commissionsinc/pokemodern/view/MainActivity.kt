package com.commissionsinc.pokemodern.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.commissionsinc.pokemodern.R
import com.commissionsinc.pokemodern.databinding.ActivityMainBinding
import com.commissionsinc.pokemodern.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), ResourceRecyclerViewAdapter.OnItemClickListener {

    lateinit var binding : ActivityMainBinding
    private val resourceRecyclerViewAdapter = ResourceRecyclerViewAdapter(arrayListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.resourceRv.layoutManager = LinearLayoutManager(this)
        binding.resourceRv.adapter = resourceRecyclerViewAdapter

        lifecycle.addObserver(viewModel)

        viewModel.resourceList.observe(this,
                Observer { it?.let { resourceRecyclerViewAdapter.replaceData(it) }})
    }

    override fun onItemClick(position: Int) {
        val resource = resourceRecyclerViewAdapter.getResource(position)
        resource.let {
            it.favorited = !it.favorited
        }
    }
}
