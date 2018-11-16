package com.example.resumeviewer.models

import android.arch.persistence.room.*
import android.os.Parcelable
import com.example.resumeviewer.base.Item
import com.example.resumeviewer.common.TABLE_NAME
import com.example.resumeviewer.db.ProjectTypeConverter
import kotlinx.android.parcel.Parcelize

@Entity(tableName = TABLE_NAME, indices = [Index("companyName", unique = true)])
@TypeConverters(ProjectTypeConverter::class)
@Parcelize
data class Project(
    @PrimaryKey(autoGenerate = true)
    val projectId: Int? = null,
    val projectNumber: Int,
    val companyName: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val tech: List<String>,
    val description: String = "") : Item, Parcelable

