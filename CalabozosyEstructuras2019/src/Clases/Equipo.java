/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Estructuras.Lista;
import java.util.Random;

/**
 *
 * @author Fabio
 */
public class Equipo {

    private String nombre; //Nombre del Equipo
    private int categoria; //Categoria del Equipo
    private int kill; //Cantidad de Jugadores Derrotados
    private Locacion miLocacion; //Nombre de la Locacion en la que se encuentra el Equipo
    private Lista jugadores; //Lista de Jugadores del Equipo
    private boolean perdi,empate; //1. Variable que indica si el equipo perdio o no //Variable que indica si el equipo empato 
    private int guia; //Variable auxiliar para obtener un Jugador;

    public Equipo(String nombre, Locacion miLocacion) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.miLocacion = miLocacion;
        this.jugadores = new Lista();
        this.perdi = false;
        this.empate=false;
        this.guia = 1;
        this.kill = 0;

    }

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.guia = 1;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCategoria() {
        return categoria;
    }

    public Locacion getMiLocacion() {
        return miLocacion;
    }

    public Lista getJugadores() {
        return jugadores;
    }

    public boolean getPerdi() {
        return perdi;
    }
    public void Gane(){
        this.perdi=true;
    }
    public void empate(){
        this.empate=true;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void agregarKill() {
        this.kill++;
    }

    public void setMiLocacion(Locacion miLocacion) {
        this.miLocacion = miLocacion;
    }

    public String getCategor() {
        String cat = "";
        switch (this.categoria) {
            case 1:
                cat = "Novato";
                break;
            case 2:
                cat = "Aficionado";
                break;
            case 3:
                cat = "Profesional";
                break;
        }
        return cat;
    }

    public void cargarJugador(Jugador unJugador) {
        this.jugadores.insertar(unJugador, this.jugadores.longitud() + 1);
        if (unJugador.getCategoriaInt() < categoria) {
            categoria = unJugador.getCategoriaInt();
        }

    }

    public void datosGenerales() {
        int i = 1;
        System.out.println(" Equipo " + nombre + "\n "
                + " Categoria Del Equipo :" + getCategor());
        while (i < 4) {
            Jugador unJ = (Jugador) jugadores.recuperar(i);
            System.out.print(" Jugador " + i + "\n "
                    + " Nombre : " + unJ.getNombre() + "\n "
                    + " Categoria :" + unJ.getCategoria());
            i++;
        }
    }
    
    public void actualizarFinDeBatalla(){
      int i=1;
        while (i < 4) {
            Jugador unJ = (Jugador) jugadores.recuperar(i);
            unJ.actualizarDatosEquipo(perdi, empate);
            i++;
        }
    }

    public Jugador obtenerUnJugador() {
        Random r = new Random();
        Jugador unJ = null, aux;
        boolean listo=false;
        while (!listo && kill != 3) {
            aux = (Jugador) jugadores.recuperar(guia);
            if (!aux.getDerrotado()) {
                unJ = aux;
                guia++;
                listo = true;
            }
            if (guia == 4) {
                guia = 1;

            }
        }
        return unJ;
    }
}
