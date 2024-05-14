package id.ac.istts.myfit.MenuFeed

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.Data.Menu
import id.ac.istts.myfit.Data.RandomMenu
import id.ac.istts.myfit.ProfileSetting.MenuProfileAdapter
import id.ac.istts.myfit.R
import kotlinx.coroutines.withContext

class MenuFeedsAdapter (
    var data: MutableList<RandomMenu>,
    val onDetailClickListener:(RandomMenu) -> Unit
): RecyclerView.Adapter<MenuFeedsAdapter.ViewHolder>() {
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
        if(f.image!=""){
            holder.imageFeed.setImageBitmap(decodeBase64ToBitmap(f.image.toString()))
        }

        holder.imageFeed.setOnClickListener {
            onDetailClickListener?.invoke(f)
        }
    }
    fun decodeBase64ToBitmap(base64Str: String): Bitmap {
        val imageBytes = Base64.decode(base64Str, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }
}