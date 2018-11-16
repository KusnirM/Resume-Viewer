package com.example.resumeviewer.ui.details.mvp

import com.example.resumeviewer.models.Project

class DetailsPresenter(private val view: DetailsContract.View) : DetailsContract.Presenter {

    override fun start(project: Project?) {
        project?.let {
            view.showProjectTitle(it.companyName)
            view.showProjectDuration(it.startDate + " - " + it.endDate)
            view.showDescription(it.description)
            view.showTagCloud(it.tech)
            return
        }
        view.showError("Invalid Project, Please Try Again")
    }

}