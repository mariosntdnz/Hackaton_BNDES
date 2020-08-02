package com.example.hackatonbndes.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey

@Entity(tableName = "cliente")
public class ClienteEntity(
    var nome : String,
    var email : String,
    @PrimaryKey
    var CPF : String,
    var senha : String
)
