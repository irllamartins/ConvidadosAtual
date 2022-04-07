package com.example.convidadosatual.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.convidadosatual.R
import com.example.convidadosatual.listener.GuestListener
import com.example.convidadosatual.service.constants.GuestConstants
import com.example.convidadosatual.view.adapter.GuestAdapter
import com.example.convidadosatual.viewmodel.GuestViewModel

class AbsentFragment : Fragment() {

    private lateinit var mViewModel: GuestViewModel
    private lateinit var mListener: GuestListener
    private val mAdapter: GuestAdapter = GuestAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         mViewModel = ViewModelProvider(this).get(GuestViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_absent, container, false)
        //RecyclerView
        val recycler =root.findViewById<RecyclerView>(R.id.recycler_absent)

        //define um layout
        recycler.layoutManager = LinearLayoutManager(context)

        //adapter(cola: layout e dados )
        recycler.adapter = mAdapter

        mListener = object : GuestListener {
            //mudar de layout
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUESTID,id)

                intent.putExtras(bundle)
                startActivity(intent)

            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load(GuestConstants.FILTER.ABSENT)
            }
        }
        mAdapter.attachListener(mListener)
        observer()
        return root
    }
    override fun onResume() {
        super.onResume()

        mViewModel.load(GuestConstants.FILTER.ABSENT)
    }
    private fun observer(){
        mViewModel.guestList.observe(viewLifecycleOwner, Observer{
            mAdapter.updateGuests(it)

        })
    }

}