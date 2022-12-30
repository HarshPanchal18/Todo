package com.example.todokotlin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBhelper(context: Context?) : SQLiteOpenHelper(context,"todo",null,1) {
    private val TABLE="todos"

    companion object {
        const val TITLE:String="title"
        const val HOUR:String="hour"
        const val ISDONE:String="isDone"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table if not exists $TABLE ($TITLE varchar, $HOUR varchar, $ISDONE tinyint)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}

    fun add(title:String,hour:String){
        val values=ContentValues()
        values.put(TITLE,title)
        values.put(HOUR,hour)
        values.put(ISDONE,0)
        writableDatabase.insert(TABLE,null,values)
    }

    fun delete(title:String,hour:String)
    { writableDatabase.delete(TABLE,"title='$title' and hour='$hour'",null) }

    fun getAll():ArrayList<TodoModel>{
        val todolist=ArrayList<TodoModel>()
        try {
            val cursor=writableDatabase.rawQuery("select * from $TABLE",null)
            val titleIth=cursor.getColumnIndex("title")
            val hourIth=cursor.getColumnIndex("hour")
            val isDoneIth=cursor.getColumnIndex("isDone")
            cursor.moveToFirst()

            while(cursor!=null){
                todolist.add(
                    TodoModel(
                        cursor.getString(titleIth),
                        cursor.getString(hourIth),
                        cursor.getInt(isDoneIth)
                    )
                )
                cursor.moveToNext()
            }
            cursor?.close()
        } catch (e:Exception) { e.printStackTrace() }
        return todolist
    }
}
