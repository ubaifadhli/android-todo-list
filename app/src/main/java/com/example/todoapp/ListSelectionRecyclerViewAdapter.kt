package com.example.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListSelectionRecyclerViewAdapter (private val lists : ArrayList<String>) : RecyclerView.Adapter<ListSelectionViewHolder>(){

//    val listTitles = arrayOf("Shopping List", "Chores", "Android")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_selection_view_holder, parent, false)

        return ListSelectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.listPosition.text = (position + 1).toString()
        holder.listTitle.text = lists.get(position)
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    fun addTodo (todoName : String) {
        lists.add(todoName)
        notifyItemInserted(lists.size - 1)
    }
}