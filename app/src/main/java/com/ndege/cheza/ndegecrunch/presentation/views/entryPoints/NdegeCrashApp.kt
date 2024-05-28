package com.ndege.cheza.ndegecrunch.presentation.views.entryPoints

import android.app.Application
import com.ndege.cheza.ndegecrunch.utils.Constants
import com.onesignal.OneSignal
import com.onesignal.debug.LogLevel
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltAndroidApp
class NdegeCrashApp() : Application() {
    override fun onCreate() {
        super.onCreate()
        OneSignal.Debug.logLevel = LogLevel.VERBOSE
        OneSignal.initWithContext(this, Constants.ONESIGNAL_APP_ID)
        CoroutineScope(Dispatchers.IO).launch {
            OneSignal.Notifications.requestPermission(true)
        }
    }
}