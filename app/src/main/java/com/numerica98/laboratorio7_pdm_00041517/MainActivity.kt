package com.numerica98.laboratorio7_pdm_00041517

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.numerica98.laboratorio7_pdm_00041517.database.GithubRepo
import com.numerica98.laboratorio7_pdm_00041517.viewmodel.GithubRepoViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Crear una variable para usar ViewModelProviders (no olvidar la "s" o no funcionara)
        //.of(contexto en el que esta (en este caso una actividad)).get(le mando la clase) y con eso ya se tiene el ViewModel
        val viewModel = ViewModelProviders.of(this).get(GithubRepoViewModel::class.java)

        //Esta practica consistia en que se tenia un editText y al apretar el boton, agarraba el valor y creaba algo
        bt_add.setOnClickListener {
            val name = et_name.text.toString() //Agarra el texto del editText y lo pasa a string

            //Revisa que no venga vacio o este en blanco
            if (name.isNotEmpty() && name.isNotBlank()){
                viewModel.insert(GithubRepo(name)) //Manda una entidad con un atributo y lo inserta en la base de datos
            }
        }

        //Llama a una funcion que me devuelve un LiveData
        //.observe(le doy un contexto (osea esta actividad), el objeto Observer en sí (que lo unico que hace es ejecutar este codigo cuando cambie))
        //en este caso se definio que lo que va a venir es una variable llamada repos
        viewModel.getAll().observe(this, Observer {repos->
            //Cuando venga repos entonces hace esto
            Log.d("LISTA DE REPOS", "--------------") //Solo es para debuguearlos, que ponga una lista y va a iterar esos repos que vienen
            //Aqui los imprime
            for (repo in repos){
                Log.d("LISTA DE REPOS: ", repo.name)
            }
        })
    }
}
