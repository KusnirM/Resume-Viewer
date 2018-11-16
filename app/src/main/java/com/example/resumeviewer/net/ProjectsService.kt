package com.example.resumeviewer.net

import com.example.resumeviewer.common.PROJECTS_GIST
import com.example.resumeviewer.models.Projects
import io.reactivex.Single
import retrofit2.http.GET

interface ProjectsService {
    @GET(PROJECTS_GIST)
    fun getProjects(): Single<Projects>
}