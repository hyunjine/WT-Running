package com.hyunjine.wt_running

import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.HiltAndroidApp
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import java.io.IOException
import java.net.SocketException

@HiltAndroidApp
class WTRunningApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        setFireBase()
        setRxJava()
    }

    private fun setFireBase() {
        FirebaseApp.initializeApp(this)
        Firebase.crashlytics.apply {
            setCrashlyticsCollectionEnabled(true)
//            setUserId(Firebase.auth.currentUser?.email ?: STRING_INIT_DATA)
        }
    }

    private fun setRxJava() {
        RxJavaPlugins.setErrorHandler { e ->
            var error = e
            if (error is UndeliverableException) {
                e.cause?.let { error = it }
            }
            if (error is IOException || error is SocketException) {
                // fine, irrelevant network problem or API that throws on cancellation
                return@setErrorHandler
            }
            if (error is InterruptedException) {
                // fine, some blocking code was interrupted by a dispose call
                return@setErrorHandler
            }
            if (error is NullPointerException || error is IllegalArgumentException) {
                // that's likely a bug in the application
                Thread.currentThread().uncaughtExceptionHandler
                    ?.uncaughtException(Thread.currentThread(), error)
                return@setErrorHandler
            }
            if (error is IllegalStateException) {
                // that's a bug in RxJava or in a custom operator
                Thread.currentThread().uncaughtExceptionHandler
                    ?.uncaughtException(Thread.currentThread(), error)
                return@setErrorHandler
            }
            Log.w("Undeliverable exception received, not sure what to do", error)
        }
    }
}