package com.student.nofity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {

    // Insert a new notification into the database
    @Insert
    suspend fun insert(notification: NotificationEntity): Long // Returns the row ID of the inserted item

    // Retrieve all notifications as a Flow (for real-time updates)
    @Query("SELECT * FROM notifications ORDER BY timestamp DESC")
    fun getAllNotifications(): Flow<List<NotificationEntity>>

    // Delete all notifications and return the number of rows deleted
    @Query("DELETE FROM notifications")
    suspend fun deleteAll(): Int
}
