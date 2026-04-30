package org.example.practica4_algoritmos;

import java.util.Random;

public class Tablero {
    private int filas, columnas;
    private ListaSimple<ListaSimple<Node<Casilla>>> filasNodos; // lista de filas
    private ListaSimple<Casilla> casillas; // lista lineal de todas las casillas
    private static final int MAX_FILAS = 18;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.filasNodos = new ListaSimple<>();
        this.casillas = new ListaSimple<>();
        generarInicial();
        conectarNodos();
    }

    private void generarInicial() {
        Random rand = new Random();
        for (int f = 0; f < filas; f++) {
            ListaSimple<Node<Casilla>> filaActual = new ListaSimple<>();
            for (int c = 0; c < columnas; c++) {
                int valor = rand.nextInt(9) + 1;
                Casilla cas = new Casilla(f, c, valor);
                Node<Casilla> node = new Node<>(cas);
                filaActual.insertarFinal(node);
                casillas.insertarFinal(cas);
            }
            filasNodos.insertarFinal(filaActual);
        }
    }

    // Obtiene un nodo por coordenadas (búsqueda O(filas+columnas))
    private Node<Casilla> obtenerNodo(int fila, int col) {
        if (fila < 0 || fila >= filas || col < 0 || col >= columnas) return null;
        ListaSimple<Node<Casilla>> filaNodos = filasNodos.obtener(fila);
        return filaNodos.obtener(col);
    }

    // Conecta todos los nodos (8 direcciones) usando referencias
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
    public boolean puedeCrecer() { return filas < MAX_FILAS; }

    // Agrega nuevas filas con los números activos
    public boolean agregarNumerosActivosAlFinal() {
        if (!puedeCrecer()) return false;

        // Recolectar valores de casillas activas
        ListaSimple<Integer> valores = new ListaSimple<>();
        for (int i = 0; i < casillas.tamanio(); i++) {
            Casilla c = casillas.obtener(i);
            if (c != null && c.isActiva()) valores.insertarFinal(c.getValor());
        }
        if (valores.estaVacia()) return false;

        int espaciosDisponibles = (MAX_FILAS - filas) * columnas;
        int totalAInsertar = Math.min(valores.tamanio(), espaciosDisponibles);
        int nuevasFilas = filas + (totalAInsertar + columnas - 1) / columnas;

        // Crear nuevas filas y nodos
        for (int f = filas; f < nuevasFilas; f++) {
            ListaSimple<Node<Casilla>> nuevaFila = new ListaSimple<>();
            for (int c = 0; c < columnas; c++) {
                if (valores.tamanio() > 0) {
                    int valor = valores.eliminarInicio();
                    Casilla nuevaCas = new Casilla(f, c, valor);
                    Node<Casilla> newNode = new Node<>(nuevaCas);
                    nuevaFila.insertarFinal(newNode);
                    casillas.insertarFinal(nuevaCas);
                } else {
                    Casilla vacia = new Casilla(f, c, 0);
                    vacia.setActiva(false);
                    Node<Casilla> newNode = new Node<>(vacia);
                    nuevaFila.insertarFinal(newNode);
                    casillas.insertarFinal(vacia);
                }
            }
            filasNodos.insertarFinal(nuevaFila);
        }
        this.filas = nuevasFilas;
        conectarNodos(); // Reconectar todo el tablero
        return true;
    }
}