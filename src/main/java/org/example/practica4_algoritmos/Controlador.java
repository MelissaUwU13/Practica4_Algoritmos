package org.example.practica4_algoritmos;

import javafx.stage.Stage;

public class Controlador {
    private Stage stage;

    public Controlador(Stage stage) {
        this.stage = stage;
    }

    public void iniciar() {
        JuegoGUI juegoGUI = new JuegoGUI();
        juegoGUI.iniciar(stage);
    }
}