package org.example.practica4_algoritmos;

public class Lista<T> {
    private Node<T> inicio;
    private int tamanio;

    public ListaSimple() {
        this.inicio = null;
        this.tamanio = 0;
    }

    public void insertarInicio(T dato) {
        Node<T> n = new Node<>(dato);
        n.setSig(inicio);
        inicio = n;
        tamanio++;
    }

    public void insertarFinal(T dato) {
        Node<T> n = new Node<>(dato);
        if (inicio == null) {
            inicio = n;
        } else {
            Node<T> r = inicio;
            while (r.getSig() != null) {
                r = r.getSig();
            }
            r.setSig(n);
        }
        tamanio++;
    }

    public T eliminarInicio() {
        if (inicio == null) {
            return null;
        }
        T dato = inicio.getInfo();
        inicio = inicio.getSig();
        tamanio--;
        return dato;
    }

    public T obtener(int indice) {
        if (indice < 0 || indice >= tamanio) {
            return null;
        }
        Node<T> actual = inicio;
        int contador = 0;

        while (actual != null) {
            if (contador == indice) {
                return actual.getInfo();
            }
            actual = actual.getSig();
            contador++;
        }
        return null;
    }

    public void asignar(int indice, T dato) {
        if (indice < 0 || indice >= tamanio) {
            return;
        }
        Node<T> actual = inicio;
        int contador = 0;

        while (actual != null) {
            if (contador == indice) {
                actual.setInfo(dato);
                return;
            }
            actual = actual.getSig();
            contador++;
        }
    }

    public int tamanio() {
        return tamanio;
    }

    public boolean estaVacia() {
        return inicio == null;
    }

    public void vaciar() {
        inicio = null;
        tamanio = 0;
    }

    public T peekInicio() {
        if (inicio == null) {
            return null;
        }
        return inicio.getInfo();
    }
}
