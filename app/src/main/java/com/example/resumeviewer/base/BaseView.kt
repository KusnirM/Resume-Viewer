package com.example.resumeviewer.base

interface BaseView {
    fun showProgress()
    fun hideProgress()
    fun showError(message: String)
}