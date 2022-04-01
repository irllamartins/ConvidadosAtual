package com.example.convidadosatual.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidadosatual.service.model.GuestModel
import com.example.convidadosatual.service.repository.GuestRepository

class GuestFormViewModel(application: Application): AndroidViewModel(application) {

    //salva o acesso ao repositorio
    private val mContext= application.applicationContext
    private var mGuestRepository : GuestRepository = GuestRepository.getInstance(mContext)
    private var mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mSaveGuest

    fun save(name: String, presence: Boolean){
        //entidade
        val guest= GuestModel(name = name,presence = presence)
        mGuestRepository.save(guest)
    }
}