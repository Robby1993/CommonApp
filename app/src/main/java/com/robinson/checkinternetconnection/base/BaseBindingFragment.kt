package com.robinson.checkinternetconnection.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseBindingFragment<T : ViewBinding> : Fragment() {

    private var _binding: T? = null
    protected val binding: T get() = _binding!!

    private val networkChangeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (context != null) {
                if (isConnectedToInternet(context)) {
                    onInternetConnected()
                } else {
                    onInternetDisconnected()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = createBinding(inflater, container)
        registerNetworkChangeReceiver()
        return binding.root
    }

    abstract fun createBinding(inflater: LayoutInflater, container: ViewGroup?): T

    private fun registerNetworkChangeReceiver() {
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        binding.root.context.registerReceiver(networkChangeReceiver, intentFilter)
    }

    private fun unregisterNetworkChangeReceiver() {
        binding.root.context.unregisterReceiver(networkChangeReceiver)
    }

    private fun isConnectedToInternet(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    open fun onInternetConnected() {
        // Implement your logic when internet is connected
    }

    open fun onInternetDisconnected() {
        // Implement your logic when internet is disconnected
    }

    open fun retryApiCall() {
        if (isConnectedToInternet(binding.root.context)) {
            // Retry the API call
            performApiCall();
        } else {
            onInternetDisconnected()
        }
    }

    open fun performApiCall() {
        // Implement your API call logic
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unregisterNetworkChangeReceiver()
        _binding = null
    }
}