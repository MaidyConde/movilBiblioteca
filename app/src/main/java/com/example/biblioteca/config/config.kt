package com.example.biblioteca.config

class config {
    //se crea una url estatica para consultar sin instanciar
    //metodo companion object sirve para almacenar  las
    //variables static
    companion object{
        val urlBase="http://10.192.92.110:8080/api/v1/";
        val urlLibro= urlBase+"Libro/"




    }
}