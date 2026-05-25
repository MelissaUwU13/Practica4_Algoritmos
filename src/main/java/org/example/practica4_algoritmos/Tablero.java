package org.example.practica4_algoritmos;

import java.util.Random;

public class Tablero {
    private int filas, columnas;
    private ListaSimple<ListaSimple<Node<Casilla>>> filasNodos; //lista de listas
    private ListaSimple<Casilla> casillas; //lista de las casillas
    private static final int MAX_FILAS = 18;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        //lista madre
        this.filasNodos = new ListaSimple<>();
        this.casillas = new ListaSimple<>();
        generarInicial();
        conectarNodos();
    }

    //Para generar el tablero, estaremos trabajando con listas, en la cual tenemos una lista madre
    //donde cada uno de los elementos va ingresado listas, formando asi el tablero
    private void generarInicial() {
        Random rand = new Random();
        for (int f = 0; f < filas; f++) {
            ListaSimple<Node<Casilla>> filaActual = new ListaSimple<>();

            //Ingresamos las casillas en las filas
            for (int c = 0; c < columnas; c++) {
                int valor = rand.nextInt(9) + 1;
                Casilla cas = new Casilla(f, c, valor);
                Node<Casilla> node = new Node<>(cas);
                filaActual.insertarFinal(node);
                casillas.insertarFinal(cas);
            }
            //Ingresamos las subs listas en la lista madre
            filasNodos.insertarFinal(filaActual);
        }
    }

    //Obtiene un nodo por coordenadas
    private Node<Casilla> obtenerNodo(int fila, int col) {
        if (fila < 0 || fila >= filas || col < 0 || col >= columnas) return null;
        ListaSimple<Node<Casilla>> filaNodos = filasNodos.obtener(fila);
        return filaNodos.obtener(col);
    }

    //Conecta todos los nodos usando referencias
    private void conectarNodos() {
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                Node<Casilla> actual = obtenerNodo(f, c);
                // Up
                if (f > 0) actual.setUp(obtenerNodo(f-1, c));
                // Down
                if (f < filas-1) actual.setDown(obtenerNodo(f+1, c));
                // Left
                if (c > 0) actual.setLeft(obtenerNodo(f, c-1));
                // Right
                if (c < columnas-1) actual.setRight(obtenerNodo(f, c+1));
                // UpLeft
                if (f > 0 && c > 0) actual.setUpLeft(obtenerNodo(f-1, c-1));
                // UpRight
                if (f > 0 && c < columnas-1) actual.setUpRight(obtenerNodo(f-1, c+1));
                // DownLeft
                if (f < filas-1 && c > 0) actual.setDownLeft(obtenerNodo(f+1, c-1));
                // DownRight
                if (f < filas-1 && c < columnas-1) actual.setDownRight(obtenerNodo(f+1, c+1));
            }
        }
    }

    //GETTERS
    public Casilla getCasilla(int fila, int col) {
        Node<Casilla> node = obtenerNodo(fila, col);
        return node == null ? null : node.getContent();
    }
    public Node<Casilla> getNode(int fila, int col) {
        return obtenerNodo(fila, col);
    }
    public ListaSimple<Casilla> getCasillas() { return casillas; }
    public int getFilas() { return filas; }
    public int getColumnas() { return columnas; }
    public int getTotalCasillas() { return casillas.tamanio(); }
}