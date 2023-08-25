package com.robinson.checkinternetconnection.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.robinson.checkinternetconnection.base.BaseBindingFragment
import com.robinson.checkinternetconnection.databinding.FragmentSplashBinding

class SplashFragment : BaseBindingFragment<FragmentSplashBinding>() {

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //  binding.tvCheckInternetConnection.text = "Hello from Base Fragment!"
        // Initialize other views here
        // Wait for a delay and navigate to the main fragment
        Handler(Looper.getMainLooper()).postDelayed({
            navigateToMainFragment()
        }, SPLASH_DELAY_MILLIS)
    }

    private fun navigateToMainFragment() {
        val navController = findNavController()
      //  navController.navigate(R.id.action_splashFragment_to_mainFragment)
    }

    companion object {
        private const val SPLASH_DELAY_MILLIS = 2000L // 2 seconds
    }

}