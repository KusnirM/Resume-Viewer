package com.example.resumeviewer.repo

import com.example.resumeviewer.base.DataSource
import com.example.resumeviewer.models.Project
import com.example.resumeviewer.net.ProjectsService
import io.reactivex.Maybe

class RemoteDataSource(private val projectsService: ProjectsService) : DataSource {
    override fun getProjectsOrderedByLatest(): Maybe<List<Project>> {
        return projectsService.getProjects()
            .flatMapMaybe { Maybe.just(it.projects) }
    }

    override fun addProject(project: Project) {
        //NO-OP
    }

    override fun deleteProject(project: Project) {
        //NO-OP
    }

    override fun updateProject(project: Project) {
        //NO-OP
    }
}