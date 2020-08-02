package com.example.hackatonbndes.database.entity;

import android.content.Intent;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "restaurante")
data class RestauranteEntity (
     @PrimaryKey
     val CNPJ : String ,
     val nome : String,
     val endereco : String,
     val vagas : Int,
     val horario_abrir : Int,
     val horario_fechar : Int
){}

