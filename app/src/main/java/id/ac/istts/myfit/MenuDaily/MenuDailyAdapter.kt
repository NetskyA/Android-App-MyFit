package id.ac.istts.myfit.MenuDaily

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

class MenuDailyAdapter (
    var data: MutableList<MenuDietData>,
    var status:Boolean?=false,
    val onDetailClickListener:(MenuDietData, Boolean) -> Unit
): RecyclerView.Adapter<MenuDailyAdapter.ViewHolder>() {
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

    }
}
