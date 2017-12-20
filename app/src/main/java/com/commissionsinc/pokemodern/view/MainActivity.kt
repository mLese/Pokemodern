package com.commissionsinc.pokemodern.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.commissionsinc.pokemodern.PokemodernApplication
import com.commissionsinc.pokemodern.R
import com.commissionsinc.pokemodern.databinding.ActivityMainBinding
import com.commissionsinc.pokemodern.viewmodel.MainViewModel
import com.commissionsinc.pokemodern.viewmodel.MainViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ResourceRecyclerViewAdapter.OnItemClickListener {

    lateinit var binding : ActivityMainBinding
    private val resourceRecyclerViewAdapter = ResourceRecyclerViewAdapter(arrayListOf(), this)

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app = application as PokemodernApplication
        app.appComponent.mainComponent().inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        binding.viewModel = viewModel

        binding.resourceRv.layoutManager = LinearLayoutManager(this)
        binding.resourceRv.adapter = resourceRecyclerViewAdapter

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
