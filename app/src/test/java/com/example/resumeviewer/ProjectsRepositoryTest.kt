package com.example.resumeviewer

import com.example.resumeviewer.base.DataSource
import com.example.resumeviewer.repo.ProjectsRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProjectsRepositoryTest : BaseTest() {
    private val localDataSource: DataSource = mock()
    private val remoteDataSource: DataSource = mock()

    private lateinit var projectsRepository: ProjectsRepository

    @Before
    override fun setup() {
        super.setup()
        projectsRepository = ProjectsRepository(localDataSource = localDataSource, remoteDataSource = remoteDataSource)
    }

    @Test
    fun getProjectsOrderedByLatest_Online_DataFetchedFromAPI() {
        //given
        val project = TestUtils.getProjects()
        whenever(remoteDataSource.getProjectsOrderedByLatest()).thenReturn(Maybe.just(project.projects))
        whenever(localDataSource.getProjectsOrderedByLatest()).thenReturn(Maybe.empty())
        //when
        val testSubscriber = projectsRepository.getProjectsOrderedByLatest().test()

        //then
        verify(remoteDataSource).getProjectsOrderedByLatest()
        verify(localDataSource).getProjectsOrderedByLatest()

        testSubscriber.assertNoErrors()
        testSubscriber.assertValueCount(1)
        testSubscriber.assertComplete()

    }

    @Test
    fun getProjectsOrderedByLatest_Offline_DataFetchedFromDatabase() {
        //given
        whenever(remoteDataSource.getProjectsOrderedByLatest())
            .thenReturn(Maybe.error(IllegalStateException("Error: Unknown")))
        whenever(localDataSource.getProjectsOrderedByLatest()).thenReturn(Maybe.empty())
        //when
        val testSubscriber = projectsRepository.getProjectsOrderedByLatest().test()

        //then
        verify(remoteDataSource).getProjectsOrderedByLatest()
        verify(localDataSource).getProjectsOrderedByLatest()

        testSubscriber.assertValueCount(0)
        testSubscriber.assertComplete()

        //and then
        val project = TestUtils.getProjects()
        whenever(localDataSource.getProjectsOrderedByLatest()).thenReturn(Maybe.just(project.projects))

        val testSubscriberTwo = projectsRepository.getProjectsOrderedByLatest().test()

        testSubscriberTwo.assertValueCount(1)
        testSubscriberTwo.assertComplete()
    }

}