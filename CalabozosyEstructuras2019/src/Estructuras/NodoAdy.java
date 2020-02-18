/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;



/**
 *
 * @author Fabio
 */
public class NodoAdy {
    
    
    private NodoVert vertice;
    private NodoAdy sigAdyacente;
    private Comparable  etiqueta;

    public NodoAdy() {
	this.vertice = null;
	this.sigAdyacente = null;
	this.etiqueta = 0;
    }
    
    public NodoAdy(NodoVert vert, Comparable eti){
        //Constructor nodoAdy con etiqueta
        this.vertice = vert;
        this.sigAdyacente = null;
        this.etiqueta = eti;
    }

    public NodoVert getVertice() {
        return this.vertice;
    }

    public NodoAdy getSigAdyacente() {
        return this.sigAdyacente;
    }

    public Comparable getEtiqueta() {
        return this.etiqueta;
    }

    public void setVertice(NodoVert vertice) {
        this.vertice = vertice;
    }

    public void setSigAdyacente(NodoAdy sigAdyacente) {
        this.sigAdyacente = sigAdyacente;
    }

    public void setEtiqueta(Comparable etiqueta) {
        this.etiqueta = etiqueta;
    }
    
    
    
    
    
}
