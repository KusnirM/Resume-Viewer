package com.example.resumeviewer.db

import android.arch.persistence.room.*
import com.example.resumeviewer.models.Project
import io.reactivex.Maybe

@Dao
interface ProjectsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProject(project: Project)

    @Update
    fun updateProject(project: Project)

    @Delete
    fun deleteProject(project: Project)

    @Query("SELECT * FROM projects ORDER BY projectNumber DESC")
    fun getOrderedProjects(): Maybe<List<Project>>
}