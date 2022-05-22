package com.liven.demo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.liven.demo.R
import com.liven.demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mViewBind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mViewBind.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.nav_home, R.id.nav_invoice, R.id.nav_transaction))
        setupActionBarWithNavController(navHostFragment.navController, appBarConfiguration)
        mViewBind.navView.setupWithNavController(navHostFragment.navController)
    }
}