package com.example.convidadosatual.service.repository

import com.example.convidadosatual.service.model.GuestModel

class GuestRepository {
    //rsponsavel por exibir todas as pessoas da lista
    fun getAll():List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    //rsponsavel por exibir todas as pessoas presente
    fun getPresent() :List<GuestModel>{
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    //rsponsavel por exibir todas as pessoas ausentes
    fun getAbsent():List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    //CRUD

    //responsavel por salvar
    fun save(guest: GuestModel) {

    }

    //responsavel por atualizar
    fun update(guest: GuestModel) {

    }

    //responsavel por atualizar
    fun delete(guest: GuestModel) {

    }
}