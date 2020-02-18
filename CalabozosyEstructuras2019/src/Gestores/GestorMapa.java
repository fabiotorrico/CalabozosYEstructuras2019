/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestores;

import Clases.Locacion;
import Estructuras.Grafo;
import Estructuras.Lista;

/**
 *
 * @author Fabio
 */
public class GestorMapa {

    private Grafo unMapa;

    public GestorMapa() {
        unMapa = new Grafo();
    }

    public boolean insertarLocacion(Locacion unaL) {
        boolean exito = false;
        if (unMapa.insertarVertice(unaL)) {
            exito = true;
            System.out.println("__________"+unMapa.cantidadVertices());
            
        }
        return exito;
    }

    public boolean eliminarLocacion(Locacion unaL) {
        boolean exito = false;
        exito = unMapa.eliminarVertice(unaL);
        return exito;
    }

    public boolean existeLocacion(Locacion unaL) {
        boolean exito = false;
        if (unMapa.existeVertice(unaL)) {
              exito =true;
        }
 
        return exito;
    }

    public boolean agregarCamino(Locacion unaL1, Locacion unaL2, int km) {
        boolean exito = false;
        if (!unMapa.existeCamino(unaL1, unaL2)) {
            exito = unMapa.insertarArco(unaL1, unaL2, km);
        }
        return exito;
    }

    public boolean eliminarCamino(Locacion unaL1, Locacion unaL2) {
        boolean exito = false;
        if (!unMapa.existeCamino(unaL1, unaL2)) {
            exito = unMapa.eliminarArco(unaL1, unaL2);
        }
        return exito;
    }

    public Locacion obtenerLocacion(Locacion unaLo) {
        Locacion unaL = (Locacion) unMapa.obtenerElemento(unaLo);
        return unaL;
    }

    public void obtenerLocacionesLimitrofes(Locacion unaL) {
        Lista limitrofes = new Lista();
        limitrofes = unMapa.obtenerAdyacentes(unaL);
        int i=1;
        while (i<=limitrofes.longitud()) {            
        Locacion nL=(Locacion)limitrofes.recuperar(i);
            System.out.println(nL.toStrin());
        i++;
        }
    }

    public void caminoXaYmasCorto(Locacion unaL1, Locacion unaL2) {
        Lista locaciones = new Lista();
        locaciones = unMapa.caminoMasCortoEnKm(unaL1, unaL2);
        int i=1;
        while (i<=locaciones.longitud()) {            
            Locacion unaL =(Locacion)locaciones.recuperar(i);
            System.out.println(unaL.toStrin());
        }
    }
    
    public Locacion obtenerLocalidadRandom(){
    Locacion unaL=(Locacion)unMapa.obtenerVerticeAleatorio();
    return unaL;}
    
}
