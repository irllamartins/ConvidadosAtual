package com.example.convidadosatual.service.repository

import android.content.ContentValues
import android.content.Context
import com.example.convidadosatual.service.DataBaseConstants
import com.example.convidadosatual.service.model.GuestModel
import java.lang.Exception

class GuestRepository private constructor(context: Context){
    private var mGuestDataBaseHelper :GuestDataBaseHelper = GuestDataBaseHelper(context)

    //metodo estatico
    //singleton->instancia a classe e só ele pode ti dar uma instancia
    companion object{
        private lateinit var repository: GuestRepository

        //instancia da classe
        fun getInstance(context: Context):GuestRepository{
            if(!::repository.isInitialized){
                repository = GuestRepository(context)
            }
            return repository
        }
    }
    //fim singleton
    //responsavel por salvar
    fun save(guest: GuestModel):Boolean {
        return try{
            val db = mGuestDataBaseHelper.writableDatabase
            val contentValues = ContentValues()

            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

            //insere
            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, contentValues)
            true
        }catch (e:Exception){
            false
        }
    }
    //responsavel por exibir todas as pessoas da lista
    fun getAll():List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    //responsavel por exibir todas as pessoas presente
    fun getPresent() :List<GuestModel>{
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    //responsavel por exibir todas as pessoas ausentes
    fun getAbsent():List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    //CRUD

    //responsavel por atualizar
    fun update(guest: GuestModel):Boolean {
        return try{
            val db = mGuestDataBaseHelper.writableDatabase
            val contentValues = ContentValues()

            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

            //captura o id da class guest
            val selection = DataBaseConstants.GUEST.COLUMNS.ID+" = ?"
            //lista de capturas de id de pessoas
            val args = arrayOf(guest.id.toString())
            db.update(DataBaseConstants.GUEST.TABLE_NAME, contentValues, selection,args)
            true
        }catch (e:Exception){
            false
        }
    }

    //responsavel por atualizar
    fun delete(guest: GuestModel) {

    }
}