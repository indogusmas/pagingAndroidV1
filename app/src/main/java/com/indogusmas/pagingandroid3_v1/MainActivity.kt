package com.indogusmas.pagingandroid3_v1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.indogusmas.pagingandroid3_v1.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var  passengerViewModel: PassengeresViewModel
    lateinit var passengerAdapter: PassengersAdapter
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupView()
        setupList()
    }

    private fun setupList() {
        lifecycleScope.launch {
            passengerViewModel.passenger.collectLatest { pagedData ->
                passengerAdapter.submitData(pagedData)
            }
        }
    }

    private fun setupView() {
        passengerAdapter = PassengersAdapter()
        binding.rvPassengers.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = passengerAdapter
            setHasFixedSize(false)
        }
        binding.rvPassengers.adapter =
            passengerAdapter.withLoadStateHeaderAndFooter(
                header =  PassengersLoadStateAdapter{passengerAdapter.retry()},
                footer =  PassengersLoadStateAdapter{passengerAdapter.retry()}
            )
    }

    private fun setupViewModel() {
        val factory = PassengersViewModelFactory(MyApi.create())
        passengerViewModel = ViewModelProvider(this, factory).get(PassengeresViewModel::class.java)
    }
}