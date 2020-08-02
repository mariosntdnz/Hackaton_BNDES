package com.example.hackatonbndes.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.hackatonbndes.database.entity.ClienteEntity

@Dao
interface ClienteDao {
    @Insert
    fun insertAll(cliente: ClienteEntity?)

    // Retorna uma lista com todos nomes e senhas n list pode usar pra validar o usurio
    @Query("SELECT nome, senha, email, cpf FROM cliente")
    fun getAll() : List<ClienteEntity?>?
}