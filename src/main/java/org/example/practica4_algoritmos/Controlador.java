package org.example.practica4_algoritmos;

import javafx.stage.Stage;

public class Controlador {
    private Juego juego;
    private JuegoGUI juegoGUI;
    private TableroGUI tableroGUI;
    private Casilla primeraSeleccionada;

    public Controlador() {}

    public void iniciar(Stage stage, int filas, int columnas) {
        juego = new Juego(filas, columnas);
        juegoGUI = new JuegoGUI(this);
        tableroGUI = new TableroGUI(this, juego.getTablero());
        juegoGUI.mostrar(stage, tableroGUI);
        actualizarVista();
    }

    public void manejarSeleccion(int fila, int col) {
        Casilla actual = juego.getTablero().getCasilla(fila, col);
        if (actual == null || !actual.isActiva()) return;

        if (primeraSeleccionada == null) {
            primeraSeleccionada = actual;
            actual.setSeleccionada(true);
            actualizarVista();
            return;
        }

        if (primeraSeleccionada == actual) {
            actual.setSeleccionada(false);
            primeraSeleccionada = null;
            actualizarVista();
            return;
        }

        boolean exito = juego.seleccionarPar(primeraSeleccionada, actual);

        primeraSeleccionada.setSeleccionada(false);
        actual.setSeleccionada(false);
        primeraSeleccionada = null;

        actualizarVista();

        if (juego.juegoTerminado()) {
            mostrarMensaje("Fin del juego", "No hay más movimientos posibles.");
            //salir del juego
        }
    }

    public void pedirPista() {
        Casilla[] pista = juego.darPista();
        actualizarVista();
        if (pista == null) {
            if (juego.getPistasRestantes() <= 0)
                mostrarMensaje("Pistas", "Ya no quedan pistas disponibles.");
            else
                mostrarMensaje("Pistas", "No se encontró una jugada válida.");
            return;
        }
        // Resaltar temporalmente las casillas de la pista (lo maneja TableroGUI)
        tableroGUI.resaltarPista(pista[0], pista[1]);
    }

    public void deshacer() {
        if (!juego.deshacer())
            mostrarMensaje("Deshacer", "No hay movimientos para deshacer.");
        actualizarVista();
    }

    public void actualizarVista() {
        juegoGUI.actualizarInfo(
                juego.getPuntos(),
                juego.getConcordanciasEncontradas(),
                juego.getConcordanciasPendientes(),
                juego.getPistasRestantes()
        );
        tableroGUI.actualizar();
    }

    private void mostrarMensaje(String titulo, String contenido) {
        javafx.scene.control.Alert alerta = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }
}