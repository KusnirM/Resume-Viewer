package com.example.resumeviewer.repo

import com.example.resumeviewer.base.DataSource
import com.example.resumeviewer.db.ProjectsDatabase
import com.example.resumeviewer.models.Project
import io.reactivex.Maybe

class LocalDataSource(private val projectsDatabase: ProjectsDatabase) : DataSource {

    override fun getProjectsOrderedByLatest(): Maybe<List<Project>> = projectsDatabase.projectsDao().getOrderedProjects()

    override fun addProject(project: Project) = projectsDatabase.projectsDao().insertProject(project)

    override fun deleteProject(project: Project) = projectsDatabase.projectsDao().deleteProject(project)

    override fun updateProject(project: Project) = projectsDatabase.projectsDao().updateProject(project)
}