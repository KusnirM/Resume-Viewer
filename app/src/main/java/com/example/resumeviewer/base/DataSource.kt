package com.example.resumeviewer.base

import com.example.resumeviewer.models.Project
import io.reactivex.Maybe

interface DataSource {
    fun getProjectsOrderedByLatest(): Maybe<List<Project>>
    fun addProject(project: Project)
    fun deleteProject(project: Project)
    fun updateProject(project: Project)
}