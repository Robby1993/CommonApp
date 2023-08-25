package com.robinson.checkinternetconnection

import android.content.Intent
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.robinson.checkinternetconnection.base.BaseBindingActivity
import com.robinson.checkinternetconnection.databinding.ActivityMainBinding

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {

    override fun createBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        /*  binding.tvCheckInternetConnection.text = "Check Internet Connection!"
          // Initialize other views here
          binding.tvCheckInternetConnection.setOnClickListener {
              retryApiCall()
          }*/

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navController.navigate(R.id.mainFragment) // Load the first fragment

      //  NavigationUI.setupActionBarWithNavController(this, navController)

       // navigateToBaseActivity()

    }


    private fun navigateToBaseActivity() {
        val intent = Intent(this, BaseBindingActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onInternetConnected() {
        //  binding.tvCheckInternetConnection.setTextColor(ContextCompat.getColor(this,android.R.color.holo_green_dark))
        //  binding.tvCheckInternetConnection.text = "Internet is connected"
        // showToast("Internet is connected")
        // Additional logic when internet is connected
    }

    override fun onInternetDisconnected() {
        //   binding.tvCheckInternetConnection.setTextColor(ContextCompat.getColor(this,android.R.color.holo_red_dark))
        //  binding.tvCheckInternetConnection.text = "Internet is disconnected"

        // showToast("Internet is disconnected")
        // Additional logic when internet is disconnected
    }

    override fun performApiCall() {
        showToast("api call")
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}