package com.example.cmsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cmsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.mainActivityToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}
