/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import java.util.Random;

/**
 *
 * @author Torrico
 */
public class Grafo {

    private NodoVert inicio;

    public Grafo() {
        this.inicio = null;

    }

    public Grafo(NodoVert inicio) {
        this.inicio = inicio;
    }

    public NodoVert getInicio() {
        return inicio;
    }
    public int cantidadVertices(){
    int largo=0;
    NodoVert aux=this.inicio;
        while (aux!=null) {            
            largo++;
            aux=aux.getSigVertice();
        }
    return largo;}
    public Object obtenerVerticeAleatorio() {
        NodoVert aux = this.inicio;
        Random ale=new Random();
        Object fin;
        int cant=ale.nextInt(cantidadVertices());
        int i=1;
        while (i!=cant) {
            aux = aux.getSigVertice();
            i++;
        }
        fin=aux.getElem();
        return fin;
    }
   
    private NodoVert ubicarVertice(Object vertBuscado) {
        NodoVert aux = this.inicio;
        //recorro los vertices mientras todavia queden o todavia no encontre el buscado
        while (aux != null && !aux.getElem().equals(vertBuscado)) {

            aux = aux.getSigVertice();

        }
        return aux;
    }

    public boolean insertarVertice(Object nuevoVert) {
        boolean respuesta = false;

        NodoVert aux = ubicarVertice(nuevoVert);
        //agrego el vertice si no existe
        if (aux == null) {
            // siempre al principio
            this.inicio = new NodoVert(nuevoVert, this.inicio);
            respuesta = true;
        }

        return respuesta;
    }

    public boolean eliminarVertice(Object vertice) {
        boolean exito = false;

        NodoVert verticeTemp = ubicarVertice(vertice);
        //si el vertice existe...
        if (verticeTemp != null) {
            //System.out.println(verticeTemp.getElem());
            //si es el primero seteo al inicio el siguiente
            if (verticeTemp.equals(this.inicio)) {

                eliminarVerticeAux(this.inicio);
                this.inicio = this.inicio.getSigVertice();

            } else {// sino lo busco para asignarle el siguiente
                boolean encontrado = false;
                NodoVert verticeAux = this.inicio;
                while (verticeAux != null && !encontrado) {
                    if (verticeAux.getElem().equals(vertice)) {
                        eliminarVerticeAux(verticeAux);
                        verticeTemp.setSigVertice(verticeAux.getSigVertice());
                        encontrado = true;
                    } else { //si no es paso al siguiente
                        verticeTemp = verticeAux;
                        verticeAux = verticeTemp.getSigVertice();
                    }
                }
            }
            exito = true;
        }
        return exito;
    }

    private void eliminarVerticeAux(NodoVert verticeEliminar) {
        //Elimina todas las adyacencias de un vertice dado
        NodoAdy adyOrigen = verticeEliminar.getPrimerAdy();
        //elimino todos los que tiene al vertice como destino
        while (adyOrigen != null) {
            //si tiene adyacencia con algun otro vertice
            NodoVert destino = adyOrigen.getVertice();
            //por cada adyacente se que voy a tener un adyacente entonces accedo al origen y elimino el 
            //NODOADY que corresponde al vertice a eliminar
            if (destino != null && destino.getPrimerAdy().getVertice().equals(verticeEliminar)) {
                //si es primer ady le seteo el siguiente como primero
                destino.setPrimerAdy(adyOrigen.getSigAdyacente());
            }//sino recorro hasta encontrarlo
            else {
                if (destino != null) {
                    NodoAdy adyAux = destino.getPrimerAdy();
                    boolean eliminado = false;
                    while (adyAux != null && !eliminado) {//Controlar si ya elimina no continuar!!!!!!
                        if (adyAux.getSigAdyacente() != null) {
                            if (adyAux.getSigAdyacente().getVertice().equals(verticeEliminar)) {

                                adyAux.setSigAdyacente(adyAux.getSigAdyacente().getSigAdyacente());
                                eliminado = true;
                            }
                        }
                        adyAux = adyAux.getSigAdyacente();
                    }
                }
            }

            adyOrigen = adyOrigen.getSigAdyacente();
        }
    }

