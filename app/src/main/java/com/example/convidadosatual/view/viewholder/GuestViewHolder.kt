package com.example.convidadosatual.view.viewholder

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.convidadosatual.R
import com.example.convidadosatual.listener.GuestListener
import com.example.convidadosatual.service.model.GuestModel

class GuestViewHolder(itemView : View,private val listener: GuestListener): RecyclerView.ViewHolder(itemView) {
    //trata valores para layout
    fun bind(guest: GuestModel ){
        val textName = itemView.findViewById<TextView>(R.id.text_name)
        textName.text = guest.name

        //click normal
        textName.setOnClickListener{
            listener.onClick(guest.id)
        }
        //click mais demorado
        textName.setOnLongClickListener{

            //criar mensagem
            AlertDialog.Builder(itemView.context)
                .setTitle(R.string.guest_title_remove)
                .setMessage(R.string.guest_mensage_remove)
                .setPositiveButton(R.string.guest_remove){ dialog,which->
                    listener.onDelete(guest.id)
                }
                .setNeutralButton(R.string.guest_cancel,null)
                .show()
            true
        }
    }
}