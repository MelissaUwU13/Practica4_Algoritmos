package org.example.practica4_algoritmos;

//Esta clase nos sirve para representar el valor de la tabla, para almacenar la informacion
//Su posicion y su estado
public class Casilla implements Comparable<Casilla> {
    private int fila, columna, valor;
    private boolean activa, seleccionada;

    public Casilla(int fila, int columna, int valor) {
        this.fila = fila;
        this.columna = columna;
        this.valor = valor;
        this.activa = true;
        this.seleccionada = false;
    }

    // getters y setters
    public int getFila() { return fila; }
    public void setFila(int fila) { this.fila = fila; }
    public int getColumna() { return columna; }
    public void setColumna(int columna) { this.columna = columna; }
    public int getValor() { return valor; }
    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }
    public boolean isSeleccionada() { return seleccionada; }
    public void setSeleccionada(boolean seleccionada) { this.seleccionada = seleccionada; }

    //Comparamos si las casillas tienen el mismo valor o si la suma de las dos es igual a 10
    @Override
    public int compareTo(Casilla otra) {
        if (otra == null) return -1;
        if (valor == otra.valor || valor + otra.valor == 10) return 0;
        return Integer.compare(valor, otra.valor);
    }
}