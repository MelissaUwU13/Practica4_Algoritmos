package org.example.practica4_algoritmos;

import java.util.Random;

public class Tablero {
    private int filas, columnas;
    private Node[][] nodos;
    private ListaSimple<Casilla> casillas; // lista lineal
    private static final int MAX_FILAS = 18;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.nodos = new Node[filas][columnas];
        this.casillas = new ListaSimple<>();
        generarInicial();
        conectarNodos();
    }

    private void generarInicial() {
        Random rand = new Random();
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                int valor = rand.nextInt(9) + 1; // 1-9
                Casilla cas = new Casilla(f, c, valor);
                Node node = new Node(cas);
                nodos[f][c] = node;
                casillas.insertarFinal(cas);
            }
        }
    }

    private void conectarNodos() {
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                Node actual = nodos[f][c];
                // up
                if (f > 0) actual.setUp(nodos[f-1][c]);
                // down
                if (f < filas-1) actual.setDown(nodos[f+1][c]);
                // left
                if (c > 0) actual.setLeft(nodos[f][c-1]);
                // right
                if (c < columnas-1) actual.setRight(nodos[f][c+1]);
                // upLeft
                if (f > 0 && c > 0) actual.setUpLeft(nodos[f-1][c-1]);
                // upRight
                if (f > 0 && c < columnas-1) actual.setUpRight(nodos[f-1][c+1]);
                // downLeft
                if (f < filas-1 && c > 0) actual.setDownLeft(nodos[f+1][c-1]);
                // downRight
                if (f < filas-1 && c < columnas-1) actual.setDownRight(nodos[f+1][c+1]);
            }
        }
    }

    public Casilla getCasilla(int fila, int columna) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) return null;
        return nodos[fila][columna].getCasilla();
    }

    public Node getNode(int fila, int columna) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) return null;
        return nodos[fila][columna];
    }

    public ListaSimple<Casilla> getCasillas() { return casillas; }
    public int getFilas() { return filas; }
    public int getColumnas() { return columnas; }
    public int getTotalCasillas() { return casillas.tamanio(); }
    public int getMaxFilas() { return MAX_FILAS; }
    public boolean puedeCrecer() { return filas < MAX_FILAS; }

    // Método que agrega números activos al final (como antes, pero ajustado)
    public boolean agregarNumerosActivosAlFinal() {
        if (!puedeCrecer()) return false;

        ListaSimple<Integer> valores = new ListaSimple<>();
        for (int i = 0; i < casillas.tamanio(); i++) {
            Casilla c = casillas.obtener(i);
            if (c != null && c.isActiva()) valores.insertarFinal(c.getValor());
        }
        if (valores.estaVacia()) return false;

        int espaciosDisponibles = (MAX_FILAS - filas) * columnas;
        int totalAInsertar = Math.min(valores.tamanio(), espaciosDisponibles);

        // Creamos nueva matriz ampliada
        int nuevasFilas = filas + (totalAInsertar + columnas - 1) / columnas;
        Node[][] nuevaMalla = new Node[nuevasFilas][columnas];
        // Copiar nodos existentes
        for (int f = 0; f < filas; f++) {
            System.arraycopy(nodos[f], 0, nuevaMalla[f], 0, columnas);
        }
        // Rellenar nuevos nodos con los valores
        int idx = 0;
        for (int f = filas; f < nuevasFilas; f++) {
            for (int c = 0; c < columnas; c++) {
                if (idx < totalAInsertar) {
                    int valor = valores.obtener(idx);
                    Casilla nuevaCas = new Casilla(f, c, valor);
                    Node newNode = new Node(nuevaCas);
                    nuevaMalla[f][c] = newNode;
                    casillas.insertarFinal(nuevaCas);
                    idx++;
                } else {
                    Casilla vacia = new Casilla(f, c, 0);
                    vacia.setActiva(false);
                    Node newNode = new Node(vacia);
                    nuevaMalla[f][c] = newNode;
                    casillas.insertarFinal(vacia);
                }
            }
        }
        this.nodos = nuevaMalla;
        this.filas = nuevasFilas;
        // Reconectar nodos (simple: llamar a conectarNodos completa)
        conectarNodos();
        return true;
    }

    // Necesario para actualizar la lista lineal cuando se desactiva una casilla (no se elimina de la lista)
    // Pero como la lista solo se usa para recorrer, no hay problema.
}