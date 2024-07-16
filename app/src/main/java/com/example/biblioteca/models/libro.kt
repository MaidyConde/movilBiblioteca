package com.example.biblioteca.models

data class libro(
    var idLibro:String,
    var Titulo:String,
    var Autor:String,
    var ISBN:String,
    var Genero: String,
    var numeroEjemplaresDisponibles: Int,
    var numeroEjemplaresOcupados:Int
)
