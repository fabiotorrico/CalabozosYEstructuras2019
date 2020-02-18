/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 *
 * @author Torrico
 */
public class Nodo_1 {

    private Object elem;
    private Nodo enlace;

    public Nodo_1(Object elemento, Nodo enl) {
        this.elem = elemento;
        this.enlace = enl;
    }

    public Nodo_1(Object elemento) {
        this.elem = elemento;
    }

    public Object getElem() {
        return this.elem;
    }

    public void setElem(Object elemento) {
        this.elem = elemento;
    }

    public Nodo getEnlace() {
        return this.enlace;
    }

    public void setEnlace(Nodo enl) {
        this.enlace = enl;
    }

}
