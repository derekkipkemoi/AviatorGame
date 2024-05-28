package com.ndege.cheza.ndegecrunch.utils

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

data object Utils {
    fun isNetworkConnected(context: Context) : Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =  connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        capabilities.also {
            if (it != null){
                if (it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
                    return true
                else if (it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){
                    return true
                }
            }
        }
        return false
    }

    fun restartApp(context: Context){
        val packageManager: PackageManager = context.packageManager
        val intent: Intent = packageManager.getLaunchIntentForPackage(context.packageName)!!
        val componentName: ComponentName = intent.component!!
        val restartIntent: Intent = Intent.makeRestartActivityTask(componentName)
        context.startActivity(restartIntent)
        Runtime.getRuntime().exit(0)
    }

    fun shareAppPlayStoreUrl(context: Context) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                "Ndege, Fly and Win\n " + "https://play.google.com/store/apps/details?id=" + context.applicationInfo.packageName
            )
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }
}