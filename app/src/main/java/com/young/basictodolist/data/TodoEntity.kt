package com.young.basictodolist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoEntity (
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "task") var task: String,
    @ColumnInfo(name = "memo") var memo: String,
    @ColumnInfo(name = "importance") var importance: Int?,
    @ColumnInfo(name = "category") var category: String,
    @ColumnInfo(name = "date") var date: Long,
    @ColumnInfo(name = "time") var time: Long,
    @ColumnInfo(name = "isFinished") var isFinished: Boolean
)
