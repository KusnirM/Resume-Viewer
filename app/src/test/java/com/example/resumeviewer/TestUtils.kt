package com.example.resumeviewer

import com.example.resumeviewer.models.Project
import com.example.resumeviewer.models.Projects

class TestUtils {
    companion object {
        fun getProjects(): Projects {
            val project =  Project(projectId = null,
                projectNumber = 1,
                companyName = "Acme Inc",
                startDate = "October 2018",
                endDate = "November 2018",
                tech = listOf("Hello", "World"),
                description = "An Awesome Project")
            return Projects(listOf(project))
        }
    }
}