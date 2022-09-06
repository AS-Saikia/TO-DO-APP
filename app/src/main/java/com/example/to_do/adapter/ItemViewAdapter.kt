package com.example.to_do.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do.R
import com.example.to_do.model.task



class ItemViewAdapter(
    private val context: Context,
    private val dataset: MutableList<task>,
) : RecyclerView.Adapter<ItemViewAdapter.ItemViewHolder>() {

    class ItemViewHolder (private val view: View) : RecyclerView.ViewHolder(view)
    {
        val title : TextView = view.findViewById(R.id.title)
        val priority: TextView = view.findViewById((R.id.priority_title))
        val delete: ImageView = view.findViewById((R.id.delete))



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemLayout = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ItemViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.title.text = item.taskTitle
        holder.priority.text = item.priorityTitle



            holder.delete.setOnClickListener {
                reArrange(position)

            }


    }

    private fun reArrange(position: Int) {

        dataset.removeAt(position)
        notifyItemRemoved(position)


    }
    fun addTask(item: task){
        dataset.add(0,item)
        notifyItemInserted(0)
    }




    override fun getItemCount(): Int {
        return dataset.size
    }
}