package com.student.nofity

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NotificationEntity::class], version = 1)
abstract class NotificationDatabase : RoomDatabase() {
    abstract fun notificationDao(): NotificationDao
}
