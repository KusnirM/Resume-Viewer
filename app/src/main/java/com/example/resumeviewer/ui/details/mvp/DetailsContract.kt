package com.example.resumeviewer.ui.details.mvp

import com.example.resumeviewer.base.BaseView
import com.example.resumeviewer.models.Project

interface DetailsContract {
    interface View : BaseView {
        fun showProjectTitle(title: String)
        fun showProjectDuration(duration: String)
        fun showTagCloud(data: List<String>)
        fun showDescription(description: String)
    }

    interface Presenter {
        fun start(project: Project?)
    }
}