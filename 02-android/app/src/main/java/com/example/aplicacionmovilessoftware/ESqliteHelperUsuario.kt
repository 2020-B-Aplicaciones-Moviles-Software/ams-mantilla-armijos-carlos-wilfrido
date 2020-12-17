package com.example.aplicacionmovilessoftware

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class ESqliteHelperUsuario(
        contexto: Context?
) : SQLiteOpenHelper(
        contexto,  //Contexto
        "moviles",  //Nombre de la BDD
        null,
        1
){
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTabUsuario =
                """
                    CREATE TABLE USUARIO (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre VARCHAR(50),
                    descripcion VARCHAR(50)
                    )
                    """.trimIndent()
        Log.i("bdd", "Creando la tabla de usuario")
        db?.execSQL(scriptCrearTabUsuario)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun consultarUsuarioPorId(id: Int): EUsuarioBDD{
        val scriptConsultarUsuario = "SELECT * FROM USUARIO WHERE id = ${id}"

        val conexionLectura = readableDatabase

        val resultadoConsulta = conexionLectura
                .rawQuery(
                        scriptConsultarUsuario,
                        null
                )

        val existeUsuario = resultadoConsulta.moveToFirst()
        val usuarioEncontrado = EUsuarioBDD(0, "", "")

        if (existeUsuario){
            do {
                val id = resultadoConsulta.getInt(0)  //Columna indice 0 -> ID
                val nombre = resultadoConsulta.getString(1) //Columna indice 1 -> NOMBRE
                val descripcion = resultadoConsulta.getString(2) //Columna indice 2 -> DESCRIPCION
                if (id != null){
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.descripcion = descripcion
                }
            } while (resultadoConsulta.moveToNext())
        }
        resultadoConsulta.close()
        conexionLectura.close()

        return usuarioEncontrado
    }

    fun crearUsuarioFormulario(
            nombre: String,
            descripcion: String
    ): Boolean {
        val conexionEscritura = writableDatabase

        val valoresAGuardar = ContentValues()

        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descripcion", descripcion)

        val resultadoEscritura: Long = conexionEscritura
                .insert(
                        "USUARIO",
                        null,
                        valoresAGuardar
                )
        conexionEscritura.close()
        return if (resultadoEscritura.toInt() == -1) false else true
    }
    fun actualizarUsuarioFormulario(
            nombre: String,
            descripcion: String,
            idActualizar: Int
    ): Boolean{
        val conexionEscritura = writableDatabase

        val valoresAActualizar = ContentValues()

        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("descripcion", descripcion)

        val resultadoActualizacion = conexionEscritura
                .update(
                        "USUARIO",  // Nombre tabla
                        valoresAActualizar,  // Valores a actualizar
                        "id = ?",  //Clausula Where
                        arrayOf(
                                idActualizar.toString()
                        ) // Parametros clausula Where
                )
        conexionEscritura.close()
        return if (resultadoActualizacion.toInt() == -1) false else true
    }

    fun eliminarUsuarioFormulario(id: Int): Boolean{
        val conexionEscritura = writableDatabase

        val resultadoEliminacion = conexionEscritura
                .delete(
                        "USUARIO",
                        "id=?",
                        arrayOf(
                                id.toString()
                        )
                )
        conexionEscritura.close()

        return if (resultadoEliminacion.toInt() == -1) false else true
    }
}