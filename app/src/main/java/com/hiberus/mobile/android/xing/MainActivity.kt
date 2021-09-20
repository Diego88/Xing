package com.hiberus.mobile.android.xing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.hiberus.mobile.android.xing.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.clToolbar.toolbar)
        configureNavigation()
    }

    private fun configureNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                as? NavHostFragment
        navHostFragment?.navController?.let { navController ->
            setupWithNavController(binding.clToolbar.toolbar, navController)
        }
    }
}