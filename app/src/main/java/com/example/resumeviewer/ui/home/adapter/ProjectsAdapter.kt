package com.example.resumeviewer.ui.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.resumeviewer.R
import com.example.resumeviewer.base.ItemSelectedListener
import com.example.resumeviewer.common.inflate
import com.example.resumeviewer.models.Project
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_project.*

class ProjectsAdapter(private val listener: ItemSelectedListener) :
    RecyclerView.Adapter<ProjectsAdapter.ProjectViewHolder>() {

    private val projects = mutableListOf<Project>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProjectViewHolder(parent.inflate(R.layout.item_project))

    override fun getItemCount() = projects.size

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) = holder.bind(projects[position])

    fun setData(data: List<Project>) {
        projects.clear()
        projects.addAll(data)
        notifyDataSetChanged()
    }

    inner class ProjectViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(project: Project) = with(project) {
            tvProjectTitle.text = companyName
            tvProjectDuration.text = containerView.context.getString(R.string.project_duration_text, startDate, endDate)
            containerView.setOnClickListener { listener.onItemSelected(project) }
        }
    }


}