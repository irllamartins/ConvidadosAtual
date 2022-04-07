package com.example.convidadosatual.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidadosatual.service.model.GuestModel
import com.example.convidadosatual.service.repository.GuestRepository

class GuestFormViewModel(application: Application): AndroidViewModel(application) {

    //salva o acesso ao repositorio
    private val mContext= application.applicationContext

    private var mGuestRepository : GuestRepository = GuestRepository.getInstance(mContext)

    private var mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mSaveGuest

    //carrega um usuario
    private var mGuest = MutableLiveData<GuestModel>()
    val guest: MutableLiveData<GuestModel> = mGuest

    //carregando..
    fun load(id: Int){
        mGuest.value = mGuestRepository.get(id)
    }
    //salva os dados ou se já existi atualiza
    fun save(id: Int,name: String, presence: Boolean){
        //entidade
        val guest= GuestModel(id,name,presence)
        if(id==0) {
            mSaveGuest.value = mGuestRepository.save(guest)
        }else{
            mSaveGuest.value =mGuestRepository.update(guest)
        }
    }

}