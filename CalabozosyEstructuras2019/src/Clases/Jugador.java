/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Estructuras.Lista;
import Estructuras.ListaItem;
import java.util.Random;

/**
 *
 * @author Fabio
 */
public class Jugador {

    private String nombre; // Nombre del Jugador
    private String tipoJugador; // Tipo del Jugador (1 Defensor / 2 Guerrero) 
    private double salud; // Salud del Jugador
    private double saludGame; //Salud que va a ser afectada en la Batalla
    private int dinero; // Dinero acumulado
    private int perdidas; // Cantidad de Partidas Perdidas
    private int ganadas; // Cantidad de Partidas Ganadas
    private String categoria; // Categoria 1.Novato 2.Aficionado 3.Profesional
    private int ataque, defensa; // Valor de Ataque y Defensa del Jugador
    private ListaItem items; // Items Adquiridos
    private boolean derrotado; //Me informa si esta derrotado;

    public Jugador(String nombre, String tipoJugador, String categoria, int dinero) {
        this.nombre = nombre;
        this.tipoJugador = tipoJugador;
        this.salud = 100;
        this.saludGame = salud;
        this.dinero = dinero;
        this.perdidas = 0;
        this.ganadas = 0;
        this.categoria = categoria;
        this.derrotado = false;
        this.items = new ListaItem();
        asignaratributos(tipoJugador);
    }

    public Jugador(String nombre, String tipoJugador, String categoria, int dinero, ListaItem items) {
        this.nombre = nombre;
        this.tipoJugador = tipoJugador;
        this.salud = 100;
        this.saludGame = salud;
        this.dinero = dinero;
        this.perdidas = 0;
        this.ganadas = 0;
        this.categoria = categoria;
        this.derrotado = false;
        this.items = items;
        asignaratributos(tipoJugador);
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipoJugador() {
        return tipoJugador;
    }

    public double getSalud() {
        return salud;
    }

    public int getDinero() {
        return dinero;
    }

    public int getPerdidas() {
        return perdidas;
    }

    public boolean getDerrotado() {
        return derrotado;
    }

    public int getGanadas() {
        return ganadas;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getCategoriaInt() {
        int cat;
        if (categoria.equalsIgnoreCase("Novato")) {
            cat = 1;
        } else {
            if (categoria.equalsIgnoreCase("Aficionado")) {
                cat = 2;
            } else {
                cat = 3;
            }
        }
        return cat;
    }

    private void asignaratributos(String tipoJugador) {
        if (tipoJugador.equalsIgnoreCase("guerrero")) {
            this.ataque = 100 * getCategoriaInt();
            this.defensa = 50 * getCategoriaInt();
        } else {
            this.ataque = 25 * getCategoriaInt();
            this.defensa = 90 * getCategoriaInt();
        }
    }

    public int getMultiplicador() {
        int cat;
        if (categoria.equalsIgnoreCase("Novato")) {
            cat = 3;
        } else {
            if (categoria.equalsIgnoreCase("Aficionado")) {
                cat = 4;
            } else {
                cat = 5;
            }
        }
        return cat;
    }

    public ListaItem getItems() {
        return items;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipoJugador(String tipoJugador) {
        this.tipoJugador = tipoJugador;
        asignaratributos(tipoJugador);
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public void setPerdidas(int perdidas) {
        this.perdidas = perdidas;
    }

    public void setGanadas(int ganadas) {
        this.ganadas = ganadas;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean cargarItems(Item unItem) {
        return this.items.insertar(unItem, items.longitud() + 1);
    }

    public void actualizarItems() {
        int i = 1;
        while (i <= items.longitud()) {
            items.recuperar(i).actualizarDatos();
            if (items.recuperar(i).getDefensa() <= 0 && items.recuperar(i).getAtaque() <= 0) {
                items.eliminar(i);
            }
            i++;
        }
    }

    private int getDefensa() {
        return this.defensa;
    }

    public void actualizarDatosBatalla(boolean gano) {
        if (gano) {
            ganadas++;
            dinero = dinero + 1000;
        } else {
            perdidas++;
            derrotado = true;
        }
    }

        public void actualizarDatosEquipo(boolean gano, boolean empato) {

        if (empato) {
            dinero = dinero + 500;
        } else {
            if (!gano) {
                dinero = dinero + 1000;
            } else {
                dinero = dinero - 500;
            }
        }
        saludGame = salud; //restablesco la Salud
    }
        public void gane(){
        this.dinero=dinero+1000;
        this.ganadas++;
        }
        public void perdi(){
        this.perdidas++;
        }

    public double ataque() {
        Random m = new Random();
        double ataque;
        ataque = this.ataque * (m.nextDouble() + 0.5);
        return ataque;
    }

    public boolean recibirAtaque(double ataq) {
        boolean muerto = false;
        if (ataq > getDefensa()) {
            salud = salud - (ataq - getDefensa());
            if (salud <= 0) {
                muerto = true;
            }
        }

        return muerto;
    }

    public String toStrin() {
        return "Jugador{" + "\n "
                + "nombre=" + nombre + "\n "
                + " TipoJugador=" + this.getCategoria() + "\n "
                + " SaludGame=" + saludGame + "\n "
                + " Dinero=" + dinero + "\n "
                + " Perdidas=" + perdidas + "\n "
                + " Ganadas=" + ganadas + "\n "
                + " Categoria=" + categoria + "\n "
                + " Items=" + items.longitud() + "\n "
                + " Derrotado=" + derrotado + '}';
    }

}
