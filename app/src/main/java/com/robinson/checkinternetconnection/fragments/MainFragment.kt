package com.robinson.checkinternetconnection.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.robinson.checkinternetconnection.base.BaseBindingFragment
import com.robinson.checkinternetconnection.databinding.FragmentMainBinding
import com.robinson.checkinternetconnection.extentions.setViewColor
import com.robinson.checkinternetconnection.fragments.main.MainViewModel

class MainFragment : BaseBindingFragment<FragmentMainBinding>() {

    private lateinit var viewModel: MainViewModel

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvCheckInternetConnection.text = "Hello from Base Fragment!"
        // Initialize other views here

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.data.observe(viewLifecycleOwner) {
            // Update UI with the API response
        }

        viewModel.error.observe(viewLifecycleOwner) {
            // Handle error message
        }

       // viewModel.fetchData()

    }

    override fun onInternetConnected() {
        binding.tvCheckInternetConnection.setViewColor(android.R.color.holo_green_dark)
        binding.tvCheckInternetConnection.text = "Fragment Internet is connected"
    }

    override fun onInternetDisconnected() {
        binding.tvCheckInternetConnection.setViewColor(android.R.color.holo_red_dark)
        binding.tvCheckInternetConnection.text = "Fragment Internet is disconnected"
    }

    override fun performApiCall() {
        showToast("api call")
    }


    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}