/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calabozosyestructuras2019;

import Clases.Equipo;
import Clases.Item;
import Clases.Jugador;
import Clases.Locacion;
import Estructuras.ColaDePrioridad;
import Estructuras.Lista;
import Gestores.GestorEquipos;
import Gestores.GestorItems;
import Gestores.GestorJugador;
import Gestores.GestorMapa;

/**
 *
 * @author Fabio
 */
public class CalabozosyEstructuras2019 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GestorJugador BDjugadores = new GestorJugador();
        GestorEquipos BDequipos = new GestorEquipos();
        GestorItems BDItems = new GestorItems();
        GestorMapa BDmapa = new GestorMapa();
        ColaDePrioridad colaprioridad = new ColaDePrioridad();
        int opcion;
        menuPrincipal();
        opcion = TecladoIn.readLineInt();
        while (opcion != 0) {
            switch (opcion) {
                case 1:
                    abmJugadorea(BDjugadores);
                    break;
                case 2:
                    abmItems(BDItems);
                    // Consultar el tema de Compra de Items y el tema de las copias
                    break;
                case 3:
                    abmLocaciones(BDmapa);
                    break;
                case 4:
                    altaJugador(BDjugadores, colaprioridad);
                    break;
                case 5:
                    crearEquipo(BDequipos, BDmapa, colaprioridad);
                    break;
                case 6:
                    batallaDeEquipos(BDequipos);
                    //Consultar tema de Salud e elegir Equipos
                    break;
                case 7:
                    consultaDeEquipo(BDequipos);
                    break;
                case 8:
                    consultaItems(BDItems);
                    break;
                case 9:
                    consultaDeJugadores(BDjugadores);
                    break;
                case 10:
                    consultaLocaciones(BDmapa);
                    //Faltantes
                    break;
                case 11:
                    consultasGenerales(BDjugadores, BDItems);
                    break;
                case 12:
                    System.out.println("Consutlar a que se refieree");
                            
                    break;
                default:
                    System.out.println("Ingrese una Opcion correcta del 0 al 12");
                    break;
            }
            menuPrincipal();
            opcion = TecladoIn.readLineInt();
        }

    }

    public static void menuPrincipal() {
        System.out.println("_____________  MENU PRINCIPAL  ______________\n"
                + "| 1. ABM de Jugadores.                                        |\n"
                + "| 2. ABM de Items.                                            |\n"
                + "| 3. ABM de Locaciones.                                       |\n"
                + "| 4. Alta de un jugador en la cola de espera por un equipo .  |\n"
                + "| 5. Creación automática de un equipo.                        |\n"
                + "| 6. Batalla de Equipos                                    .  |\n"
                + "| 7. Consultas sobre Equipos.                                 |\n"
                + "| 8. Consultas sobre Items.                                   |\n"
                + "| 9. Consultas sobre Jugadores .                              |\n"
                + "| 10. Consultas sobre Locaciones .                            |\n"
                + "| 11. Consultas Generales .                                   |\n"
                + "| 12. Mostrar Sistema .                                       |\n"
                + "| 0. Finalizar .                                              |\n"
                + "|_____________________________________________________________|");
    }

    public static void abmJugadorea(GestorJugador BDjugador) {
        int opcion;
        menuabmJugadores();
        opcion = TecladoIn.readLineInt();
        while (opcion != 0) {
            switch (opcion) {
                case 1:
                    String nombre,
                     tipodeJugador = "",
                     categoria = "";
                    ;
                    int numTipo = 0,
                     dinero = 0,
                     tres;

                    boolean valido = false;
                    System.out.println("Ingrese el Nombre del Jugador");
                    nombre = TecladoIn.readLine();
                    if (!BDjugador.existeJugador(nombre)) {
                        while (!valido) {
                            System.out.println("Ingrese el numero del Tipo de Jugador \n"
                                    + " 1 -> DEFENSOR    "
                                    + " 2 -> GUERRERO    ");
                            numTipo = TecladoIn.readLineInt();
                            if (numTipo > 0 && numTipo < 3) {
                                valido = true;
                                if (numTipo == 1) {
                                    tipodeJugador = "DEFENSOR";
                                } else {
                                    tipodeJugador = "GUERRERO";
                                }
                            } else {
                                System.out.println("Ingrese una Opcion correcta");
                            }
                        }
                        valido = false;
                        System.out.println("Ingrese el Dinero disponible del Jugador");
                        dinero = TecladoIn.readLineInt();
                        while (!valido) {
                            System.out.println("Ingrese el numero de la CATEGORIA de Jugador \n"
                                    + " 1 -> NOVATO    "
                                    + " 2 -> AFICIONADO    "
                                    + " 3 -> PROFESIONAL   ");
                            tres = TecladoIn.readLineInt();
                            if (tres > 0 && tres < 4) {
                                if (tres == 1) {
                                    categoria = "Novato";
                                } else {
                                    if (tres == 2) {
                                        categoria = "Aficionado";
                                    } else {
                                        categoria = "Profesional";
                                    }
                                }
                                valido = true;
                            } else {
                                System.out.println("Ingrese una Categoria correcta");
                            }
                        }
                        if (BDjugador.insertarJugador(nombre, tipodeJugador, categoria, dinero)) {
                            System.out.println("Jugador Cargado Exitosamente");
                        } else {
                            System.out.println(" No se pudo Cargar el Jugador ");
                        }
                    } else {
                        System.out.println(" Ya exisate un Jugador con el nombre " + nombre);
                    }
                    break;
                case 2:
                    String nombree;
                    System.out.println("Ingrese el nombre del Jugador a eliminar");
                    nombree = TecladoIn.readLine();
                    if (BDjugador.existeJugador(nombree)) {
                        BDjugador.eliminarJugador(nombree);
                        System.out.println("El jugador " + nombree + " Fue eliminado ");
                    } else {
                        System.out.println("El jugador con el nombre " + nombree + " No existe");
                    }
                    break;
                case 3:
                    String nombrem;
                    System.out.println("Ingrese el nombre del Jugador a Modificar");
                    nombrem = TecladoIn.readLine();
                    if (BDjugador.existeJugador(nombrem)) {
                        Jugador unJugador = BDjugador.obtenerJugador(nombrem);
                        modificarJugador(unJugador, BDjugador);
                    } else {
                        System.out.println("El jugador " + nombrem + " no Existe");
                    }
                    break;
                case 4:
                    BDjugador.mostrarBaseDeDatos();
                    break;
                default:
                    System.out.println("Ingrese una Opcion Correcta del 0 al 3");
                    break;

            }
            menuabmJugadores();
            opcion = TecladoIn.readLineInt();
        }
    }

    public static void modificarJugador(Jugador unJuga, GestorJugador BDjugadores) {
        int opcion;
        menumodificadorJugador();
        opcion = TecladoIn.readLineInt();
        while (opcion != 0) {
            switch (opcion) {
                case 1:
                    String nombreNuevo;
                    System.out.println("Ingrese el nombre nuevo");
                    nombreNuevo = TecladoIn.readLine();
                    if (!BDjugadores.existeJugador(nombreNuevo)) {
                        BDjugadores.modificarClave(unJuga, nombreNuevo);
                        unJuga.setNombre(nombreNuevo);
                    } else {
                        System.out.println(" YA existe alguien con ese Nombre");
                    }
                    break;
                case 2:
                    boolean valido = false;
                    int numTipo;
                    String tipodeJugador;
                    while (!valido) {
                        System.out.println("Ingrese el numero del (Nuevo) Tipo de Jugador \n"
                                + " 1 -> DEFENSOR    "
                                + " 2 -> GUERRERO    ");
                        numTipo = TecladoIn.readLineInt();
                        if (numTipo > 0 && numTipo < 3) {
                            valido = true;
                            if (numTipo == 1) {
                                tipodeJugador = "DEFENSOR";
                            } else {
                                tipodeJugador = "GUERRERO";
                            }
                            unJuga.setTipoJugador(tipodeJugador);
                        } else {
                            System.out.println("Ingrese una Opcion correcta");
                        }
                    }

                    break;

                case 3:
                    boolean valid = false;
                    int tres;
                    String categoria;
                    while (!valid) {
                        System.out.println("Ingrese el Nuevo numero de la CATEGORIA de Jugador \n"
                                + " 1 -> NOVATO    "
                                + " 2 -> AFICIONADO    "
                                + " 3 -> PROFESIONAL   ");
                        tres = TecladoIn.readLineInt();
                        if (tres > 0 && tres < 4) {
                            if (tres == 1) {
                                categoria = "Novato";
                            } else {
                                if (tres == 2) {
                                    categoria = "Aficionado";
                                } else {
                                    categoria = "Profesional";
                                }
                            }
                            unJuga.setCategoria(categoria);
                            valido = true;
                        } else {
                            System.out.println("Ingrese una Categoria correcta");
                        }
                    }

                    break;

                case 4:
                    int monto;
                    System.out.println("Ingrese el (Nuevo) monto de Dinero");
                    monto = TecladoIn.readLineInt();
                    if (monto > 0) {
                        unJuga.setDinero(monto);
                    } else {
                        System.out.println("No se puede ingresar un monto de Dinero menor a 0");
                    }
                    break;
                default:
                    System.out.println("Ingrese una Opcion Correcta");
                    break;
            }
            menumodificadorJugador();
            opcion = TecladoIn.readLineInt();
        }

    }

    public static void menumodificadorJugador() {
        System.out.println(" ___________ MODIFICAR JUGADOR ___________\n"
                + "| 1. Cambiar Nombre de Jugador.           |\n"
                + "| 2. Cambiar el TIPO de Jugador           |\n"
                + "| 3. Cambiar la Categoria del Jugador.    |\n"
                + "| 4. Cambiar Monto de Dinero del Jugador  |\n"
                + "| 0. Atras.                               |\n"
                + "|_________________________________________|");
    }

    public static void menuabmJugadores() {
        System.out.println(" ________MENU ABM JUGADORES ________\n"
                + "| 1. ALTA Jugador.                  |\n"
                + "| 2. BAJA Jugador.                  |\n"
                + "| 3. MODIFICAR Jugador.             |\n"
                + "| 4. Mostrar Base de Datos.         |\n"
                + "| 0. Atras.                         |\n"
                + "|___________________________________|");
    }

    public static void abmItems(GestorItems BDitem) {
        int opcion;
        menuabmItems();
        opcion = TecladoIn.readLineInt();
        while (opcion != 0) {
            switch (opcion) {
                case 1:
                    Item unI;
                    unI = crearItem();
                    if (BDitem.insertarItem(unI)) {
                        System.out.println("Item Agregado Correctamente");
                    } else {
                        System.out.println("El Item no pudo ser Agregado");
                    }
                    break;
                case 2:
                    String codigo;
                    System.out.println("Ingrese el Codigo del Item a Eliminar");
                    codigo = TecladoIn.readLine();
                    if (BDitem.existe(codigo)) {
                        BDitem.eliminarItem(codigo);
                    } else {
                        System.out.println("No existe ningun Item con el Codigo Especificado");
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el Codigo del Item a Modificar");
                    codigo = TecladoIn.readLine();
                    if (BDitem.existe(codigo)) {
                        modificarItem(BDitem.obtenerItem(codigo), BDitem);
                    } else {
                        System.out.println("No existe ningun Item con el Codigo Especificado");
                    }

                    break;
                case 4:
                    BDitem.mostrarBD();
                    break;
                default:
                    System.out.println("Ingrese una opcion Correcta del 0 al 4");
                    break;
            }
            menuabmItems();
            opcion = TecladoIn.readLineInt();
        }

    }

    public static void modificarItem(Item unI, GestorItems GBitems) {
        int opcion, precio, ataque, defensa, copias;
        menumodificadorItem();
        opcion = TecladoIn.readLineInt();
        while (opcion != 0) {
            switch (opcion) {
                case 1:
                    String nombre;
                    System.out.println("Ingrese el nombre nuevo del Item");
                    nombre = TecladoIn.readLine();
                    unI.setNombre(nombre);
                    break;
                case 2:
                    System.out.println("Ingrese el nuevo Precio");
                    precio = TecladoIn.readLineInt();
                    GBitems.eliminarItem(unI.getCodigo());
                    unI.setPrecio(precio);
                    GBitems.insertarItem(unI);
                    break;
                case 3:
                    System.out.println("Ingrese el NUEVO Valor de Ataque");
                    ataque = TecladoIn.readLineInt();
                    while (!validarNumero(ataque)) {
                        System.out.println("Ingrese un Valor de Ataque Mayor a 0");
                    }
                    unI.setAtaque(ataque);
                    break;
                case 4:
                    System.out.println("Ingrese el NUEVO Valor de Defense");
                    defensa = TecladoIn.readLineInt();
                    while (!validarNumero(defensa)) {
                        System.out.println("Ingrese un Valor de Defensa Mayor a 0");
                    }
                    unI.setDefensa(defensa);
                    break;
                case 5:
                    System.out.println("Ingrese la Cantidad de Copias Disponibles");
                    copias = TecladoIn.readLineInt();
                    unI.setDisponibles(copias);
                    break;
                default:
                    System.out.println("Ingrese un valor del 0 al 5");
                    break;

            }
            menumodificadorItem();
            opcion = TecladoIn.readLineInt();
        }
    }

    public static void menumodificadorItem() {
        System.out.println(" ___________ MODIFICAR ITEM ___________\n"
                + "| 1. Cambiar Nombre.                      |\n"
                + "| 2. Cambiar Precio                       |\n"
                + "| 3. Cambiar Puntos de Ataque.            |\n"
                + "| 4. Cambiar Puntos de Defensa.           |\n"
                + "| 5. Cambiar cantidad de Copias.          |\n"
                + "| 0. Atras.                               |\n"
                + "|_________________________________________|");
    }

    public static void menuabmItems() {
        System.out.println(" ________MENU ABM ITEMS ________\n"
                + "| 1. ALTA Item.                  |\n"
                + "| 2. BAJA Item.                  |\n"
                + "| 3. MODIFICAR Item.             |\n"
                + "| 4. Mostrar Base de Datos.      |\n"
                + "| 0. Atras.                      |\n"
                + "|________________________________|");
    }

    public static void abmLocaciones() {
        System.out.println(" ________MENU ABM LOCACIONES ________\n"
                + "| 1. ALTA Locacion.                 |\n"
                + "| 2. BAJA Locacion.                 |\n"
                + "| 3. MODIFICAR Locacion.            |\n"
                + "| 4. Mostrar Base de Datos.         |\n"
                + "| 0. Atras.                         |\n"
                + "|___________________________________|");
    }

    public static boolean validarNumero(int x) {
        boolean valido = false;
        if (x >= 0) {
            valido = true;
        }
        return valido;
    }

    public static Item crearItem() {
        Item nuevoI = new Item();
        String nombre, codigo;
        int precio, ataque, defensa, stock;
        boolean valido = false;
        System.out.println("Ingrese el Codigo AlfaNumerico del Item");
        codigo = TecladoIn.readLine();
        nuevoI.setCodigo(codigo);
        System.out.println("Ingrese el nombre del Item");
        nombre = TecladoIn.readLine();
        nuevoI.setNombre(nombre);
        while (!valido) {
            System.out.println("Ingrese el Precio del Item");
            precio = TecladoIn.readLineInt();
            if (!validarNumero(precio)) {
                System.out.println("Ingrese un Precio mayor a 0");
            } else {
                valido = true;
                nuevoI.setPrecio(precio);
            }
        }
        valido = false;
        while (!valido) {
            System.out.println("Ingrese el Ataque del Item");
            ataque = TecladoIn.readLineInt();
            if (!validarNumero(ataque)) {
                System.out.println("Ingrese un Ataque mayor a 0");
            } else {
                valido = true;
                nuevoI.setAtaque(ataque);
            }
        }
        valido = false;
        while (!valido) {
            System.out.println("Ingrese la defensa del Item");
            defensa = TecladoIn.readLineInt();
            if (!validarNumero(defensa)) {
                System.out.println("Ingrese una Defensa mayor a 0");
            } else {
                valido = true;
                nuevoI.setDefensa(defensa);
            }
        }
        valido = false;

        while (!valido) {
            System.out.println("Ingrese la cantidad de Ejemplares");
            stock = TecladoIn.readLineInt();
            if (!validarNumero(stock)) {
                System.out.println("Ingrese una cantidad mayor a 0");
            } else {
                valido = true;
                nuevoI.setDisponibles(stock);
            }
        }
        return nuevoI;
    }

    public static void abmLocaciones(GestorMapa BDMapa) {
        int opcion;
        menuabmLocaciones();
        opcion = TecladoIn.readLineInt();
        while (opcion != 0) {
            switch (opcion) {
                case 1:
                    String nombre;
                    System.out.println("Ingrese el Nombre de la Locacion");
                    nombre = TecladoIn.readLine();
                    if (BDMapa.insertarLocacion(new Locacion(nombre))) {
                        System.out.println("La Locacion " + nombre + " fue CARGADO Correctamente");
                    } else {
                        System.out.println("La locacion " + nombre + " NO pudo ser CARGADO");
                    }
                    break;
                case 2:

                    System.out.println("Ingrese el nombre de la Locacion a Eliminar");
                    nombre = TecladoIn.readLine();
                    if (BDMapa.existeLocacion(new Locacion(nombre))) {
                        BDMapa.eliminarLocacion(new Locacion(nombre));
                        System.out.println("La Locacion " + nombre + " fue Eliminada Correctamente");
                    } else {
                        System.out.println("La Locacion " + nombre + " NO EXISTE");
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el Nombre de la Locacion a Modificar");
                    nombre = TecladoIn.readLine();
                    if (BDMapa.existeLocacion(new Locacion(nombre))) {
                        Locacion unaL = (Locacion) BDMapa.obtenerLocacion(new Locacion(nombre));
                        modificarLocacion(unaL);
                    } else {
                        System.out.println("La Locacion " + nombre + " NO EXISTE");
                    }
                    break;
                default:
                    System.out.println("Ingrese una Opcion Correcta");
                    break;
            }
            menuabmLocaciones();
            opcion = TecladoIn.readLineInt();
        }

    }

    public static void modificarLocacion(Locacion unaL) {
        int opcion;
        menuModificadorLocacion();
        opcion = TecladoIn.readLineInt();
        while (opcion != 0) {
            switch (opcion) {
                case 1:
                    String nombre;
                    System.out.println("Ingrese el nombre nuevo");
                    nombre = TecladoIn.readLine();
                    unaL.setNombre(nombre);
                    break;
                default:
                    System.out.println("Ingrese una Opcion Correcta");
                    break;
            }
            menuModificadorLocacion();
            opcion = TecladoIn.readLineInt();
        }

    }

    public static void menuModificadorLocacion() {
        System.out.println(" _____MODIFICAR LOCACION  _____\n"
                + "| 1. MODIFICAR nombre de Locacion.       |\n"
                + "| 0. Atras.                              |\n"
                + "|________________________________________|");
    }

    public static void menuabmLocaciones() {
        System.out.println(" _____MENU ABM LOCACIONES _____\n"
                + "| 1. ALTA Locacion.                      |\n"
                + "| 2. BAJA Locacion.                      |\n"
                + "| 3. MODIFICAR Locacion.                 |\n"
                + "| 0. Atras.                              |\n"
                + "|________________________________________|");
    }

    public static void crearEquipo(GestorEquipos BDEquipos, GestorMapa BDMapa, ColaDePrioridad colaprioridad) {
        String nombre;
        Locacion unaL;
        if (colaprioridad.longitud() >= 3) {
            System.out.println("Ingrese el nombre del Equipo");
            nombre = TecladoIn.readLine();
            unaL = BDMapa.obtenerLocalidadRandom();
            Equipo unE = new Equipo(nombre, unaL);
            cargarJugadoresAequipo(colaprioridad, unE);
            unaL.agregarEquipo(unE);
            System.out.println(" El equipo " + nombre + " Se Cargo Correctamente");

        } else {
            System.out.println("NO hay suficientes Jugadores en la Cola para armar un Equipo");
        }
    }

    public static void cargarJugadoresAequipo(ColaDePrioridad colaprioridad, Equipo unE) {
        int i = 1;
        while (i < 4) {
            unE.cargarJugador(colaprioridad.obtenerFrente());
            i++;
        }
    }

    public static void altaJugador(GestorJugador BDjugadores, ColaDePrioridad colaprioridad) {
        String nombre;
        System.out.println("Ingrese el Nombre del Jugador a dar de alta");
        nombre = TecladoIn.readLine();
        if (BDjugadores.existeJugador(nombre)) {
            Jugador unJ = BDjugadores.obtenerJugador(nombre);
            colaprioridad.cargarJugador(unJ);
            System.out.println("El jugador con  " + nombre + " esta en la COLA DE ESPERA");
        } else {
            System.out.println("El jugador con Nombre " + nombre + " No existe");
        }
    }

    public static void consultaDeEquipo(GestorEquipos BDEquipos) {
        String nombre;
        System.out.println("Ingrese el nombre del Equipo");
        nombre = TecladoIn.readLine();
        if (BDEquipos.existeEquipo(new Equipo(nombre))) {
            Equipo n = (Equipo) BDEquipos.obtenerEquipo(new Equipo(nombre));
            n.datosGenerales();
        } else {
            System.out.println("El Equipo " + nombre + " NO EXISTE");
        }
    }

    public static void consultaDeJugadores(GestorJugador BDjugadores) {
        String nombre;
        int opcion;
        menuConsultaJugadores();
        opcion = TecladoIn.readLineInt();
        while (opcion != 0) {
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nombre del Jugador");
                    nombre = TecladoIn.readLine();
                    if (BDjugadores.existeJugador(nombre)) {
                        Jugador unJ = BDjugadores.obtenerJugador(nombre);
                        System.out.println(unJ.toStrin());

                    } else {
                        System.out.println("El jugador " + nombre + " NO EXISTE");
                    }
                    break;
                case 2:
                    String subcadena;
                    System.out.println("Ingrese la SUBCADENA ");
                    subcadena = TecladoIn.readLine();
                    Lista listita = BDjugadores.listarJugadoresConNombreSimilar(subcadena);
                    int i = 1;
                    while (i <= listita.longitud()) {
                        Jugador unJ = (Jugador) listita.recuperar(i);
                        System.out.println(unJ.toStrin());
                        i++;
                    }
                    break;
                default:
                    System.out.println("Ingresar una Opcion valida");
                    break;
            }
            menuConsultaJugadores();
            opcion = TecladoIn.readLineInt();
        }
    }

    public static void menuConsultaJugadores() {
        System.out.println(" ___________________________CONSULTA JUGADORES _____________________________\n"
                + "| 1. Dado un nombre de usuario de un jugador, mostrar todos sus datos                    |\n"
                + "| 2. Dada una subcadena, mostrar todos los nombres de usuarios que comienzan con esa     |\n"
                + "|    subcadena.                                                                          |\n"
                + "| 0. Atras.                                                                              |\n"
                + "|________________________________________________________________________________________|");

    }

    public static void menuConsultasLocaciones() {
        System.out.println("_________________________CONSULTA LOCACIONES ___________________________\n"
                + "| 1. Dado un nombre de locación, mostrar todas las locaciones a las que puede moverse un  |\n"
                + "|     equipo después de ganar una batalla en dicha locación                               |\n"
                + "| 2. Dados dos nombres de locaciones A y B                                                |\n"
                + "| 0. Salir.                                                                               |\n"
                + "|_________________________________________________________________________________________|");
    }

    public static void consultaLocaciones(GestorMapa BDmapa) {
        int opcion;
        menuConsultasLocaciones();
        opcion = TecladoIn.readLineInt();
        while (opcion != 0) {
            switch (opcion) {
                case 1:
                    String nombre;
                    System.out.println("Ingrese el nombre de la Locacion");
                    nombre = TecladoIn.readLine();
                    if (BDmapa.existeLocacion(new Locacion(nombre))) {
                        BDmapa.obtenerLocacionesLimitrofes(new Locacion(nombre));
                    } else {
                        System.out.println("No existe una Locacion con nombre " + nombre);
                    }
                    break;
                case 2:
                    boolean exito1 = false,
                     exito2 = false;
                    String n1 = "",
                     n2 = "";
                    while (!exito1 || !exito2) {
                        if (!exito1) {
                            System.out.println("Ingresar nombre de Locacion 1");
                            n1 = TecladoIn.readLine();
                            if (BDmapa.existeLocacion(new Locacion(n1))) {
                                exito1 = true;
                            } else {
                                System.out.println("Ingrese el nombre de una Locacion valida");
                            }
                        }
                        if (!exito2) {
                            System.out.println("Ingresar nombre de Locacion 2");
                            n2 = TecladoIn.readLine();
                            if (BDmapa.existeLocacion(new Locacion(n2))) {
                                exito2 = true;
                            } else {
                                System.out.println("Ingrese el nombre de una Locacion valida");
                            }
                        }
                    }
                    consultaLocacionesAux(BDmapa, new Locacion(n1), new Locacion(n2));

                    break;
                default:
                    System.out.println("Ingrese una Opcion Correcta");
                    break;
            }
            menuConsultasLocaciones();
            opcion = TecladoIn.readLineInt();
        }
    }

    public static void consultaLocacionesAux(GestorMapa BDmapa, Locacion unaL1, Locacion unaL2) {
        int opcion;
        opcion = TecladoIn.readLineInt();
        Locacion l1 = (Locacion) BDmapa.obtenerLocacion(unaL1);
        Locacion l2 = (Locacion) BDmapa.obtenerLocacion(unaL2);
        while (opcion != 0) {
            switch (opcion) {
                case 1:
                    BDmapa.caminoXaYmasCorto(l1, l2);
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                default:
                    System.out.println("Ingrese una Opcion Correcta");
                    break;
            }
            opcion = TecladoIn.readLineInt();
        }
    }

    public static void menuConsultasItems() {
        System.out.println("_________________________CONSULTA LOCACIONES ___________________________\n"
                + "| 1. Dado un monto de dinero mostrar todos los items que puede comprar el jugador         |\n"
                + "| 2. Dados un monto min y max mostrar los item disponibles                                |\n"
                + "| 3. Dado un Codigo de Item mostrar Detalles                                              |\n"
                + "| 0. Salir.                                                                               |\n"
                + "|_________________________________________________________________________________________|");
    }

    public static void consultaItems(GestorItems BDItems) {
        int opcion;
        menuConsultasItems();
        opcion = TecladoIn.readLineInt();
        while (opcion != 0) {
            switch (opcion) {
                case 1:
                    int precio,
                     min;
                    System.out.println("Ingrese un Precio Maximo");
                    precio = TecladoIn.readLineInt();
                    while (!validarNumero(precio)) {
                        System.out.println("Ingrese un numero mayor a 0");
                        precio = TecladoIn.readLineInt();
                    }
                    BDItems.mostrarRango(0, precio);
                    break;
                case 2:
                    System.out.println("Ingrese un Precio Minimo");
                    min = TecladoIn.readLineInt();
                    while (!validarNumero(min)) {
                        System.out.println("Ingrese un numero mayor o igual a 0");
                        min = TecladoIn.readLineInt();
                    }
                    System.out.println("Ingrese un Precio Maximo");
                    precio = TecladoIn.readLineInt();
                    while (!validarNumero(precio)) {
                        System.out.println("Ingrese un numero mayor o igual a 0");
                        precio = TecladoIn.readLineInt();
                    }
                    BDItems.mostrarRango(min, precio);

                    break;
                case 3:
                    String codigo;
                    System.out.println("Ingrese el codigo del Item");
                    codigo = TecladoIn.readLine();
                    if (BDItems.existe(codigo)) {
                        BDItems.obtenerDatos(codigo);
                    } else {
                        System.out.println("No existe ningun Item con ese Codigo");

                    }
                    break;
                default:
                    System.out.println("Ingrese una Opcion correcta del 0 al 3");
                    break;

            }
            menuConsultasItems();
            opcion = TecladoIn.readLineInt();
        }
    }

    public static void menuConsultasGenerales() {
        System.out.println("_________________________CONSULTA GENERALES ___________________________\n"
                + "| 1. Mostrar un ranking de los jugadores con más batallas individuales ganadas.           |\n"
                + "| 2. Mostrar un listado de todos los ítems de los que hay sólo uno disponible.            |\n"
                + "| 0. Atras.                                                                               |\n"
                + "|_________________________________________________________________________________________|");
    }

    public static void consultasGenerales(GestorJugador BDjugadores, GestorItems BDItems) {
        int opcion;
        menuConsultasGenerales();
        opcion = TecladoIn.readLineInt();
        while (opcion != 0) {
            switch (opcion) {
                case 1:
                    BDjugadores.ranking();
                    break;
                case 2:
                    BDItems.mostrarItemsCon1Stock();
                    break;
                default:
                    System.out.println("Ingrese una opcion correcta del 0 al 2");
                    break;
            }
            menuConsultasGenerales();
            opcion = TecladoIn.readLineInt();
        }
    }

    public static void batallaDeEquipos(GestorEquipos BDequipos) {
        String e1, e2;
        Equipo equipo1, equipo2;
        System.out.println("Ingrese el nombre del Equipo 1");
        e1 = TecladoIn.readLine();
        System.out.println("Ingrese el nombre del Equipo 2");
        e2 = TecladoIn.readLine();
        equipo1 = BDequipos.obtenerEquipo(new Equipo(e1));
        equipo2 = BDequipos.obtenerEquipo(new Equipo(e2));
        if (equipo1.getCategoria() > equipo2.getCategoria()) {
            batalla(equipo2, equipo1);
        } else {
            batalla(equipo1, equipo2);
        }
    }

    private static void batalla(Equipo e1, Equipo e2) {
        int i = 1;
        while (i < 5 && e1.getPerdi() == e2.getPerdi()) {
            if (1 % 2 == 0) {
                round(e1, e2);
            } else {
                round(e2, e1);
            }
            i++;
        }
        if (e1.getPerdi() == e2.getPerdi()) {
            System.out.println("La Batalla Termino y es un Empate");
            e1.empate();
            e2.empate();
        }else{
            e1.actualizarFinDeBatalla();
            e2.actualizarFinDeBatalla();
            System.out.println("El Equipo 1 "+e1.getNombre()+ "gano "+e1.getPerdi());
            System.out.println("El Equipo 2 "+e2.getNombre()+ "gano "+e2.getPerdi());
        }
    }

    private static void round(Equipo e1, Equipo e2) {
        int batalla = 0;
        Jugador j1, j2;
        while (batalla < 3 && e1.getPerdi() == e2.getPerdi()) {
            j1 = e1.obtenerUnJugador();
            j2 = e2.obtenerUnJugador();
            if (j1 != null && j2 != null) {
                if (pvp(j1, j2)) {
                    e2.agregarKill();
                }
            } else {
                if (j2 == null) {//Si j2 es nulL TODOS del e2 murieron
                    e1.Gane();
                }else{
                    e2.Gane();
                }
            }
            batalla++;
        }
    }

    private static boolean pvp(Jugador j1, Jugador j2) {
        boolean gane = false;
        if (j2.recibirAtaque(j1.ataque())) {//Si true, J2 murio y Gano J1
            j1.gane();
            j2.perdi();
            gane = true;
        }
        j1.actualizarItems();
        return gane;
    }

}
