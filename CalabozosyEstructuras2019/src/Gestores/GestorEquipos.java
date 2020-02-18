/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestores;

import Clases.Equipo;
import Estructuras.AVLDiccionario;
import java.util.HashMap;

/**
 *
 * @author Fabio
 */
public class GestorEquipos {
    private HashMap BDequipos;


    public GestorEquipos() {
        this.BDequipos = new HashMap();
     
    }
    
    public boolean crearEquipo(Equipo unE){
    boolean exito=false;
        if (BDequipos.containsKey(unE)) {
            BDequipos.put(unE.getNombre(), unE);
            exito=true;
        }
    return exito;}
    
    public boolean existeEquipo(Equipo unE){
    boolean existe=false;
    existe=BDequipos.containsKey(unE);
    return existe;}
    
    public boolean eliminarEquipo(Equipo unE){
    boolean exito=false;
        if (BDequipos.containsKey(unE)) {
            BDequipos.remove(unE);
            exito=true;
        }
    return exito;}
    
    public Equipo obtenerEquipo(Equipo unE){
    Equipo nEquipo=(Equipo)BDequipos.get(unE.getNombre());
    return nEquipo;}
    
    
    
}
