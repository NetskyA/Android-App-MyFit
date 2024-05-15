package id.ac.istts.myfit.MenuDiet

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.Data.MenuDietData
import id.ac.istts.myfit.R

class MenuDietAdapter (
    var data: MutableList<MenuDietData>,
    var status:Boolean?=false,
    val onDetailClickListener:(MenuDietData,Boolean) -> Unit
): RecyclerView.Adapter<MenuDietAdapter.ViewHolder>() {
    class ViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val imageFeed: ImageView = row.findViewById(R.id.imagesmenudiet)
        val cancelimage: ImageView = row.findViewById(R.id.cancelmenudiet)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val groupLayout = LayoutInflater.from(parent.context)
        return ViewHolder(groupLayout.inflate(
            R.layout.menuitemdiet,parent,false
        ))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val f = data[position]
        if(status==true){
            holder.cancelimage.visibility = View.VISIBLE
        }else {
            holder.cancelimage.visibility = View.GONE
        }
        if(f.image!=""){
            holder.imageFeed.setImageBitmap(decodeBase64ToBitmap(f.image.toString()))
        }
        holder.imageFeed.setOnClickListener {
            onDetailClickListener.invoke(f,status!!)
        }
    }

    fun decodeBase64ToBitmap(base64Str: String): Bitmap {
        val imageBytes = Base64.decode(base64Str, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }
}