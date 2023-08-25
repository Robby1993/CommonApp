package com.robinson.checkinternetconnection.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.viewbinding.ViewBinding
import com.robinson.checkinternetconnection.R

abstract class BaseBindingActivity<T : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: T

    abstract fun createBinding(): T

    open fun initViews() {
        // Override this method to perform view-related initialization
    }


    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerNetworkChangeReceiver()
        binding = createBinding()
        setContentView(binding.root)
        initViews()
    }
    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }


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

    override fun onDestroy() {
        super.onDestroy()
        unregisterNetworkChangeReceiver()
    }

    private fun registerNetworkChangeReceiver() {
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeReceiver, intentFilter)
    }

    private fun unregisterNetworkChangeReceiver() {
        unregisterReceiver(networkChangeReceiver)
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
        if (isConnectedToInternet(this)) {
            // Retry the API call
            performApiCall();
        } else {
            onInternetDisconnected()
        }
    }

    open fun performApiCall() {
        // Implement your API call logic
    }
}
