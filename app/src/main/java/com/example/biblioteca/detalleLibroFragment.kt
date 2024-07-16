package com.example.biblioteca

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.biblioteca.config.config
import com.example.biblioteca.models.libro
import com.google.gson.Gson

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [detalleLibroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class detalleLibroFragment : Fragment() {

    //DEFINIR LAS VARIABLES
    private lateinit var lblTitulo: TextView
    private lateinit var lblAutor: TextView
    private lateinit var lblIsbn: TextView
    private lateinit var lblGenero: TextView
    private lateinit var lblnumeroEjemplaresDisponibles: TextView
    private lateinit var lblnumeroEjemplaresOcupados: TextView
    //se asigna un id existente temporal
    private var id:String="b0bcce61-34ab-11ef-a36b-5c3a454d01ce"
    private lateinit var btnEditar: Button
    private lateinit var btnEliminar: Button

    /*
       Request es peticion que hace a la API
       StringRequest=responde un String
       JsonRequest=responde un json
       JsonArrayRequest=responde un arreglo de json
        */
    fun consultarLibro(){
        /*
        solo se debe consultar, si el ID
        es diferente a vacio
         */
        if(id!=""){

            var request= JsonObjectRequest(
                Request.Method.GET,//metodo de la peticion
                config.urlLibro+id, //url
                null,//parametros
                {response->
                    //variable response contiene la respuesta de la API
                    //se convierte de json a un objeto tipo libro
                    //se genera un objeto de la libreria Gson
                    val gson= Gson()
                    //se convierte
                    val libro: libro =gson.fromJson(response.toString(), libro::class.java)
                    //se modifica el atributo text de los campos con el valor del objeto
                    lblTitulo.setText(response.getString("titulo"))
                    lblAutor.setText(response.getString("autor"))
                    lblIsbn.setText(response.getString("isbn"))
                    lblGenero.setText(response.getString("genero"))
                    lblnumeroEjemplaresDisponibles.setText(response.getInt("numeroEjemplaresDisponibles").toString())
                    lblnumeroEjemplaresOcupados.setText(response.getInt("numeroEjemplaresOcupados").toString())

                },//respuesta correcta
                { error->
                    Toast.makeText(
                        context,
                        "Error al consultar",
                        Toast.LENGTH_LONG
                    ).show()
                } //error en la peticion
            )
            //se añade la peticion
            var queue= Volley.newRequestQueue(context)
            queue.add(request)
        }
    }
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_detalle_libro, container, false)
        lblAutor=view.findViewById(R.id.lblAutor)
        lblTitulo=view.findViewById(R.id.lblTitulo)
        lblGenero=view.findViewById(R.id.lblGenero)
        lblIsbn=view.findViewById(R.id.lblIsbn)
        lblnumeroEjemplaresDisponibles=view.findViewById(R.id.lblnumeroEjemplaresDisponibles)
        lblnumeroEjemplaresOcupados=view.findViewById(R.id.lblnumeroEjemplaresOcupados)
        consultarLibro()
        btnEditar=view.findViewById(R.id.btnEditar)
        btnEditar.setOnClickListener{ editarLibro() }
        btnEliminar=view.findViewById(R.id.btnEliminar)
        btnEliminar.setOnClickListener{ eliminarLibro() }
        return view
    }

    //se crea el metodo editar libro
    fun editarLibro(){

    }
    //se crea el metodo eliminar libro
    fun eliminarLibro(){

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment detalleLibroFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            detalleLibroFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}