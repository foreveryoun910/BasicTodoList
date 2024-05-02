package com.young.basictodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.young.basictodolist.adapter.TodoAdapter
import com.young.basictodolist.data.TodoEntity
import com.young.basictodolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    var list = arrayListOf<TodoEntity>()
    var adapter = TodoAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}