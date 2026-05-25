package org.example.practica4_algoritmos;

//Clase lista Simple en la cual trabajamos en clase
public class ListaSimple<T> {
    private Node<T> inicio;
    private int tamanio;

    public ListaSimple(){
        this.inicio = null;
    }

    public void insertarInicio(T dato) {
        Node<T> n = new Node<>(dato);
        n.setInfo(dato);
        n.setSig(inicio);
        inicio = n;
        tamanio++;
    }

    public void insertarFinal(T dato) {
        Node<T> n = new Node<>(dato);
        n.setInfo(dato);

        if (inicio == null) {
            n.setSig(null);
            inicio = n;
        } else {
            Node<T> r = inicio;
            while (r.getSig() != null) {
                r = r.getSig();
            }
            r.setSig(n);
            n.setSig(null);
        }
        tamanio++;
    }

    public T eliminarInicio() {
        T regreso = null;
        if (inicio == null) {
            System.out.println("Lista Vacia");
        } else {
            regreso = inicio.getInfo();
            inicio = inicio.getSig();
            tamanio--;
        }
        return regreso;
    }

    public T eliminarFinal() {
        T dato = null;

        if (inicio == null) {
            System.out.println("Lista Vacia");
        } else if (inicio.getSig() == null) {
            dato = inicio.getInfo();
            inicio = null;
            tamanio--;
        } else {
            Node<T> r = inicio;
            Node<T> a = r;
            while (r.getSig() != null) {
                a = r;
                r = r.getSig();
            }
            dato = r.getInfo();
            a.setSig(null);
            tamanio--;
        }

        return dato;
    }

    //Metodo agregado para obtener el valor de x posicion
    public T obtener(int indice) {
        if (indice < 0 || indice >= tamanio) return null;
        Node<T> actual = inicio;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSig();
        }
        return actual.getInfo();
    }

    public int tamanio() {
        return tamanio;
    }

    //Metodo que nos ayudara a determinar si la fila esta vacia
    public boolean estaVacia() {
        return inicio == null;
    }

    public void vaciar() {
        inicio = null;
        tamanio = 0;
    }

    public T peekInicio() {
        return inicio == null ? null : inicio.getInfo();
    }

    public String mostrarLista() {
        String cadena = "";
        Node<T> r = inicio;
        while (r != null) {
            cadena += r.getInfo() + " ";
            r = r.getSig();
        }
        return cadena;
    }
}