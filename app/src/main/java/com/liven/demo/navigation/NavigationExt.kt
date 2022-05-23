package com.liven.demo.navigation

import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.ui.R
import com.google.android.material.bottomnavigation.BottomNavigationView

val topThreeDest = arrayOf(
    com.liven.demo.R.id.nav_home,
    com.liven.demo.R.id.nav_invoice,
    com.liven.demo.R.id.nav_transaction
)

fun setupWithNavController(
    bottomNavigationView: BottomNavigationView,
    navController: NavController
) {
    bottomNavigationView.setOnNavigationItemSelectedListener { item ->
        navController.onNavDestinationSelected(item)
    }
}

fun NavController.onNavDestinationSelected(
    item: MenuItem
): Boolean {
    val builder = NavOptions.Builder()
        .setLaunchSingleTop(true)
    if (currentDestination!!.parent!!.findNode(item.itemId) is ActivityNavigator.Destination) {
        builder.setEnterAnim(R.anim.nav_default_enter_anim)
            .setExitAnim(R.anim.nav_default_exit_anim)
            .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
            .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
    } else {
        builder.setEnterAnim(R.animator.nav_default_enter_anim)
            .setExitAnim(R.animator.nav_default_exit_anim)
            .setPopEnterAnim(R.animator.nav_default_pop_enter_anim)
            .setPopExitAnim(R.animator.nav_default_pop_exit_anim)
    }
    val options = builder.build()
    return try {
        navigate(item.itemId, null, options)
        true
    } catch (e: IllegalArgumentException) {
        false
    }
}