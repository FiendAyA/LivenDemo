package com.liven.demo.navigation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import com.liven.demo.R

@Navigator.Name("fragment")
class FragmentNavigatorHideShow(
    private val mContext: Context,
    private val mFragmentManager: FragmentManager,
    private val mContainerId: Int
) : FragmentNavigator(mContext, mFragmentManager, mContainerId) {


    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?
    ): NavDestination? {
        if (mFragmentManager.isStateSaved) {
            Log.i(
                TAG,
                "Ignoring navigate() call: FragmentManager has already" + " saved its state"
            )
            return null
        }
        var className = destination.className
        if (className[0] == '.') {
            className = mContext.packageName + className
        }
        val ft = mFragmentManager.beginTransaction()
        var enterAnim = navOptions?.enterAnim ?: -1
        var exitAnim = navOptions?.exitAnim ?: -1
        var popEnterAnim = navOptions?.popEnterAnim ?: -1
        var popExitAnim = navOptions?.popExitAnim ?: -1
        if (enterAnim != -1 || exitAnim != -1 || popEnterAnim != -1 || popExitAnim != -1) {
            enterAnim = if (enterAnim != -1) enterAnim else 0
            exitAnim = if (exitAnim != -1) exitAnim else 0
            popEnterAnim = if (popEnterAnim != -1) popEnterAnim else 0
            popExitAnim = if (popExitAnim != -1) popExitAnim else 0
            ft.setCustomAnimations(enterAnim, exitAnim, popEnterAnim, popExitAnim)
        }

        // 获取backStack
        var mBackStack: java.util.ArrayDeque<Int>? = null
        try {
            val field = FragmentNavigator::class.java.getDeclaredField("mBackStack")
            field.isAccessible = true
            mBackStack = field[this] as java.util.ArrayDeque<Int>
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

        if (mBackStack!!.size > 0) {
            val fragment = mFragmentManager.findFragmentByTag(mBackStack.last.toString())
            if (fragment != null) {
                ft.setMaxLifecycle(fragment, Lifecycle.State.STARTED)
                ft.hide(fragment)
            }
        }
        val tag = destination.id.toString()
        var frag = mFragmentManager.findFragmentByTag(tag)
        if (frag != null) {
            ft.setMaxLifecycle(frag, Lifecycle.State.RESUMED)
            ft.show(frag)
        } else {
            frag = instantiateFragment(mContext, mFragmentManager, className, args)
            frag.arguments = args
            ft.add(mContainerId, frag, tag)
        }

        @IdRes val destId = destination.id

        // 首次开始navigation
        val initialNavigation = mBackStack.isEmpty()
        // 判定是否为一级导航的三个页面
        val isSingleInstance = navOptions != null && !initialNavigation
                && destination.id in topThreeDest
                && mBackStack.contains(destId)

        when {
            initialNavigation -> {
            }
            isSingleInstance -> {
                mBackStack.remove(destId)
            }
            else -> {
                ft.addToBackStack(generateBackStackName(mBackStack.size + 1, destId))
            }
        }
        if (navigatorExtras is Extras) {
            for ((key, value) in navigatorExtras.sharedElements) {
                ft.addSharedElement(key!!, value!!)
            }
        }
        ft.setReorderingAllowed(true)
        ft.commit()

        // The commit succeeded, update our view of the world
        mBackStack.add(destId)
        mBackStack.forEach {
            Log.i(TAG, "Id is: $it")
        }
        Log.d(TAG, "mBackStack is ${mBackStack.size}")
        return destination
    }

    private fun generateBackStackName(backStackindex: Int, destid: Int): String {
        return "$backStackindex-$destid"
    }

    companion object {
        private const val TAG = "HSFragmentNavigator"
    }
}