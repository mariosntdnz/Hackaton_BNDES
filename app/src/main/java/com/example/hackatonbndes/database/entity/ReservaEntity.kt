package com.example.hackatonbndes.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reserva")
public class ReservaEntity(
    @PrimaryKey
    var CPF : String,
    var CNPJ: String,
    var lugares_reservados : Int,
    var dia : Int,
    var mes : Int,
    var ano : Int,
    var horario_chegar : Int,
    var horario_sair : Int
){}
