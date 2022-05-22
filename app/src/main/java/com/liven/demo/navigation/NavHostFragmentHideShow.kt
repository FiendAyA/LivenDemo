package com.liven.demo.navigation

import android.view.View
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import com.liven.demo.R

class NavHostFragmentHideShow : NavHostFragment() {
    @Deprecated("Deprecated in Java")
    override fun createFragmentNavigator(): Navigator<out FragmentNavigator.Destination> {
        return FragmentNavigatorHideShow(requireContext(), childFragmentManager, containerId)
    }


    private val containerId: Int
        get() {
            val id = id
            return if (id != 0 && id != View.NO_ID) {
                id
                // Fallback to using our own ID if this Fragment wasn't added via
                // add(containerViewId, Fragment)
            } else R.id.nav_host_fragment_container
        }
}