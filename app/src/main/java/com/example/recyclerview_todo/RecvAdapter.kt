package com.example.recyclerview_todo

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview_todo.databinding.SingleTaskBinding

class RecvAdapter(var tasks : MutableList<Task>)
    : RecyclerView.Adapter<RecvAdapter.ViewHolder> (){
    var task: String? = null
    inner class ViewHolder (val binding: SingleTaskBinding) : RecyclerView.ViewHolder(binding.root)

    //Giving the look to each of our rows
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SingleTaskBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    //To tell the recycler view the number of items you want to display
    override fun getItemCount(): Int {
        return tasks.size
    }

    fun deleteDoneTodos() {
        tasks.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    //Assigning values to the views
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            taskText.text = tasks[position].task
            taskCheckBox.isChecked = tasks[position].isChecked
            toggleStrikeThrough(taskText, taskCheckBox.isChecked)

            taskCheckBox.setOnClickListener {
                toggleStrikeThrough(taskText, taskCheckBox.isChecked)
                tasks[position].isChecked = !tasks[position].isChecked
            }
        }
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}