    public boolean insertarArco(Object origen, Object destino, Comparable etiqueta) {
        //Permite insertar arco con etiqueta entre dos nodos, 
        //lo hace en ambas direcciones de origen a destino y destino a origen
        boolean exito = false;
        //Ubica los dos nodos origen y destino
        NodoVert auxOrigen = this.ubicarVertice(origen);
        NodoVert auxDestino = this.ubicarVertice(destino);
        if (auxDestino != null && auxOrigen != null) {

            if (!existeArco(auxOrigen, auxDestino)) {
                insertarArcoAux(auxOrigen, auxDestino, etiqueta);
                insertarArcoAux(auxDestino, auxOrigen, etiqueta);
                exito = true;
            }
        }

        return exito;
    }

    private void insertarArcoAux(NodoVert origen, NodoVert destino, Comparable etiqueta) {

        NodoAdy nuevoArc = new NodoAdy(destino, etiqueta);
        nuevoArc.setSigAdyacente(origen.getPrimerAdy());
        origen.setPrimerAdy(nuevoArc);
    }

    private boolean existeArco(NodoVert origen, NodoVert destino) {
        boolean existe = false;
        NodoAdy auxAdy = origen.getPrimerAdy();
        while (auxAdy != null && !existe) {
            if (auxAdy.getVertice().equals(destino)) {

                existe = true;
            } else {
                auxAdy = auxAdy.getSigAdyacente();
            }

        }
        return existe;
    }

    public boolean eliminarArco(Object origen, Object destino) {
        //elimina todo arco de origen a destino sabiendo que siempre existe un unico arco
        //reprecentado por 2 nodoAdy indicado por no ser un digrafo
        boolean exito = false;
        NodoVert vertOrigen = ubicarVertice(origen);
        NodoVert vertDestino = ubicarVertice(destino);
        if (existeArco(vertOrigen, vertDestino)) {
            //elimina de origen a destino
            eliminarArcoAux(vertOrigen, vertDestino);
            //elimina de destino a origen 
            eliminarArcoAux(vertDestino, vertOrigen);
            exito = true;
        }
        return exito;
    }

    private void eliminarArcoAux(NodoVert origen, NodoVert destino) {
        //elimina el arco (nodoAdy )formado de origen a destino
        NodoAdy adyOrigen = origen.getPrimerAdy();
        // si es el primer adyacente
        if (adyOrigen.getVertice().equals(destino)) {
            origen.setPrimerAdy(adyOrigen.getSigAdyacente());
        } else {
            NodoAdy auxAdy = adyOrigen.getSigAdyacente();
            boolean encontrado = false;
            while (auxAdy != null && !encontrado) {
                if (auxAdy.getVertice().equals(destino)) {
                    adyOrigen.setSigAdyacente(auxAdy.getSigAdyacente());
                    encontrado = true;
                } else {
                    adyOrigen = auxAdy;
                    auxAdy = auxAdy.getSigAdyacente();
                }
            }
        }
    }

    public boolean existeVertice(Object vertice) {
        boolean existe = false;
        NodoVert auxVert = ubicarVertice(vertice);
        if (auxVert != null) {
            existe = true;
        }
        return existe;
    }

    public boolean existeCamino(Object origen, Object destino) {
        boolean exito = false;
        NodoVert auxOrigen = ubicarVertice(origen);
        NodoVert auxDestino = ubicarVertice(destino);
        // si ambos existen
        if (auxOrigen != null && auxDestino != null) {
            Lista visitados = new Lista();

            exito = existeCaminoAux(auxOrigen, destino, visitados);
        }
        return exito;
    }

