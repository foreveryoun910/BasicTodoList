package com.young.basictodolist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoEntity (
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "importance") var importance: Int,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "category") var category: String,
    @ColumnInfo(name = "date") var date: Long,
    @ColumnInfo(name = "time") var time: Long,
    @ColumnInfo(name = "isFinished") var isFinished: Int
)
