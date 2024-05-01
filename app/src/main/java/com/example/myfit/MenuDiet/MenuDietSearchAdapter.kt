package com.example.myfit.MenuDiet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfit.R

class MenuDietSearchAdapter (
    val data: ArrayList<String>,
    val onDetailClickListener:(String) -> Unit
): RecyclerView.Adapter<MenuDietSearchAdapter.ViewHolder>() {
    class ViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val imageFeed: ImageView = row.findViewById(R.id.imvCircular)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val groupLayout = LayoutInflater.from(parent.context)
        return ViewHolder(groupLayout.inflate(
            R.layout.searchmenuitemdiet,parent,false
        ))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val f = data[position]

        holder.imageFeed.setOnClickListener {
            onDetailClickListener?.invoke(f)
        }
    }
}