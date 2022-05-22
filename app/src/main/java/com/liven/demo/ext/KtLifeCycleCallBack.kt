package com.liven.demo.ext

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log

class KtxLifeCycleCallBack : Application.ActivityLifecycleCallbacks {
    companion object{
        private const val TAG = "KtxLifeCycleCallBack"
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        KtxActivityManger.pushActivity(activity)
        Log.d(TAG,"onActivityCreated : ${activity.localClassName}")
    }
    override fun onActivityStarted(activity: Activity) {
        Log.d(TAG,"onActivityStarted : ${activity.localClassName}")
    }

    override fun onActivityResumed(activity: Activity) {
        Log.d(TAG,"onActivityResumed : ${activity.localClassName}")
    }

    override fun onActivityPaused(activity: Activity) {
        Log.d(TAG,"onActivityPaused : ${activity.localClassName}")
    }


    override fun onActivityDestroyed(activity: Activity) {
        Log.d(TAG,"onActivityDestroyed : ${activity.localClassName}")
        KtxActivityManger.popActivity(activity)
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityStopped(activity: Activity) {
        Log.d(TAG,"onActivityStopped : ${activity.localClassName}")
    }


}