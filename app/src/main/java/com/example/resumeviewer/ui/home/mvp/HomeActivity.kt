package com.example.resumeviewer.ui.home.mvp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.resumeviewer.R
import com.example.resumeviewer.base.Item
import com.example.resumeviewer.base.ItemSelectedListener
import com.example.resumeviewer.common.PROJECT_KEY
import com.example.resumeviewer.models.Project
import com.example.resumeviewer.ui.details.mvp.DetailsActivity
import com.example.resumeviewer.ui.home.adapter.ProjectsAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HomeContract.View, ItemSelectedListener {


    @Inject
    lateinit var presenter: HomeContract.Presenter

    private val projectsAdapter = ProjectsAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        rvProjects.layoutManager = LinearLayoutManager(this)
        rvProjects.adapter = projectsAdapter
        presenter.start()
    }

    override fun showData(data: List<Project>) {
        projectsAdapter.setData(data)
    }

    override fun showProgress() {
        pbHomeProgress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pbHomeProgress.visibility = View.GONE
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onItemSelected(item: Item) {
       presenter.onItemSelected(item)
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    override fun launchDetailsActivity(project: Project) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(PROJECT_KEY, project)
        startActivity(intent)
    }
}
