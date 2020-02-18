/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Clases.Item;

/**
 *
 * @author Torrico
 */
public class NodoItem {
 
	private Item elem;
	private NodoItem enlace;

	public NodoItem(Item elemento, NodoItem enl) {
		this.elem = elemento;
		this.enlace = enl;
	}

	public NodoItem(Item elemento) {
		this.elem = elemento;
	}

	public Item getElem() {
		return this.elem;
	}

	public void setElem(Item elemento) {
		this.elem = elemento;
	}

	public NodoItem getEnlace() {
		return this.enlace;
	}

	public void setEnlace(NodoItem enl) {
		this.enlace = enl;
	}


}
