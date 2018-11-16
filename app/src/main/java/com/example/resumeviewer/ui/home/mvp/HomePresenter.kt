package com.example.resumeviewer.ui.home.mvp

import com.example.resumeviewer.base.DataSource
import com.example.resumeviewer.base.Item
import com.example.resumeviewer.models.Project
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenter(private val projectsRepository: DataSource, private val view: HomeContract.View) :
    HomeContract.Presenter {


    private val compositeDisposable = CompositeDisposable()

    override fun start() {
        compositeDisposable.add(projectsRepository.getProjectsOrderedByLatest()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showProgress() }
            .doOnError { view.hideProgress() }
            .doOnSuccess { view.hideProgress() }
            .subscribe(this::handleSuccess, this::handleFailure))
    }

    private fun handleSuccess(data: List<Project>) {
        if (data.isNotEmpty()) {
            view.showData(data)
        } else {
            view.showError("Data not available, please try again")
        }
    }

    private fun handleFailure(throwable: Throwable) {
        throwable.message?.let {
            view.showError(it)
        }
    }

    override fun stop() {
        compositeDisposable.clear()
    }

    override fun onItemSelected(item: Item) {
        view.launchDetailsActivity(item as Project)
    }
}