package com.young.basictodolist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.young.basictodolist.R
import com.young.basictodolist.data.TodoEntity
import java.util.Random

class TodoAdapter(val list: List<TodoEntity>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemId(position: Int): Long {
        return list[position].id?.toLong() ?: 0L
    }

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(todoEntity: TodoEntity) {
            with(itemView) {
                val colors = resources.getIntArray(R.array.random_color)
                val randomColor = colors[(Random().nextInt(colors.size))]
                //viewColorTag.setBackgroundColor(randomColor)
            }
        }
    }

}