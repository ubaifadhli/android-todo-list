package com.example.todoapp

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListSelectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val listPosition = itemView.findViewById(R.id.item_number) as TextView
    val listTitle = itemView.findViewById(R.id.item_string) as TextView
    var listLayout = itemView.findViewById(R.id.parentLayout) as LinearLayout
}