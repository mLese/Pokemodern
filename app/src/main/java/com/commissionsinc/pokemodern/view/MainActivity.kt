package com.commissionsinc.pokemodern.view

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.commissionsinc.pokemodern.R
import com.commissionsinc.pokemodern.databinding.ActivityMainBinding
import com.commissionsinc.pokemodern.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), ResourceRecyclerViewAdapter.OnItemClickListener {

    lateinit var binding : ActivityMainBinding // "lateinit" used to indicate we are instantiating this value later

    // our adapter instantiated to an empty list and on click listener set to this activity
    private val resourceRecyclerViewAdapter = ResourceRecyclerViewAdapter(arrayListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // get our data binding object
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // use the view model provider to get the view model, same view model will be re-used on events such as
        // orientation changes
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        // set our view model object in our binding so we can update our view states
        binding.viewModel = viewModel

        // obtain recycler view from binding, no need to "findViewById" with data binding
        binding.resourceRv.layoutManager = LinearLayoutManager(this)
        binding.resourceRv.adapter = resourceRecyclerViewAdapter

        // our viewModel needs to be aware of lifecycle events so we can load when activity is created
        lifecycle.addObserver(viewModel)

        // observe for changes on our resource list and update the adapter with the new data when it is loaded
        viewModel.resourceList.observe(this,
                Observer { it?.let { resourceRecyclerViewAdapter.replaceData(it) }})
    }

    // implement our on click interface defined in adapter
    override fun onItemClick(position: Int) {
        // toggle the favorited state when "favorite" icon is selected on appropriate resource
        val resource = resourceRecyclerViewAdapter.getResource(position)
        resource.favorited = !resource.favorited
    }
}
