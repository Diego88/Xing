package com.hiberus.mobile.android.xing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hiberus.mobile.android.xing.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}