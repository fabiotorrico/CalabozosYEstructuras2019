/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Clases.Item;

/**
 *
 * @author Fabio
 */
public class ListaItem {
	private NodoItem cabecera;
	private int longitud;

	public ListaItem() {
		this.cabecera = null;
		this.longitud = 0;
	}

	public boolean insertar(Item nuevoElem, int pos) {
		boolean exito;
		if (pos > 0 || pos <= longitud + 1) {

			if (pos == 1) {
				this.cabecera = new NodoItem(nuevoElem, this.cabecera);
			} else {
				int i = 1;
				NodoItem aux = this.cabecera;
				while (i < pos - 1) {
					aux = aux.getEnlace();
					i++;
				}
				NodoItem nuevoNodo = new NodoItem(nuevoElem);
				nuevoNodo.setEnlace(aux.getEnlace());
				aux.setEnlace(nuevoNodo);
			}
			this.longitud += 1;
			exito = true;
		} else {
			exito = false;
		}
		return exito;
	}

	public boolean eliminar(int pos) {
		boolean exito;
		if (pos < 1 || pos > this.longitud + 1 || this.esVacia()) {
			exito = false;
		} else {
			if (pos == 1) {
				this.cabecera = this.cabecera.getEnlace();
			} else {
				NodoItem aux = this.cabecera;
				int i = 1;
				while (i < pos - 1) {
					aux = aux.getEnlace();
					i++;
				}
				aux.setEnlace(aux.getEnlace().getEnlace());
			}
			this.longitud -= 1;
			exito = true;
		}
		return exito;
	}

	public Item recuperar(int pos) {
		Item elemento;
		if (esVacia() || pos < 1 || pos > longitud()) {
			elemento = null;
		} else {
			if (pos == 1) {
				elemento = this.cabecera.getElem();
			} else {
				int i = 0;
				NodoItem aux = this.cabecera;
				while (i < pos - 1) {
					aux = aux.getEnlace();
					i++;
				}
				elemento = aux.getElem();
			}
		}

		return elemento;
	}

	public int localizar(Item elem) {
		int pos;
		if (esVacia()) {
			pos = -1;
		} else {
			pos = 1;
			NodoItem aux = this.cabecera;
			boolean encontrado = false;
			while (!encontrado && pos <= this.longitud) {
				if (aux.getElem().equals(elem)) {
					encontrado = true;
				} else {
					aux = aux.getEnlace();
					pos++;
				}
			}
			if (!encontrado) {
				pos = -1;
			}
		}
		return pos;
	}

	public int longitud() {
		return this.longitud;
	}

	public boolean esVacia() {
		boolean vacia = false;
		if (this.cabecera == null) {
			vacia = true;
		}
		return vacia;
	}

	public void vaciar() {
		this.cabecera = null;
	}

	public String toString() {
		String cadena = "";
		if (this.cabecera == null) {
			cadena = "Lista vacia";
		} else {
			cadena = "[";
			NodoItem aux = this.cabecera;
			while (aux != null) {
				cadena += aux.getElem();
				aux = aux.getEnlace();
				if (aux != null) {
					cadena += ",";
				}
			}
			cadena += "]";
		}
		return cadena;
	}

	private NodoItem cloneNodos(NodoItem nodo) {
		NodoItem nuevoNodo;
		if (nodo.getEnlace() == null) {
			nuevoNodo = new NodoItem(nodo.getElem());
		} else {
			nuevoNodo = new NodoItem(nodo.getElem(), cloneNodos(nodo.getEnlace()));
		}
		return nuevoNodo;
	}

	public ListaItem clone() {
		ListaItem listaClon = new ListaItem();
		listaClon.cabecera = cloneNodos(this.cabecera);
		listaClon.longitud = this.longitud;
		return listaClon;
	}
}

    

