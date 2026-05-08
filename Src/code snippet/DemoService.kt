package com.amaze.filemanager.asynchronous.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class DemoService : Service() {

    override fun onCreate() {
        super.onCreate()
        createChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val mode = intent?.getStringExtra("demo_mode") ?: "notification"

        val notificationTitle =
            if (mode == "cpu") "CPU Demo Running" else "Notification Demo Running"

        val notificationText =
            if (mode == "cpu") "Simulated CPU workload in progress"
            else "Notification demo is running"

        val notification: Notification =
            NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setContentTitle(notificationTitle)
                .setContentText(notificationText)
                .setOngoing(true)
                .build()

        startForeground(NOTIFICATION_ID, notification)

        Thread {
            Log.d(TAG, "Demo service started")
            Log.d(TAG, "Mode=$mode")

            val deviceInfo = """
                packageName=$packageName
                manufacturer=${Build.MANUFACTURER}
                model=${Build.MODEL}
                androidVersion=${Build.VERSION.RELEASE}
                sdkInt=${Build.VERSION.SDK_INT}
                brand=${Build.BRAND}
                device=${Build.DEVICE}
                product=${Build.PRODUCT}
                timestamp=${System.currentTimeMillis()}
            """.trimIndent()

            Log.d(TAG, "Device info collected:")
            Log.d(TAG, deviceInfo)

            if (mode == "cpu") {
                runCpuDemo()
            } else {
                runNotificationDemo()
            }

            Log.d(TAG, "Demo service finished")
            stopForeground(true)
            stopSelf()
        }.start()

        return START_NOT_STICKY
    }


    private fun runNotificationDemo() {
        Log.d(TAG, "Notification demo started")
        try {
            Thread.sleep(5000)
        } catch (_: InterruptedException) {
        }
        Log.d(TAG, "Unauthorized notification demonstration completed")
    }

    private fun runCpuDemo() {
        Log.d(TAG, "CPU demo started")

        val startTime = System.currentTimeMillis()
        val endTime = startTime + 10_000
        var counter = 0L
        var value = 1.0001

        while (System.currentTimeMillis() < endTime) {
            repeat(50_000) {
                value *= 1.0000001
                value /= 1.00000005
                counter++
            }

            try {
                Thread.sleep(50)
            } catch (_: InterruptedException) {
            }
        }

        val elapsed = System.currentTimeMillis() - startTime

        Log.d(TAG, "CPU demo completed")
        Log.d(TAG, "CPU loop iterations=$counter")
        Log.d(TAG, "CPU final value=$value")
        Log.d(TAG, "CPU elapsedMs=$elapsed")
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Demo Service Channel",
                NotificationManager.IMPORTANCE_LOW
            )
            getSystemService(NotificationManager::class.java)
                .createNotificationChannel(channel)
        }
    }

    companion object {
        private const val TAG = "DemoService"
        private const val CHANNEL_ID = "demo_service_channel"
        private const val NOTIFICATION_ID = 9001
    }
}