package id.ac.istts.myfit.MenuDaily

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.R

class SelectedDayMenusAdapter (
    val data: ArrayList<String>
): RecyclerView.Adapter<SelectedDayMenusAdapter.ViewHolder>() {
    class ViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val tvTitle: TextView = row.findViewById(R.id.openedDaily_tvTitle)
        val tvLikesValue: TextView = row.findViewById(R.id.openedDaily_tvLikesValue)
        val ivLikes: ImageView = row.findViewById(R.id.openedDaily_ivLikes)
        /*val tvDescription: TextView = row.findViewById(R.id.openedDaily_tvDescription)*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val groupLayout = LayoutInflater.from(parent.context)
        return ViewHolder(groupLayout.inflate(
            R.layout.opened_daily_menu_item,parent,false
        ))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val f = data[position]


    }
}