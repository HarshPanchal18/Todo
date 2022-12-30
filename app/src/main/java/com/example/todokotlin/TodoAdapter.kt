package com.example.todokotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.todo_row.view.*

class TodoAdapter(var todoContext: Context, var resources:Int, var todos:List<TodoModel>) :
    ArrayAdapter<TodoModel>(todoContext,resources,todos) {
    private val dbhelper=DBhelper(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutinflater=LayoutInflater.from(todoContext)
        val view:View=layoutinflater.inflate(resources,null)

        val todo:TodoModel=todos[position]
        view.hourtxt.text=todo.hour
        view.todotxt.text=todo.title
        view.isdonecheck.isChecked=todo.isDone==1

        view.deletebtn.setOnClickListener {
            dbhelper.delete(view.todotxt.text.toString(),view.hourtxt.text.toString())
            Toast.makeText(context,"Note: ${view.todotxt.text} is Deleted",Toast.LENGTH_SHORT).show()
        }
        return view
    }
}
