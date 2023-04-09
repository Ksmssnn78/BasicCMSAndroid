package com.example.cmsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.cmsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        Timber.tag("home").d("Main activity")
        mainBinding.mainActivityToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainActivityFragment) as NavHostFragment
        navController = navHostFragment.navController
        setContentView(mainBinding.root)
    }
}
