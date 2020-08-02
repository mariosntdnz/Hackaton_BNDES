package com.example.hackatonbndes.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hackatonbndes.database.dao.ClienteDao
import com.example.hackatonbndes.database.dao.ReservaDao
import com.example.hackatonbndes.database.dao.RestauranteDao
import com.example.hackatonbndes.database.entity.*
import com.example.hackatonbndes.database.entity.ClienteEntity
import com.example.hackatonbndes.database.entity.ReservaEntity
import com.example.hackatonbndes.database.entity.RestauranteEntity
import com.example.hackatonbndes.model.Restaurante
import com.example.hackatonbndes.ui.activity.MainActivity


@Database(
    entities = [
        ClienteEntity::class,
        ReservaEntity::class,
        RestauranteEntity::class
    ],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun clienteDao() : ClienteDao
    abstract fun reservaDao() : ReservaDao
    abstract fun restauranteDao() : RestauranteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase (): AppDatabase? {
            if (this.INSTANCE != null) {
                return this.INSTANCE
            } else {
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        MainActivity.instance,
                        AppDatabase::class.java,
                        "ha")
                        .build()
                    this.INSTANCE = instance
                    return instance
                }
            }
        }
    }
}