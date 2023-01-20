package com.demir.foodapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.demir.foodapp.R
import com.demir.foodapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       val nav= Navigation.findNavController(this,R.id.foodHostFragment)
        binding.foodNavBottom.setupWithNavController(nav)
        nav.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.homeFragment ->binding.foodNavBottom.visibility = View.VISIBLE
                R.id.favoritesFragment ->binding.foodNavBottom.visibility = View.VISIBLE
                R.id.catagoriesFragment ->binding.foodNavBottom.visibility = View.VISIBLE
                else -> binding.foodNavBottom.visibility = View.GONE
            }
        }

    }
}