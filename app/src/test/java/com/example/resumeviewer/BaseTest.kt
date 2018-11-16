package com.example.resumeviewer

import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
open class BaseTest {

    private val immediateScheduler = object : Scheduler() {
        override fun scheduleDirect(
            run: Runnable,
            delay: Long, unit: TimeUnit
        ): Disposable {
            return super.scheduleDirect(run, 0, unit)
        }

        override fun createWorker(): Scheduler.Worker {
            return ExecutorScheduler.ExecutorWorker(
                Executor { it.run() })
        }
    }

    @Before
    open fun setup() {
        RxJavaPlugins.setInitIoSchedulerHandler { immediateScheduler }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediateScheduler }
    }

}