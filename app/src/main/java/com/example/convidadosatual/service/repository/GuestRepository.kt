package com.example.convidadosatual.service.repository

import android.content.ContentValues
import android.content.Context
import com.example.convidadosatual.service.constants.DataBaseConstants
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

        return try{
            //banco de dados
            val db = mGuestDataBaseHelper.readableDatabase

            //projeção de valores
            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE)

            //exercução da query
            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
            )
            if(cursor!=null && cursor.count >0){
                //preencher a lista
                //enquanto tiver um proximo cursor
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow (DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndexOrThrow (DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndexOrThrow (DataBaseConstants.GUEST.COLUMNS.PRESENCE))==1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }

            }
            cursor?.close()
            list
        }catch (e:Exception){
            list
        }
    }

    //responsavel por exibir todas as pessoas presente
    fun getPresent() :List<GuestModel>{
        val list: MutableList<GuestModel> = ArrayList()
        return try{
            //banco de dados
            val db = mGuestDataBaseHelper.readableDatabase

            //projeção de valores
            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE)

            //exercução da query
            val cursor = db.rawQuery("SELECT id,name,presence FROM Guest WHERE presence = 1",null)

            if(cursor!=null && cursor.count >0){
                //preencher a lista
                //enquanto tiver um proximo cursor
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow (DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndexOrThrow (DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndexOrThrow (DataBaseConstants.GUEST.COLUMNS.PRESENCE))==1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }

            }
            cursor?.close()
            list
        }catch (e:Exception){
            list
        }
    }

    //responsavel por exibir todas as pessoas ausentes
    fun getAbsent():List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return try{
            //banco de dados
            val db = mGuestDataBaseHelper.readableDatabase

            //projeção de valores
            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE)

            //exercução da query
            val cursor = db.rawQuery("SELECT id,name,presence FROM Guest WHERE presence = 0",null)

            if(cursor!=null && cursor.count >0){
                //preencher a lista
                //enquanto tiver um proximo cursor
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow (DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndexOrThrow (DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndexOrThrow (DataBaseConstants.GUEST.COLUMNS.PRESENCE))==1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }

            }
            cursor?.close()
            list
        }catch (e:Exception){
            list
        }
    }

    fun get(id: Int):GuestModel?{
        var guest: GuestModel?=null
        return try{
            val db = mGuestDataBaseHelper.readableDatabase

            val projection = arrayOf(DataBaseConstants.GUEST.COLUMNS.NAME, DataBaseConstants.GUEST.COLUMNS.PRESENCE)

            //captura o id da class guest
            val selection = DataBaseConstants.GUEST.COLUMNS.ID+" = ?"
            //lista de capturas de id de pessoas
            val args = arrayOf(id.toString())

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                projection,
                selection,
                args,
                null,
                null,
                null
            )
            if(cursor!=null && cursor.count >0){
                cursor.moveToFirst()

                val name = cursor.getString(cursor.getColumnIndexOrThrow (DataBaseConstants.GUEST.COLUMNS.NAME))
                val presence = (cursor.getInt(cursor.getColumnIndexOrThrow (DataBaseConstants.GUEST.COLUMNS.PRESENCE))==1)

                guest = GuestModel(id, name, presence)
            }
            cursor?.close()
            guest
        }catch (e:Exception){
            guest
        }
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

    //responsavel por deletar
    fun delete(id: Int):Boolean {
        return try{
            val db = mGuestDataBaseHelper.writableDatabase

            //captura o id da class guest
            val selection = DataBaseConstants.GUEST.COLUMNS.ID+" = ?"
            //lista de capturas de id de pessoas
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME,selection,args)
            true
        }catch (e:Exception){
            false
        }
    }
}