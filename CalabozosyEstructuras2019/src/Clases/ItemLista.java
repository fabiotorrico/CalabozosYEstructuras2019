/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Estructuras.Lista;
 
public class ItemLista {
    
    private  Lista unaL;
   
    
    public ItemLista() {
        this.unaL = new Lista();
    }
    public boolean insertar(Item unI){
    boolean exito;
        exito=unaL.insertar(unI, unaL.longitud()+1);
    return exito;}
    
    public boolean eliminar(String codigo){
    boolean exito=false;
    int i;
i=unaL.localizar(new Item(codigo));
        if (i>0) {
             exito=unaL.eliminar(i);
             exito=true;
        }
    return exito;}
    
    
    
    public void mostrarItems(){
        Item n;
        for (int i = 0; i <= unaL.longitud(); i++) {
            System.out.println("( "+i+" )");
            n=(Item)unaL.recuperar(i);
            System.out.println(n.toString());
        }
    }
    
    
    
    
    
    
}
