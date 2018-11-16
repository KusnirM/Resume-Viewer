package com.example.resumeviewer.repo

import com.example.resumeviewer.base.DataSource
import com.example.resumeviewer.models.Project
import io.reactivex.Maybe


class ProjectsRepository(private val localDataSource: DataSource, private val remoteDataSource: DataSource) : DataSource {

    override fun getProjectsOrderedByLatest(): Maybe<List<Project>> {
        return remoteDataSource.getProjectsOrderedByLatest()
            .doAfterSuccess { it.forEach { project -> localDataSource.addProject(project) } }
            .doOnError { it.printStackTrace() }
            .onErrorResumeNext(localDataSource.getProjectsOrderedByLatest())
    }

    override fun addProject(project: Project) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteProject(project: Project) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateProject(project: Project) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}