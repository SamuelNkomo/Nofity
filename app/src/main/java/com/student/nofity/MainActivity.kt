package com.student.nofity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.student.nofity.R

class MainActivity : AppCompatActivity() {

    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationHistoryAdapter: NotificationHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Setup RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_history)
        recyclerView.layoutManager = LinearLayoutManager(this)
        notificationHistoryAdapter = NotificationHistoryAdapter(mutableListOf())
        recyclerView.adapter = notificationHistoryAdapter

        // Notify button click listener
        findViewById<Button>(R.id.btn_notify).setOnClickListener {
            sendNotification("Sample Title", "This is a sample notification message.")
        }

        // Settings button click listener (for future implementation)
        findViewById<Button>(R.id.settings_button).setOnClickListener {
            // Open settings activity
        }
    }

    private fun sendNotification(title: String, message: String) {
        val channelId = "your_channel_id"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Channel human-readable title", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        notificationManager.notify(System.currentTimeMillis().toInt(), notification)

        // Add notification to history
        notificationHistoryAdapter.addNotification(NotificationItem(title, message, System.currentTimeMillis().toString()))
    }
}
