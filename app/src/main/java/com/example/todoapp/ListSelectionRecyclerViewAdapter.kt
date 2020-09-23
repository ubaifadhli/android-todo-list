package com.example.todoapp

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class ListSelectionRecyclerViewAdapter (private val lists : ArrayList<String>, private val mainContext : Context) : RecyclerView.Adapter<ListSelectionViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_selection_view_holder, parent, false)

        return ListSelectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.listPosition.text = (position + 1).toString()
        holder.listTitle.text = lists.get(position)

        holder.listLayout.setOnClickListener {
            var detailDialog = Dialog(mainContext)
            detailDialog.setContentView(R.layout.detail_dialog)

            var todoNameEditText = detailDialog.findViewById(R.id.todo_name_edittext) as EditText
            var updateButton = detailDialog.findViewById(R.id.update_button) as Button
            var deleteButton = detailDialog.findViewById(R.id.delete_button) as Button

            updateButton.setOnClickListener {
                if(todoNameEditText.text.toString() != "") {
                    lists.add(position, todoNameEditText.text.toString())
                    lists.removeAt(position + 1)
                    notifyDataSetChanged()

                }
                detailDialog.dismiss()
            }

            deleteButton.setOnClickListener {
                deleteTodoAt(position)
                notifyDataSetChanged()
                detailDialog.dismiss()
            }

            todoNameEditText.setHint(lists.get(position))

            detailDialog.show()

        }
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    fun addTodo (todoName : String) {
        lists.add(todoName)
        notifyItemInserted(lists.size - 1)
    }

    fun deleteTodoAt(position: Int) {
        lists.removeAt(position)
        notifyDataSetChanged()
    }
}