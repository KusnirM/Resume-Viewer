package com.example.resumeviewer

import com.example.resumeviewer.base.DataSource
import com.example.resumeviewer.ui.home.mvp.HomeContract
import com.example.resumeviewer.ui.home.mvp.HomePresenter
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomePresenterTest : BaseTest() {

    private val homeView: HomeContract.View = mock()
    private val projectsRepository: DataSource = mock()

    private lateinit var presenter: HomeContract.Presenter

    @Before
    override fun setup() {
        super.setup()
        presenter = HomePresenter(projectsRepository, homeView)
    }

    @Test
    fun start_WhenOnline_Success() {
        //given
        val project = TestUtils.getProjects()
        whenever(projectsRepository.getProjectsOrderedByLatest()).thenReturn(Maybe.just(project.projects))

        //when
        presenter.start()

        //then
        verify(projectsRepository).getProjectsOrderedByLatest()
        verify(homeView).showProgress()
        verify(homeView).hideProgress()
        verify(homeView).showData(project.projects)
        verifyNoMoreInteractions(homeView, projectsRepository)

    }

    @Test
    fun start_Error() {
        //given
        whenever(projectsRepository.getProjectsOrderedByLatest())
            .thenReturn(Maybe.error(IllegalStateException("Error: Unknown")))
        //when
        presenter.start()

        //then
        verify(projectsRepository).getProjectsOrderedByLatest()
        verify(homeView).showProgress()
        verify(homeView).hideProgress()
        verify(homeView).showError("Error: Unknown")
        verifyNoMoreInteractions(homeView, projectsRepository)
    }

}