package com.numerica98.laboratorio7_pdm_00041517.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//Esta notacion la toma como la encargada para crear la base de datos
//Se debe crear una lista de entidades, estas van anotadas como clases
@Database (entities = [GithubRepo::class], version = 1, exportSchema = false)
 abstract class RoomDB : RoomDatabase() {

    /*Por ser de tipo RoomDatabase construyo lo que va en esta funcion en base a la interfaz definida...
    * ... y lo genera automaticamente*/
    abstract fun repoDao(): GithubDAO //Objeto Dao del tipo de mi entidad, deben ir tantas como Daos y entidades tenga

    companion object {
        private var INSTANCE: RoomDB? = null

        fun getInstance(AppContext: Context): RoomDB {
            val temInstance = INSTANCE

            //Revisa si ya existe para devolver esa instancia, porque si viene null crea la base de datos
            if (temInstance != null) return temInstance
            synchronized(this){ //Sincroniza lo que se crea en diferentes hilos
                val instance = Room //Manda a llamar Room...
                    .databaseBuilder(AppContext, RoomDB::class.java, "RepoDB") //... hacia el punto databaseBuilder del objeto Room y le pasa: el AppContext que se tiene en el getInsntance, la clase archivo en la que estoy y un nombre para la base de datos
                    .build()//Construye una referencia a la base de datos ya creada
                INSTANCE = instance
                return instance //Solo la retorno

            }
        }
    }
}
