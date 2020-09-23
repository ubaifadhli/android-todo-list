package com.example.todoapp

import android.os.Bundle
import android.os.Parcel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity() : AppCompatActivity(){

    lateinit var listsRecyclerView: RecyclerView
    lateinit var fab : FloatingActionButton
    lateinit var lists : ArrayList<String>

    constructor(parcel: Parcel) : this() {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        listsRecyclerView = findViewById(R.id.lists_recyclerview)
        fab = findViewById(R.id.fab)

        lists = createDummyList()

        listsRecyclerView.layoutManager = LinearLayoutManager(this)
        listsRecyclerView.adapter = ListSelectionRecyclerViewAdapter(lists, this)

        fab.setOnClickListener { showCreateListDialog() }
    }

    private fun showCreateListDialog() {
        val dialogTitle = getString(R.string.name_of_list)
        val positiveButtonTitle = getString(R.string.create_list)

        val builder = AlertDialog.Builder(this)

        val dialogView = this.layoutInflater.inflate(R.layout.create_dialog, null)

        builder.setView(dialogView)

        val createDescriptionTextView = dialogView.findViewById(R.id.create_description_textview) as TextView

        createDescriptionTextView.text = dialogTitle

        builder.setPositiveButton(positiveButtonTitle) { dialog, _ ->
            var newTodo = dialogView.findViewById<EditText>(R.id.new_todo_edittext).text.toString()

            val recyclerAdapter = listsRecyclerView.adapter as ListSelectionRecyclerViewAdapter
            recyclerAdapter.addTodo(newTodo)

            dialog.dismiss()
        }

        builder.create().show()
    }

    fun createDummyList () : ArrayList<String> {
        val lists = ArrayList<String>()
        lists.add("Hello")
        lists.add("World")
        lists.add("Test")

        return lists
    }
}