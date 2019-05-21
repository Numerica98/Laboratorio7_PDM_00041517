package com.numerica98.laboratorio7_pdm_00041517.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.numerica98.laboratorio7_pdm_00041517.database.GithubRepo
import com.numerica98.laboratorio7_pdm_00041517.database.RoomDB
import com.numerica98.laboratorio7_pdm_00041517.repository.GithubRepoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
* Debo pasarle al constructor el contexto de la aplicacion
* La clase debe ser de tipo AndroidViewModel y se le debe pasar esa aplicacion*/

class GithubRepoViewModel(app: Application) : AndroidViewModel(app) {
    private val repository : GithubRepoRepository //variable privada en el repositorio

    init { //es como un constructor para obtener la instancia del GithubReposotory
        /*repoDao inicializa el dao
        * de la forma: clase ya creada RoomDB (es decir la refernecia de mi base de datos, la instancia que le paso por parametro el contexto de la aplicacion...
         *... y mando a llamar a repoDao() que es una clase abstracta declarada en RoomDB
        * */
        val repoDao = RoomDB.getInstance(app).repoDao()
        repository = GithubRepoRepository(repoDao) //Se le pasa un dao creado porque eso es lo que me pedia la clase GithubRepository
    }

    //Saco todas las funciones que me interesen del reposotorio para la UI

    //Ya no es suspend porque aqui estoy realizando el launch, basicamente la instruccion es "agarra esto de aca y mandalo"
    //Scope es como un contexto o perspectiva que se le esta mandando
    //Dispatchers.IO es el encargado de agarrar el codigo, llevarlo a donde sea y traerlo de vuelta
    //Pasa lo que va a ser el lauch que es una funcion suspend
    fun insert(repo: GithubRepo) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(repo)
    }

    fun getAll(): LiveData<List<GithubRepo>> = repository.getAll()

    fun nukeAll() = repository.nuke()
}