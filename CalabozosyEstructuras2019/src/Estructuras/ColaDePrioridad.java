/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Clases.Jugador;

/**
 *
 * @author Fabio
 */
public class ColaDePrioridad {

    private Lista colaPrioridad;


    public void cargarJugador(Jugador unJ) {
        if (colaPrioridad.esVacia()) {
            colaPrioridad.insertar(unJ, colaPrioridad.longitud() + 1);
        } else {
            int i = 1;
            Jugador aux = (Jugador) colaPrioridad.recuperar(i);
            while (aux != null && (unJ.getCategoriaInt() <= aux.getCategoriaInt())) {
                i++;
                aux = (Jugador) colaPrioridad.recuperar(i);
            }
            colaPrioridad.insertar(unJ, i);
        }
    }

    public Jugador obtenerFrente() {
        Jugador unJ = null;
        if (!colaPrioridad.esVacia()) {
            unJ = (Jugador) colaPrioridad.recuperar(1);
            colaPrioridad.eliminar(1);
        }

        return unJ;
    }

    public int longitud() {
        int largo;
        largo = colaPrioridad.longitud();
        return largo;
    }
    
    

}
