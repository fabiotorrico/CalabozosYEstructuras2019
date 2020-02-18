/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Estructuras.Lista;
import java.util.Objects;

/**
 *
 * @author Fabio
 */
public class Locacion{

    private String nombre; // Nombre de la Locacion 
    private Lista equipos; //Lista de Equipos que se encuentran en la Locacion

    public Locacion(String nombre) {
        this.nombre = nombre;
        this.equipos = new Lista();
    }

    public void agregarEquipo(Equipo unE) {
       boolean exito;
       exito=this.equipos.insertar(unE, this.equipos.longitud() + 1);
    }
    

    public boolean eliminarEquipo(Equipo unE) {
        boolean exito = false;
        if (this.equipos.eliminar(this.equipos.localizar(unE))) {
            exito = true;
        }
        return exito;
    }
    public void setNombre(String nombrexx){
    this.nombre=nombrexx;
    }
    public String getNombre() {
        return nombre;
    }

    public boolean equals(Locacion unaL) {
        boolean igual = false;
        igual = this.nombre.equalsIgnoreCase(unaL.getNombre());
        return igual;
    }


    public String toStrin() {
        return "Locacion{" + "nombre=" + nombre + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Locacion other = (Locacion) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
    

}
