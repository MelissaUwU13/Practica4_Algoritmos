package org.example.practica4_algoritmos;

public class Movimiento {
    private Casilla casilla1, casilla2;
    private int puntosAntes, encontradasAntes, pendientesAntes, pistasAntes;

    public Movimiento(Casilla c1, Casilla c2, int puntos, int encontradas, int pendientes, int pistas) {
        this.casilla1 = c1;
        this.casilla2 = c2;
        this.puntosAntes = puntos;
        this.encontradasAntes = encontradas;
        this.pendientesAntes = pendientes;
        this.pistasAntes = pistas;
    }

    public Casilla getCasilla1() { return casilla1; }
    public Casilla getCasilla2() { return casilla2; }
    public int getPuntosAntes() { return puntosAntes; }
    public int getEncontradasAntes() { return encontradasAntes; }
    public int getPendientesAntes() { return pendientesAntes; }
    public int getPistasAntes() { return pistasAntes; }
}