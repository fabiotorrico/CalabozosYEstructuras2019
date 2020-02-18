/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestores;

import Clases.Item;
import Clases.ItemLista;
import Estructuras.AVLDiccionario;
import Estructuras.Lista;

/**
 *
 * @author Fabio
 */
public class GestorItems {

    private AVLDiccionario items;
    private AVLDiccionario itemCodigo;

    public GestorItems() {
        this.items = new AVLDiccionario();
        this.itemCodigo = new AVLDiccionario();
    }

    public boolean insertarItem(Item unItem) {
        boolean exito = false;
        ItemLista unL;
        if (!itemCodigo.existeClave(unItem.getCodigo())) {
            if (items.existeClave(unItem.getPrecio())) {
                unL = (ItemLista) items.obtenerDato(unItem.getPrecio());
                unL.insertar(unItem);
                insertarAux(unItem);
            } else {
                unL = new ItemLista();
                unL.insertar(unItem);
                this.items.insertar(unItem.getPrecio(), unL);
                insertarAux(unItem);
            }
            exito = true;
        }
        return exito;
    }

    public boolean existe(String codigo) {
        boolean exito;
        exito = itemCodigo.existeClave(codigo);
        return exito;
    }

    private boolean insertarAux(Item unItem) {
        boolean exito;
        if (itemCodigo.existeClave(unItem.getCodigo())) {
            exito = false;
        } else {
            exito = true;
            itemCodigo.insertar(unItem.getCodigo(), unItem);
        }
        return exito;
    }

    public boolean eliminarItem(String codigo) {
        boolean exito = false;
        Item unI;
        ItemLista unaL;
        if (itemCodigo.existeClave(codigo)) {
            unI = (Item) itemCodigo.obtenerDato(codigo);
            unaL = (ItemLista) items.obtenerDato(unI.getPrecio());
            unaL.eliminar(codigo);
            itemCodigo.eliminar(codigo);

            exito = true;
        }

        return exito;
    }

    public Item obtenerItem(String codigo) {
        Item unI;
        unI = (Item) itemCodigo.obtenerDato(codigo);
        return unI;
    }
    public void obtenerDatos(String codigo) {
        Item unI;
        unI = (Item) itemCodigo.obtenerDato(codigo);
        System.out.println(unI.toStrin());
       
    }
    
    public void mostrarItemsCon1Stock(){
    Lista unaL=new Lista();
        seleccionar1(unaL);
        if (!unaL.esVacia()) {
            explotarLista(unaL);
        }else{
            System.out.println(" NO hay ningun Item Que tenga solo 1 copia disponible");
        }
    }
    private void seleccionar1(Lista unaL){
    Lista nLista;
    Item unI;
    int i=1;
    nLista=itemCodigo.listarDatos();
        while (i<=nLista.longitud()) {            
            unI=(Item)nLista.recuperar(i);
            if (unI.getDisponibles()==1) {
                unaL.insertar(unaL,unaL.longitud()+1);
            }
            i++;
        }
    }
    
    public void mostrarRango(int min, int max) {
    Lista unaL,aux;
    int i=1;
    unaL=items.listarDatos();
        while (i<=unaL.longitud()) {   
            aux=(Lista)unaL.recuperar(i);
            explotarLista(aux);
            i++;               
        }
    }
    
    private void explotarLista(Lista unal){
    int i=1;
    Item unI;
        while (i<=unal.longitud()) {            
            unI=(Item)unal.recuperar(i);
            System.out.println(unI.toStrin());
            i++;
        }
    }
    

    public void mostrarBD() {
        Lista unaL;
        Item unI;
        int i = 1;
        unaL = itemCodigo.listarDatos();
        while (i <= unaL.longitud()) {
            unI = (Item) unaL.recuperar(i);
            System.out.println(unI.toStrin());
            i++;
        }

    }

}
