package com.numerica98.laboratorio7_pdm_00041517.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.numerica98.laboratorio7_pdm_00041517.database.GithubDAO
import com.numerica98.laboratorio7_pdm_00041517.database.GithubRepo

class GithubRepoRepository(private val repoDao: GithubDAO) { //Le pido un DAO del tipo de mi entidad
    //Debe realizarse el suspend porque es una corrutina llamada de otra clase cuya funcion tambien lleva suspend

    @WorkerThread //Indica que el método anotado solo debe invocarse en un subproceso de trabajo. Si el elemento anotado es una clase, todos los métodos de la clase deben llamarse en un subproceso de trabajo

    suspend fun insert(repo: GithubRepo){
        repoDao.insert(repo) //realiza el metodo declarado en la interfaz de GithubDao
    }

    fun getAll(): LiveData<List<GithubRepo>> =repoDao.getAllRespos()

    fun nuke() = repoDao.nukeTable()

    /*
    * El reposotorio devuelve lo mismo y tiene los mismos metodos que el dao, esto se hace para mejor manejo*/
}