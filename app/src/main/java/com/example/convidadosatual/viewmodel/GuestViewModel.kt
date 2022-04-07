package com.example.convidadosatual.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidadosatual.service.constants.GuestConstants
import com.example.convidadosatual.service.model.GuestModel
import com.example.convidadosatual.service.repository.GuestRepository

class GuestViewModel(application: Application) : AndroidViewModel(application) {

    private val mGuestRepository = GuestRepository.getInstance(application.applicationContext)
    private val mGuestList = MutableLiveData<List<GuestModel>>()

    val guestList: LiveData<List<GuestModel>> = mGuestList

    fun load(filter: Int){
        if(filter == GuestConstants.FILTER.EMPTY){
            mGuestList.value= mGuestRepository.getAll()
        }
        else if (filter == GuestConstants.FILTER.EMPTY){
            mGuestList.value= mGuestRepository.getPresent()
        }
        else{
            mGuestList.value= mGuestRepository.getAbsent()
        }

    }
    fun delete(id:Int){
        mGuestRepository.delete(id)
    }
}