package com.numerica98.laboratorio7_pdm_00041517.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao //Lo identifica como DAO Data Access Object
interface GithubDAO {

    //Inserta los datos a la tabla
    @Insert(onConflict = OnConflictStrategy.REPLACE) //Por si existe datos repetidos, esto evita que se duplique y resuelve el conflicto reemplazandolo
    //por ponerle suspend, todas las veces que lo mande a llamar de otro lugar debe ir con suspend
    suspend fun insert(repo: GithubRepo)

    //Realiza la consulta para mostrar todos los datos de la tabla repos
    @Query ("SELECT * FROM repos")
    fun getAllRespos(): LiveData<List<GithubRepo>>

    //Realiza la consulta para eliminar los datos de la tabla
    @Query("DELETE FROM repos")
    fun nukeTable()
}