package com.example.todoapp

import android.os.Bundle
import android.text.InputType
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var listsRecyclerView: RecyclerView
    lateinit var fab : FloatingActionButton
    lateinit var lists : ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        listsRecyclerView = findViewById(R.id.lists_recyclerview)
        fab = findViewById(R.id.fab)

        lists = createDummyList()

        listsRecyclerView.layoutManager = LinearLayoutManager(this)
        listsRecyclerView.adapter = ListSelectionRecyclerViewAdapter(lists)

        fab.setOnClickListener { showCreateListDialog() }
    }

    private fun showCreateListDialog() {
        val dialogTitle = getString(R.string.name_of_list)
        val positiveButtonTitle = getString(R.string.create_list)

        val builder = AlertDialog.Builder(this)
        val listTitleEditText = EditText(this)
        listTitleEditText.inputType = InputType.TYPE_CLASS_TEXT

        builder.setTitle(dialogTitle)
        builder.setView(listTitleEditText)

        builder.setPositiveButton(positiveButtonTitle) { dialog, _ ->
            var newTodo = listTitleEditText.text.toString()

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