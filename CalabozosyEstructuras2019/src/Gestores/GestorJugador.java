/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestores;

import Clases.Jugador;

import Estructuras.AVLDiccionario;
import Estructuras.ColaObject;
import Estructuras.Lista;

/**
 *
 * @author Fabio
 */
public class GestorJugador {
    
    private AVLDiccionario jugadores;
    private String ultimo;

    public GestorJugador() {
        this.jugadores = new AVLDiccionario();        
    }
    
    public boolean insertarJugador(String nombre, String tipoJugador,String categoria,int dinero) {
        boolean exito = false;
        Jugador unJugadorN = new Jugador(nombre, tipoJugador, categoria,dinero);
        exito = this.jugadores.insertar(nombre, unJugadorN);
        ultimo = nombre;
        return exito;
    }

    public boolean insertarJugadorDirecto(Jugador unJuga) {
        boolean exito = false;
        exito = this.jugadores.insertar(unJuga.getNombre(), unJuga);
        return exito;
    }
    
    public boolean existeJugador(String nombreJu) {
        return this.jugadores.existeClave(nombreJu);
    }
    
    public boolean eliminarJugador(String nombreJu) {
        return this.jugadores.eliminar(nombreJu);
    }
    
    public void modificarClave(Jugador unJ, String clave) {
        jugadores.modificarClaveNodo(unJ.getNombre(), clave);
    }
    
    public String mostrarJugador(String nombreJu) {
        String datos = "";
        Jugador jgador = (Jugador) jugadores.obtenerDato(nombreJu);
        datos = jgador.toString();
        return datos;
    }
    
    public Jugador obtenerJugador(String nombre) {
        Jugador unJuga = (Jugador) this.jugadores.obtenerDato(nombre);
        return unJuga;
    }

    public Lista listarJugadoresConNombreSimilar(String subcadena) {
        Lista miLista = new Lista();
        miLista = jugadores.listarNombreSimilares(subcadena);
        return miLista;
    }
    
    public void mostrarBaseDeDatos() {
        Lista unal;
        Jugador unJ;
        int i = 1;
        unal = jugadores.listarDatos();
        while (i <= unal.longitud()) {            
            unJ = (Jugador) unal.recuperar(i);
            if (unJ.getNombre().equalsIgnoreCase(ultimo)) {
                System.out.println("NUEVO JUGADOR CARGADO");
            }
            System.out.println(unJ.toStrin());
            i++;
        }
    }

    public void ranking() {
        Lista unal;
        int i = 1;
        unal = jugadores.listarDatos();
        Jugador[] ranking = new Jugador[unal.longitud()];
        pasar(unal, ranking);
        insercion(ranking);
        imprimirRanking(ranking);
    }
    
    private void pasar(Lista unaL, Jugador[] ranking) {
        Jugador unJ;
        int i = 1;
        while (i <= unaL.longitud()) {            
            unJ = (Jugador) unaL.recuperar(i);
            ranking[i - 1] = unJ;
            i++;
        }
    }
    private void imprimirRanking(Jugador[] ranking) {
        for (int i = 0; i < ranking.length; i++) {
            System.out.println(ranking[i].toStrin());
        }
    }
    private void insercion(Jugador[] ranking){
    int inicio=0;
    int fin=ranking.length;
    Jugador cambio;
        while (inicio < fin) {
            for (int j = 0; j < fin; j++) {
                if (ranking[j].getGanadas() > ranking[j + 1].getGanadas()) {
                    cambio = ranking[j + 1];
                    ranking[j + 1] = ranking[j];
                    ranking[j] = cambio;
                }
            }
            fin--;
        }
    }
}
