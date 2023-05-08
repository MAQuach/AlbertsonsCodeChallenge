package com.example.albertsonescodechallenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.albertsonescodechallenge.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * [MainActivity]
 * Defines the main activity logic
 * Contains the Fragment Navigation
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    /**
     * [Function] onCreate
     * Inflates layout for the Main Activity
     * Sets up the navigation
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Data binding for main activity
        activityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        // Navigation
        val navHost = supportFragmentManager
            .findFragmentById(R.id.frag_container) as NavHostFragment
        setupActionBarWithNavController(navHost.navController)
    }

    /**
     * [Function] onSupportNavigateUp
     * Will get the previous fragment from the backstack
     * When the back arrow is clicked
     */
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.frag_container).navigateUp()
    }
}
