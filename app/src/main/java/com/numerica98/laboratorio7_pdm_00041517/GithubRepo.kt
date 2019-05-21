package com.numerica98.laboratorio7_pdm_00041517

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "repos") //Lo importante aqui es la notacion Entity para identificarlo como entidad y definirle el nombre a la tabla
data class GithubRepo (
    @ColumnInfo(name = "s_name") //Nombre de la columna de la tabla
    val name: String //si no se pone la notacion @ColumInfo la columna tomara automaticamente el nombre de la variable
) {
    //En este caso el id no se require cuando se crea la tabla, asi que se omite en  los parametros
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}