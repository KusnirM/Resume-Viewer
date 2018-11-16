package com.example.resumeviewer.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.resumeviewer.common.DATABASE_VERSION
import com.example.resumeviewer.models.Project

@Database(entities = [Project::class], version = DATABASE_VERSION)
abstract class ProjectsDatabase : RoomDatabase() {
    abstract fun projectsDao(): ProjectsDao
}