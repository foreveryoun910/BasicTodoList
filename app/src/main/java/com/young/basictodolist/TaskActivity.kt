package com.young.basictodolist

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TimePicker
import com.young.basictodolist.data.AppDatabase
import com.young.basictodolist.data.TodoEntity
import com.young.basictodolist.databinding.ActivityTaskBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
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
                setTimeListener()
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

    private fun setTimeListener() {
        myCalendar = Calendar.getInstance()

        timeSetListener =
            TimePickerDialog.OnTimeSetListener { _: TimePicker, hourOfDay: Int, min: Int ->
                myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                myCalendar.set(Calendar.MINUTE, min)
                updateTime()
            }
        val timePickerDialog = TimePickerDialog(
            this, timeSetListener, myCalendar.get(Calendar.HOUR_OF_DAY),
            myCalendar.get(Calendar.MINUTE), false
        )
        timePickerDialog.show()
    }

    private fun updateTime() {
        val myFormat = "h:mm a"
        val sdf = SimpleDateFormat(myFormat)
        finalDate = myCalendar.time.time
        binding.edtDate.setText(sdf.format(myCalendar.time))
        binding.tilTime.visibility = View.VISIBLE
    }
}