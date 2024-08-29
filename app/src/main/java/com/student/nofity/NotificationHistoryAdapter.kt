package com.student.nofity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.student.nofity.R

data class NotificationItem(val title: String, val message: String, val timestamp: String)

class NotificationHistoryAdapter(private val notificationList: MutableList<NotificationItem>) :
    RecyclerView.Adapter<NotificationHistoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.notification_title)
        val messageTextView: TextView = view.findViewById(R.id.notification_message)
        val timestampTextView: TextView = view.findViewById(R.id.notification_timestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notification, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notification = notificationList[position]
        holder.titleTextView.text = notification.title
        holder.messageTextView.text = notification.message
        holder.timestampTextView.text = notification.timestamp
    }

    override fun getItemCount() = notificationList.size

    fun addNotification(notification: NotificationItem) {
        notificationList.add(0, notification) // Add to the top of the list
        notifyItemInserted(0)
    }
}
