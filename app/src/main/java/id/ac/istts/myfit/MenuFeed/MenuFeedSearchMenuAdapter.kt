package id.ac.istts.myfit.MenuFeed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.Data.tempUser
import id.ac.istts.myfit.R

class MenuFeedSearchMenuAdapter (
    val data: List<String>,
    val onDetailClickListener:(String) -> Unit
): RecyclerView.Adapter<MenuFeedSearchMenuAdapter.ViewHolder>() {
    class ViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val groupLayout = LayoutInflater.from(parent.context)
        return ViewHolder(groupLayout.inflate(
            R.layout.item_card_search_opened,parent,false
        ))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val f = data[position]

    }

}