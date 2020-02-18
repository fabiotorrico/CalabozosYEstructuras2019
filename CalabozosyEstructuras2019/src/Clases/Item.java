/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Objects;
import java.util.logging.Logger;

/**
 *
 * @author Fabio
 */
public class Item {

    private String codigo; //Codigo del Item
    private String nombre; //Nombre del Item
    private int precio; // Precio del Item
    private int ataque; // Valor de Ataque de Item
    private int defensa; //Valor de Defensa del Item
    private int disponibles; //Cantidad de Ejemplares disponibles del Item.

    public Item() {
    }

    public Item(String codigo) {
    this.codigo = codigo;
    }

    public Item(String codigo, String nombre, int precio, int ataque, int defensa, int disponibles) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.ataque = ataque;
        this.defensa = defensa;
        this.disponibles = disponibles;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public void comproItem() {
        this.disponibles--;
    }

    public void actualizarDatos() {
        this.defensa = defensa - 10;
        this.ataque = ataque - 10;
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
        final Item other = (Item) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }
    


    public String toStrin() {
        return "____________________________"
                + "Item{ \n"
                + "Codigo=" + codigo + "\n "
                + "Nombre=" + nombre + "\n "
                + "Precio=" + precio + "\n "
                + "Ataque=" + ataque + "\n "
                + "Defensa=" + defensa + "\n"
                + "____________________________";
    }

}
