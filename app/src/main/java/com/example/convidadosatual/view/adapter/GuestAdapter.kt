package com.example.convidadosatual.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.convidadosatual.R
import com.example.convidadosatual.listener.GuestListener
import com.example.convidadosatual.service.model.GuestModel
import com.example.convidadosatual.view.viewholder.GuestViewHolder

class GuestAdapter: RecyclerView.Adapter<GuestViewHolder>() {

    private  var mGuestList: List<GuestModel> = arrayListOf()
    private lateinit var mListener: GuestListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        //cria as linhas
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_guest,parent,false)
        return GuestViewHolder(item,mListener)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        //atribui valores
        holder.bind(mGuestList[position])
    }

    override fun getItemCount(): Int {
        return mGuestList.count()
    }
    fun updateGuests(list: List<GuestModel>){
        mGuestList = list
        notifyDataSetChanged()
    }
    fun attachListener(listener: GuestListener){
        mListener = listener
    }
}