    public boolean existeCaminoAux(NodoVert origen, Object destino, Lista visitados) {
        boolean exito = false;
        if (origen != null) {
            if (origen.getElem().equals(destino)) {
                //Si origen es el destino, entonces existe camino
                exito = true;
            } else {
                //Si no es el destino, verifica si hay camino entre origen y destino
                visitados.insertar(origen.getElem(), visitados.longitud() + 1);
                NodoAdy ady = origen.getPrimerAdy();
                while (!exito && ady != null) {
                    if (visitados.localizar(ady.getVertice().getElem()) < 0) {
                        //Si no esta en la lista de visitados, continua la busqueda recursiva del camino
                        exito = existeCaminoAux(ady.getVertice(), destino, visitados);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
        }
        return exito;
    }

    public Lista listarEnProfundidad() {

        Lista visitados = new Lista();

        NodoVert aux = this.inicio;
        while (aux != null) {
            if (visitados.localizar(aux.getElem()) < 0) {

                listarEnProfundidadAux(aux, visitados);

            }
            aux = aux.getSigVertice();

        }
        return visitados;
    }

    private void listarEnProfundidadAux(NodoVert n, Lista vis) {
        if (n != null) {

            vis.insertar(n.getElem(), vis.longitud() + 1);
            NodoAdy ady = n.getPrimerAdy();
            while (ady != null) {
                if (vis.localizar(ady.getVertice().getElem()) < 0) {
                    listarEnProfundidadAux(ady.getVertice(), vis);
                }
                ady = ady.getSigAdyacente();
            }

        }
    }

    public Lista listarEnAnchura() {
        //Permite listar en anchura todos los nodos
        Lista visitados = new Lista();
        NodoVert aux = this.inicio;
        while (aux != null) {
            //Permite listar cada nodo vertice
            if (visitados.localizar(aux.getElem()) < 0) {
                //Si no fue visitado entonces realiza recursividad
                listarEnAnchuraAux(aux, visitados);
            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }

    private void listarEnAnchuraAux(NodoVert vertice, Lista visitados) {
        //Metodo auxiliar para listar en anchura
        ColaObject q = new ColaObject();
        q.poner(vertice);
        NodoVert aux;
        while (!q.esVacia()) {
            //Mientras que la cola no este vacia obtengo el frente y saco
            aux = (NodoVert) q.obtenerFrente();
            q.sacar();
            //Si no fue visitado
            if (visitados.localizar(aux.getElem()) < 0) {
                //Inserto en la lista el nodo
                visitados.insertar(aux.getElem(), visitados.longitud() + 1);
            }
            NodoAdy ady = aux.getPrimerAdy();
            //Permite obtener todos los adyacentes
            while (ady != null) {
                //Permite listar a todos los nodos adyacentes
                if (visitados.localizar(ady.getVertice().getElem()) < 0) {
                    q.poner(ady.getVertice());
                }
                ady = ady.getSigAdyacente();
            }
        }
    }

    public Lista caminoMasCorto(Object origen, Object destino) {

        Lista caminoMasCorto = new Lista();
        Lista caminoFinal = new Lista();
        Lista visitados = new Lista();

        NodoVert vertOrigen = this.ubicarVertice(origen);
        NodoVert vertDestino = this.ubicarVertice(destino);

        if (vertOrigen != null && vertDestino != null && !vertOrigen.equals(vertDestino)) {

            caminoFinal = caminoMasCortoAux(vertOrigen, caminoMasCorto, visitados, destino);
        }

        return caminoFinal;

    }

    private Lista caminoMasCortoAux(NodoVert n, Lista caminoMasCorto, Lista visitados, Object destino) {
        // si todavia quedan vertices por lo tanto no es null
        if (n != null) {
            //inserto el nodo en el que estoy parado como visitado
            visitados.insertar(n.getElem(), visitados.longitud() + 1);
            //si es el destino
            if (n.getElem().equals(destino)) {
                //termine la recurcion por lo tanto comparo si ese camino es mas 
                //corto que Camino mas corto actual o recien inicio y caminoMasCorto es vacio
                if (visitados.longitud() < caminoMasCorto.longitud() || caminoMasCorto.esVacia()) {

                    caminoMasCorto = visitados.clone();
                }
                //sino empieso a recorrer sus arcos para encontrar camino mas corto
            } else {
                NodoAdy adyacente = n.getPrimerAdy();
                while (adyacente != null) {
                    //si el nodo aun no fue visitado
                    if (visitados.localizar(adyacente.getVertice().getElem()) < 0) {
                        //y ademas si al visitarlo la longitud es mayor a la de camino mas corto no voy por ese camino 
                        if (visitados.longitud() < caminoMasCorto.longitud() ^ caminoMasCorto.esVacia()) {

                            caminoMasCorto = caminoMasCortoAux(adyacente.getVertice(), caminoMasCorto, visitados, destino);
                        }
                    }
                    adyacente = adyacente.getSigAdyacente();//paso a buscar otro camino
                }

            }

        }

        visitados.eliminar(visitados.longitud());

        return caminoMasCorto;
    }

    public Lista caminoMasCortoEnKm(Object origen, Object destino) {

        Lista caminoMasCorto = new Lista();
        Lista caminoFinal = new Lista();
        Lista visitados = new Lista();
        double[] kms = new double[2];
        kms[0] = Double.MAX_VALUE;//MAX_INT
        kms[1] = 0;
        NodoVert vertOrigen = this.ubicarVertice(origen);
        NodoVert vertDestino = this.ubicarVertice(destino);

        if (vertOrigen != null && vertDestino != null && !vertOrigen.equals(vertDestino)) {

            caminoFinal = caminoMasCortoKmAux(vertOrigen, caminoMasCorto, visitados, destino, kms);
        }

        return caminoFinal;

    }

    //utilice un array de 2 elementos para identificar kilometros actuales con el menor km
    private Lista caminoMasCortoKmAux(NodoVert n, Lista caminoMasCorto,
            Lista visitados, Object destino, double[] kms) {

        if (n != null) {
            visitados.insertar(n.getElem(), visitados.longitud() + 1);
            if (n.getElem().equals(destino)) {// estoy en el nodo destino
                if (kms[1] < kms[0]) {//verifico si los kilometros del camino actua son menor al masCorto
                    caminoMasCorto = visitados.clone();
                    kms[0] = kms[1];
                }
            } else {//si no estoy sigo recorriendo
                NodoAdy adyacente = n.getPrimerAdy();
                while (adyacente != null) {//kmActual un double llamada= kmActual + adya.....
                    kms[1] += (double) adyacente.getEtiqueta();//sumo los kilometros del arco actual
                    if (kms[1] < kms[0]) {//verifico si es menor mientras no llegue al destino
                        if (visitados.localizar(adyacente.getVertice().getElem()) < 0) {
                            caminoMasCorto = caminoMasCortoKmAux(adyacente.getVertice(), caminoMasCorto, visitados, destino, kms);
                        }
                    } else {//si es mayor no debo seguir por ese camino
                        System.out.println("Este camino ya es mas largo ");
                    }
                    kms[1] -= (double) adyacente.getEtiqueta();//vuelta de la recursion resto kms del nodo
                    adyacente = adyacente.getSigAdyacente();//paso al siguiente adyacente 
                }

            }
            visitados.eliminar(visitados.longitud());
        }
        return caminoMasCorto;
    }

    public Lista caminoMasCortoPorCiudad(Object origen, Object destino, Object medio) {
        NodoVert aux0 = ubicarVertice(origen);
        NodoVert aux1 = ubicarVertice(destino);
        NodoVert aux2 = ubicarVertice(medio);

        Lista visitados = new Lista();
        Lista camino = new Lista();
        boolean[] arrBool = new boolean[1];
        double[] arrDoub = new double[1];
        arrBool[0] = false;
        arrDoub[0] = Double.MAX_VALUE;
        if (aux0 != null && aux1 != null && aux2 != null) {
            camino = caminoMasCortoPorCiudadAux(aux0, destino, medio, visitados, camino, arrBool, arrDoub, 0);
        }
        return camino;
    }

    //Utilice un array de un booleano para identificar cuando estoy o no por la ciudad intermedia
    private Lista caminoMasCortoPorCiudadAux(NodoVert n, Object destino, Object medio, Lista visitados, Lista camino,
            boolean[] bandera, double[] distMin, double distActual) {

        if (n != null) {//Si no es nulo
            visitados.insertar(n.getElem(), visitados.longitud() + 1);
            if (n.getElem().equals(medio)) {
                bandera[0] = true;
            }
            if (n.getElem().equals(destino)) {
                if (bandera[0]) {
                    if (distActual < distMin[0]) {
                        camino = visitados.clone();
                        distMin[0] = distActual;
                    }
                }
            } else {
                NodoAdy arco = n.getPrimerAdy();
                while (arco != null) {
                    distActual += (double) arco.getEtiqueta();
                    if (distActual < distMin[0]) {
                        if (visitados.localizar(arco.getVertice().getElem()) < 0) {
                            camino = caminoMasCortoPorCiudadAux(arco.getVertice(), destino, medio, visitados, camino,
                                    bandera, distMin, distActual);
                            if (arco.getVertice().getElem().equals(medio)) {
                                bandera[0] = false;
                            }
                        }
                    }
                    distActual -= (double) arco.getEtiqueta();
                    arco = arco.getSigAdyacente();
                }
            }
            visitados.eliminar(visitados.longitud());
        }
        return camino;

    }

    public Cola caminosEntre(Object origen, Object destino) {
        Lista visitados = new Lista();
        Cola q = new Cola();
        NodoVert aux0 = ubicarVertice(origen);
        NodoVert aux1 = ubicarVertice(destino);
        if (aux0 != null && aux1 != null) {
            caminosEntreAux(aux0, destino, visitados, q);
        }
        return q;
    }

    private void caminosEntreAux(NodoVert n, Object destino, Lista visitados, Cola q) {

        if (n != null) {
            visitados.insertar(n.getElem(), visitados.longitud() + 1);
            if (n.getElem().equals(destino)) {
                q.poner(visitados.clone());
            } else {
                NodoAdy arco = n.getPrimerAdy();
                while (arco != null) {
                    if (visitados.localizar(arco.getVertice().getElem()) < 0) {
                        caminosEntreAux(arco.getVertice(), destino, visitados, q);
                    }
                    arco = arco.getSigAdyacente();
                }

            }
            visitados.eliminar(visitados.longitud());
        }

    }

    public Object obtenerElemento(Object verticeBuscado) {
        return (ubicarVertice(verticeBuscado)).getElem();
    }

    public Lista obtenerAdyacentes(Object elem) {
        Lista unaL = new Lista();
        NodoVert unNodo = ubicarVertice(elem);
        if (unNodo != null) {
            NodoAdy aux = unNodo.getPrimerAdy();

            while (aux != null) {
                unaL.insertar(aux, unaL.longitud() + 1);
                aux = aux.getSigAdyacente();
            }
        }

        return unaL;
    }

    @Override
    public String toString() {
        String cad = "";

        NodoVert n = this.inicio;
        while (n != null) {
            cad += "\nVertice: " + n.getElem() + "\n";
            NodoAdy arco = n.getPrimerAdy();

            cad += "Arcos: \n";
            while (arco != null) {
                cad += "etiqueta: " + arco.getEtiqueta() + " ------> "
                        + arco.getVertice().getElem() + "\n";
                arco = arco.getSigAdyacente();
            }
            n = n.getSigVertice();
        }
        return cad;
    }

}
