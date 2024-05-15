package id.ac.istts.myfit.MenuDiet

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.Data.MenuSearch
import id.ac.istts.myfit.R

class MenuDietSearchAdapter (
    var data: MutableList<MenuSearch>,
    val onDetailClickListener:(MenuSearch) -> Unit,
    val onAddClickListener:(MenuSearch) -> Unit
): RecyclerView.Adapter<MenuDietSearchAdapter.ViewHolder>() {
    class ViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val imageFeed: ImageView = row.findViewById(R.id.imvCircular)
        val creator: TextView = row.findViewById(R.id.menuDaily_tvCreatorName)
        val nutrition: TextView = row.findViewById(R.id.menuDaily_tvNutrition)
        val nameMenu: TextView = row.findViewById(R.id.menuDaily_tvMenuName)
        val button: Button = row.findViewById(R.id.menuDiet_buttonAdd)
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
        holder.nameMenu.text = f.name
        holder.imageFeed.setImageBitmap(decodeBase64ToBitmap(f.image.toString()))
        holder.creator.text = "by ${f.nama_user.toString()}"
        Log.e("test",f.nama_user.toString())
        holder.nutrition.text = f.nutrition
        holder.imageFeed.setOnClickListener {
            onDetailClickListener.invoke(f)
        }
        holder.button.setOnClickListener {
            onAddClickListener.invoke(f)
        }
    }
    fun decodeBase64ToBitmap(base64Str: String): Bitmap {
        val imageBytes = Base64.decode(base64Str, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }
}