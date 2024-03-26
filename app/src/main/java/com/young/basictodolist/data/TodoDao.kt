package com.young.basictodolist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {

    @Query("SELECT * FROM TodoEntity")
    fun getAllTodo(): List<TodoEntity>

    @Query("SELECT * FROM TodoEntity WHERE isFinished == 0")
    fun getOngoingTodo(): List<TodoEntity>

    @Query("SELECT * FROM TodoEntity WHERE isFinished == 1")
    fun getFinishedTodo(): List<TodoEntity>

    @Insert
    fun insertTodo(todo: TodoEntity)

    @Delete
    fun deleteTodo(todo: TodoEntity)

    @Query("UPDATE TodoEntity SET isFinished = 1 WHERE id=:uid")
    fun finishTodo(uid: Int)

}