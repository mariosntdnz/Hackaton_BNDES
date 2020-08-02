package com.example.hackatonbndes.repository

import com.example.hackatonbndes.database.AppDatabase
import com.example.hackatonbndes.database.entity.RestauranteEntity
import com.example.hackatonbndes.model.Restaurante
import kotlin.random.Random

class RestauranteRepository {

    private val restauranteDao = AppDatabase.getDatabase()!!.restauranteDao()

    fun getAll() : List<Restaurante>{

        return restauranteDao.getAll().map {
            Restaurante(it.nome,it.endereco,it.vagas,it.horario_abrir,it.endereco,it.CNPJ)
        }
    }

    fun add(restaurante: Restaurante){
        var r = Random
        var restauranteEntity = RestauranteEntity("CNPJOK" + r.nextInt(10).toString(),"NOMEOK"+ r.nextInt(10).toString(),"ENDEREÇO OK"+ r.nextInt(10).toString(),10,15,18)
        restauranteDao.add(restauranteEntity)
    }
}