package id.ac.istts.myfit.MenuFeed

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.Data.tempUser
import id.ac.istts.myfit.R

class MenuFeedSearchAdapter (
    val data: List<tempUser>,
    val onDetailClickListener:(tempUser) -> Unit
): RecyclerView.Adapter<MenuFeedSearchAdapter.ViewHolder>() {
    class ViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val ivProfile: ImageView = row.findViewById(R.id.itemSearch_ivProfile)
        val tvProfileName: TextView = row.findViewById(R.id.itemSearch_tvProfileName)
       /* val ivRemove: ImageView = row.findViewById(R.id.itemSearch_ivRemove)*/
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

        holder.tvProfileName.setText(f.username)
        if(f.image!=""){
            holder.ivProfile.setImageBitmap(decodeBase64ToBitmap(f.image.toString()))
        }
//        holder.ivProfile.setOnClickListener {
//            onDetailClickListener?.invoke(f)
//        }
//        holder.tvProfileName.setOnClickListener {
//            onDetailClickListener?.invoke(f)
//        }
//        holder.ivRemove.setOnClickListener {
//            // TODO: Remove from search
//        }
    }
    fun decodeBase64ToBitmap(base64Str: String): Bitmap {
        val imageBytes = Base64.decode(base64Str, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }
}