package com.young.basictodolist

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.young.basictodolist.data.AppDatabase
import com.young.basictodolist.data.TodoEntity
import com.young.basictodolist.databinding.ActivityTaskBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

const val DB_NAME = "todo.db"

class TaskActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityTaskBinding

    lateinit var myCalendar: Calendar
    lateinit var dateSetListener: DatePickerDialog.OnDateSetListener
    lateinit var timeSetListener: TimePickerDialog.OnTimeSetListener
    var finalDate = 0L
    var finalTime = 0L

    private val labels = arrayListOf("Personal", "Business", "Insurance", "Shopping", "Banking")

    val db by lazy {
        AppDatabase.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpSpinner()
    }

    private fun setUpSpinner() {
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, labels)
        labels.sort()
        binding.spCategory.adapter = adapter
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.edt_date -> {
                //
            }
            R.id.edt_time -> {
                //
            }
            R.id.btn_add_task -> {
                addTask()
            }
        }
    }

    private fun addTask() {
        val category = binding.spCategory.toString();
        val task = binding.tilTask.editText?.text.toString()
        val memo = binding.tilMemo.editText?.text.toString()

        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                return@withContext db?.getTodoDao()?.insertTodo(
                    TodoEntity(
                        id = null,
                        task,
                        memo,
                        importance = null,
                        category,
                        finalDate,
                        finalTime,
                        isFinished = false
                    )
                )
            }
            finish()
        }
    }
}