package com.example.resumeviewer.ui.home.mvp

import com.example.resumeviewer.base.BaseView
import com.example.resumeviewer.base.Item
import com.example.resumeviewer.models.Project

interface HomeContract {
    //master
    interface View : BaseView {
        fun showData(data: List<Project>)
        fun launchDetailsActivity(project: Project)
    }
    interface Presenter {
        fun start()
        fun stop()
        fun onItemSelected(item: Item)
    }
}