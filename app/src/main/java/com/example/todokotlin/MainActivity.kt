package com.example.todokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbhelper=DBhelper(this)
        val todolist:ArrayList<TodoModel> =dbhelper.getAll()
        val adapter=TodoAdapter(applicationContext,R.layout.todo_row,todolist)
        todolistview.adapter=adapter
    }

    fun addTodo(view: View){
        AddTodoBottomSheet().apply{
            show(supportFragmentManager,AddTodoBottomSheet.TAG)
        }
    }
}
