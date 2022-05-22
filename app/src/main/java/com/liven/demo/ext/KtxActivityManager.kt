package com.liven.demo.ext

import android.app.Activity
import java.util.*

object KtxActivityManger {
    private val mActivityList = LinkedList<Activity>()

    val currentActivity: Activity?
        get() =
            if (mActivityList.isEmpty()) null
            else mActivityList.last

    /**
     * @param activity Activity
     */
    fun pushActivity(activity: Activity) {
        if (mActivityList.contains(activity)) {
            if (mActivityList.last != activity) {
                mActivityList.remove(activity)
                mActivityList.add(activity)
            }
        } else {
            mActivityList.add(activity)
        }
    }

    /**
     * @param activity Activity
     */
    fun popActivity(activity: Activity) {
        mActivityList.remove(activity)
    }

    /**
     */
    fun finishCurrentActivity() {
        currentActivity?.finish()
    }

    /**
     * @param activity Activity
     */
    fun finishActivity(activity: Activity) {
        mActivityList.remove(activity)
        activity.finish()
    }

    /**
     * @param clazz Class<*>
     */
    fun finishActivity(clazz: Class<*>) {
        for (activity in mActivityList) {
            if (activity.javaClass == clazz) {
                mActivityList.remove(activity)
                activity.finish()
                return
            }
        }
    }

    /**
     * 关闭所有的activity
     */
    fun finishAllActivity() {
        for (activity in mActivityList) {
            activity.finish()
        }
        mActivityList.clear()
    }
}