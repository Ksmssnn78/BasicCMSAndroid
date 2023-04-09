package com.example.cmsapp

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.cmsapp.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.zip.Inflater

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding
    lateinit var navController : NavController
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
        return setContentView(mainBinding.root)
    }
}
