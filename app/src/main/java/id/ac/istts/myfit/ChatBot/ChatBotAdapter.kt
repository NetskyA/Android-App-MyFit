package id.ac.istts.myfit.ChatBot

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.Data.ChatBot
import id.ac.istts.myfit.R

class ChatBotAdapter (
    var data: MutableList<ChatBot>,
): RecyclerView.Adapter<ChatBotAdapter.ViewHolder>() {
    class ViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val chatBotContraintLayout: View = row.findViewById(R.id.chatBot_ConstrainLayout)
        val chatBotLinearLayout: LinearLayout = row.findViewById(R.id.innerLinearLayoutChatBot)
        val tvMsg: TextView = row.findViewById(R.id.tv_send_bot)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val groupLayout = LayoutInflater.from(parent.context)
        return ViewHolder(groupLayout.inflate(
            R.layout.send_msg_bot,parent,false
        ))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val f = data[position]
        holder.tvMsg.text = f.message
        if(f.side != "bot") {
            holder.chatBotLinearLayout.gravity = Gravity.RIGHT
            holder.tvMsg.setBackgroundResource(R.drawable.borderwhites)
        }
    }
}
