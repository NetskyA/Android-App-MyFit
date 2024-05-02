package id.ac.istts.myfit.ProfileSetting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.R

class MenuProfileAdapter (
    val data: ArrayList<String>,
    val onDetailClickListener:(String) -> Unit
): RecyclerView.Adapter<MenuProfileAdapter.ViewHolder>() {
    class ViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val imageFeed: ImageView = row.findViewById(R.id.imagesfeed)
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
        val f = data[position]

        holder.imageFeed.setOnClickListener {
            onDetailClickListener?.invoke(f)
        }
    }
}