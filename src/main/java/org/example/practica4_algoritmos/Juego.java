package org.example.practica4_algoritmos;

public class Juego {
    private Tablero tablero;
    private ListaSimple<Movimiento> historial;
    private int puntos;
    private int concordanciasEncontradas;
    private int concordanciasPendientes;
    private int pistasRestantes;

    public Juego(int filas, int columnas) {
        tablero = new Tablero(filas, columnas);
        historial = new ListaSimple<>();
        puntos = 0;
        concordanciasEncontradas = 0;
        pistasRestantes = 5;
        concordanciasPendientes = calcularConcordanciasPendientes();
    }

    public Tablero getTablero() { return tablero; }
    public int getPuntos() { return puntos; }
    public int getConcordanciasEncontradas() { return concordanciasEncontradas; }
    public int getConcordanciasPendientes() { return concordanciasPendientes; }
    public int getPistasRestantes() { return pistasRestantes; }

    public boolean seleccionarPar(Casilla primera, Casilla segunda) {
        if (primera == null || segunda == null) return false;
        if (primera == segunda) return false;
        if (!primera.isActiva() || !segunda.isActiva()) return false;
        if (primera.compareTo(segunda) != 0) return false;
        if (!esMovimientoValido(primera, segunda)) return false;

        historial.insertarInicio(new Movimiento(primera, segunda, puntos, concordanciasEncontradas, concordanciasPendientes, pistasRestantes));

        primera.setActiva(false);
        segunda.setActiva(false);
        primera.setSeleccionada(false);
        segunda.setSeleccionada(false);

        puntos += primera.getValor() + segunda.getValor();
        concordanciasEncontradas++;
        concordanciasPendientes = calcularConcordanciasPendientes();
        return true;
    }

    // Validación usando nodos y enlaces
    public boolean esMovimientoValido(Casilla a, Casilla b) {
        if (a == null || b == null) return false;
        Node nodoA = tablero.getNode(a.getFila(), a.getColumna());
        Node nodoB = tablero.getNode(b.getFila(), b.getColumna());

        // Misma fila -> recorrer left/right
        if (a.getFila() == b.getFila()) {
            return caminoLibreHorizontal(nodoA, nodoB);
        }
        // Misma columna -> up/down
        if (a.getColumna() == b.getColumna()) {
            return caminoLibreVertical(nodoA, nodoB);
        }
        // Diagonal (|Δfila| == |Δcol|)
        if (Math.abs(a.getFila() - b.getFila()) == Math.abs(a.getColumna() - b.getColumna())) {
            return caminoLibreDiagonal(nodoA, nodoB);
        }
        // Caso especial: borde lineal (final de fila con inicio de la siguiente)
        return caminoLibreLineal(a, b);
    }

    private boolean caminoLibreHorizontal(Node start, Node end) {
        // Asumimos misma fila, start y end son diferentes
        int step = (start.getCasilla().getColumna() < end.getCasilla().getColumna()) ? 1 : -1;
        Node current = (step == 1) ? start.getRight() : start.getLeft();
        while (current != null && current != end) {
            if (current.getCasilla().isActiva()) return false;
            current = (step == 1) ? current.getRight() : current.getLeft();
        }
        return current == end;
    }

    private boolean caminoLibreVertical(Node start, Node end) {
        int step = (start.getCasilla().getFila() < end.getCasilla().getFila()) ? 1 : -1;
        Node current = (step == 1) ? start.getDown() : start.getUp();
        while (current != null && current != end) {
            if (current.getCasilla().isActiva()) return false;
            current = (step == 1) ? current.getDown() : current.getUp();
        }
        return current == end;
    }

    private boolean caminoLibreDiagonal(Node start, Node end) {
        int deltaFila = end.getCasilla().getFila() - start.getCasilla().getFila();
        int deltaCol = end.getCasilla().getColumna() - start.getCasilla().getColumna();
        int pasoFila = deltaFila / Math.abs(deltaFila);
        int pasoCol = deltaCol / Math.abs(deltaCol);

        Node current = start;
        while (true) {
            if (pasoFila == 1 && pasoCol == 1) current = current.getDownRight();
            else if (pasoFila == 1 && pasoCol == -1) current = current.getDownLeft();
            else if (pasoFila == -1 && pasoCol == 1) current = current.getUpRight();
            else current = current.getUpLeft();
            if (current == null || current == end) break;
            if (current.getCasilla().isActiva()) return false;
        }
        return current == end;
    }

    // Borde lineal: final de un renglón (columna = columnas-1) con inicio del siguiente (columna=0, fila+1)
    private boolean caminoLibreLineal(Casilla a, Casilla b) {
        int colFin = tablero.getColumnas() - 1;
        // Caso a en última columna y b en primera columna de la fila siguiente
        if (a.getColumna() == colFin && b.getColumna() == 0 && b.getFila() == a.getFila() + 1) {
            return true; // son adyacentes en el borde, no hay casillas intermedias
        }
        // Caso inverso
        if (b.getColumna() == colFin && a.getColumna() == 0 && a.getFila() == b.getFila() + 1) {
            return true;
        }
        return false;
    }

    public Casilla[] darPista() {
        if (pistasRestantes <= 0) return null;
        for (int i = 0; i < tablero.getTotalCasillas(); i++) {
            Casilla a = tablero.getCasillas().obtener(i);
            if (a == null || !a.isActiva()) continue;
            for (int j = i+1; j < tablero.getTotalCasillas(); j++) {
                Casilla b = tablero.getCasillas().obtener(j);
                if (b == null || !b.isActiva()) continue;
                if (a.compareTo(b) == 0 && esMovimientoValido(a, b)) {
                    pistasRestantes--;
                    return new Casilla[]{a, b};
                }
            }
        }
        return null;
    }

    public boolean deshacer() {
        Movimiento mov = historial.eliminarInicio();
        if (mov == null) return false;
        mov.getCasilla1().setActiva(true);
        mov.getCasilla2().setActiva(true);
        puntos = mov.getPuntosAntes();
        concordanciasEncontradas = mov.getEncontradasAntes();
        concordanciasPendientes = mov.getPendientesAntes();
        pistasRestantes = mov.getPistasAntes();
        return true;
    }

    public boolean agregarNumeros() {
        boolean agregado = tablero.agregarNumerosActivosAlFinal();
        concordanciasPendientes = calcularConcordanciasPendientes();
        return agregado;
    }

    public boolean hayMovimientosDisponibles() {
        for (int i = 0; i < tablero.getTotalCasillas(); i++) {
            Casilla a = tablero.getCasillas().obtener(i);
            if (a == null || !a.isActiva()) continue;
            for (int j = i+1; j < tablero.getTotalCasillas(); j++) {
                Casilla b = tablero.getCasillas().obtener(j);
                if (b == null || !b.isActiva()) continue;
                if (a.compareTo(b) == 0 && esMovimientoValido(a, b)) return true;
            }
        }
        return false;
    }

    public boolean juegoTerminado() {
        return !hayMovimientosDisponibles();
    }

    private int calcularConcordanciasPendientes() {
        int cont = 0;
        for (int i = 0; i < tablero.getTotalCasillas(); i++) {
            Casilla a = tablero.getCasillas().obtener(i);
            if (a == null || !a.isActiva()) continue;
            for (int j = i+1; j < tablero.getTotalCasillas(); j++) {
                Casilla b = tablero.getCasillas().obtener(j);
                if (b == null || !b.isActiva()) continue;
                if (a.compareTo(b) == 0 && esMovimientoValido(a, b)) cont++;
            }
        }
        return cont;
    }
}