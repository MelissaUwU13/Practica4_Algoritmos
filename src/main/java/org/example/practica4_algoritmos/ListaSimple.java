package org.example.practica4_algoritmos;

public class ListaSimple<T> {

    //esto no aqui asi que modificar!!!
    private static class Nodo<E> {
        E info;
        Nodo<E> sig;
        Nodo(E info) { this.info = info; }
    }

    private Nodo<T> inicio;
    private int tamanio;

    public ListaSimple() {
        this.inicio = null;
        this.tamanio = 0;
    }

    public void insertarInicio(T dato) {
        Nodo<T> n = new Nodo<>(dato);
        n.sig = inicio;
        inicio = n;
        tamanio++;
    }

    public void insertarFinal(T dato) {
        Nodo<T> n = new Nodo<>(dato);
        if (inicio == null) {
            inicio = n;
        } else {
            Nodo<T> r = inicio;
            while (r.sig != null) r = r.sig;
            r.sig = n;
        }
        tamanio++;
    }

    public T eliminarInicio() {
        if (inicio == null) return null;
        T dato = inicio.info;
        inicio = inicio.sig;
        tamanio--;
        return dato;
    }

    public T obtener(int indice) {
        if (indice < 0 || indice >= tamanio) return null;
        Nodo<T> actual = inicio;
        for (int i = 0; i < indice; i++) actual = actual.sig;
        return actual.info;
    }

    public void asignar(int indice, T dato) {
        if (indice < 0 || indice >= tamanio) return;
        Nodo<T> actual = inicio;
        for (int i = 0; i < indice; i++) actual = actual.sig;
        actual.info = dato;
    }

    public int tamanio() { return tamanio; }
    public boolean estaVacia() { return inicio == null; }
    public void vaciar() { inicio = null; tamanio = 0; }
    public T peekInicio() { return inicio == null ? null : inicio.info; }
}