package com.example.resumeviewer.ui.details.mvp

import android.os.Bundle
import android.support.design.chip.Chip
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.resumeviewer.R
import com.example.resumeviewer.common.PROJECT_KEY
import com.example.resumeviewer.models.Project
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class DetailsActivity : AppCompatActivity(), DetailsContract.View {

    @Inject
    lateinit var presenter: DetailsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val project = intent.getParcelableExtra<Project>(PROJECT_KEY)
        presenter.start(project)

    }

    override fun showProjectTitle(title: String) {
        tvDetailsTitle.text = title
    }

    override fun showProjectDuration(duration: String) {
        tvDetailsDuration.text = duration
    }

    override fun showTagCloud(data: List<String>) {
        for (technology in data) {
            val chip = Chip(this)
            chip.text = technology
            chip.elevation = 10F
            chip.chipIcon = getDrawable(R.mipmap.ic_launcher_round)
            chip.isCheckable = false
            chip.setTextAppearance(R.style.TechChipsStyle)
            chipgroupTech.addView(chip)
        }
    }

    override fun showDescription(description: String) {
        tvDetailsDescription.text = description
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}