package id.ac.istts.myfit.MenuDaily

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.Data.MenuDietData
import id.ac.istts.myfit.R

class SelectedDayMenusAdapter (
    val data: MutableList<MenuDietData>
): RecyclerView.Adapter<SelectedDayMenusAdapter.ViewHolder>() {
    class ViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val tvTitle: TextView = row.findViewById(R.id.openedDaily_tvTitle)
        val img = row.findViewById<ImageView>(R.id.openedDaily_img)
        val tvLikesValue: TextView = row.findViewById(R.id.openedDaily_tvLikesValue)
        val nutrition : TextView = row.findViewById(R.id.customDailymenu_tvNutrition)
        val ingredients : TextView = row.findViewById(R.id.customDailymenu_tvIngredients)
        val howto : TextView = row.findViewById(R.id.customDailymenu_tvHowToMake)
        val note: TextView = row.findViewById(R.id.customDailymenu_tvNotes)
        val date : TextView = row.findViewById(R.id.openedDailydate)
        val hide : ImageButton = row.findViewById(R.id.menuDaily_hide)
        val detail: LinearLayout = row.findViewById(R.id.openedDaily_lyDescription)
        /*val ivLikes: ImageView = row.findViewById(R.id.openedDaily_ivLikes)*/
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
        holder.img.setImageBitmap(decodeBase64ToBitmap(f.image.toString()))
        holder.tvTitle.text = f.name
        holder.tvLikesValue.text = f.like.toString()
        holder.nutrition.text = f.nutrition
        holder.ingredients.text = f.ingredients
        holder.howto.text = f.how_to_make
        holder.note.text = f.note
        holder.date.text = f.date
        holder.hide.tag = 1
        holder.hide.setOnClickListener {
            if(holder.hide.tag == 1) {
                holder.detail.visibility = View.VISIBLE
                holder.hide.tag = 0
                holder.hide.setImageResource(R.drawable.hide)
            }else{
                holder.detail.visibility = View.GONE
                holder.hide.tag = 1
                holder.hide.setImageResource(R.drawable.hide_invert)
            }
        }
    }

    fun decodeBase64ToBitmap(base64Str: String): Bitmap {
        val imageBytes = Base64.decode(base64Str, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }
}