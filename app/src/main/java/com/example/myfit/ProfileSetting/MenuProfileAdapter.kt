package com.example.myfit.ProfileSetting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.myfit.R

class MenuProfileAdapter (
    val data: ArrayList<String>
): RecyclerView.Adapter<MenuProfileAdapter.ViewHolder>() {
    class ViewHolder(val row: View) : RecyclerView.ViewHolder(row) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val groupLayout = LayoutInflater.from(parent.context)
        return ViewHolder(groupLayout.inflate(
            R.layout.contentfeed,parent,false
        ))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Rvlayout = data[position]
    }
}