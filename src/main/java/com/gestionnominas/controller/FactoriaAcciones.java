package com.gestionnominas.controller;

import com.gestionnominas.acciones.AccionInicio;
import com.gestionnominas.acciones.AccionListar;
import com.gestionnominas.acciones.AccionModificar;
import com.gestionnominas.acciones.AccionSalario;

/**
 * Factoría abstracta encargada de instanciar el objeto Accion correspondiente (Patrón Factory).
 */
public abstract class FactoriaAcciones {

    /**
     * Crea y devuelve una instancia de Accion en función del parámetro recibido.
     *
     * @param accion Nombre simbólico de la acción solicitada.
     * @return Instancia de la clase que implementa Accion.
     */
    public static Accion creaAccion(String accion) {
        if (accion == null) {
            return new AccionInicio();
        }
        switch (accion) {
            case "listar":
                return new AccionListar();
            case "buscarSalario":
            case "mostrarSalario":
                return new AccionSalario();
            case "buscarModificar":
            case "mostrarModificar":
            case "actualizar":
                return new AccionModificar();
            case "inicio":
            default:
                return new AccionInicio();
        }
    }
}
