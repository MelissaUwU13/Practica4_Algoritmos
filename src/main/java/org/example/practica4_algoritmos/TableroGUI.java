package org.example.practica4_algoritmos;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class TableroGUI {
    private Controlador controlador;
    private Tablero tablero;
    private GridPane gridPane;
    private Button[][] botones;

    //Constructor
    public TableroGUI(Controlador controlador, Tablero tablero) {
        this.controlador = controlador;
        this.tablero = tablero;
        this.gridPane = new GridPane();
        this.botones = new Button[tablero.getFilas()][tablero.getColumnas()];
        construir();
    }

    //Metodo para construir la base del tablero a tra ves de dos fors y botones
    private void construir() {
        gridPane.setHgap(0);
        gridPane.setVgap(0);
        for (int f = 0; f < tablero.getFilas(); f++) {
            for (int c = 0; c < tablero.getColumnas(); c++) {
                Button btn = new Button();
                btn.setPrefSize(56, 56);
                btn.setMinSize(56, 56);
                btn.setMaxSize(56, 56);
                final int fila = f, col = c;
                btn.setOnAction(e -> controlador.manejarSeleccion(fila, col));
                botones[f][c] = btn;
                gridPane.add(btn, c, f);
            }
        }
        actualizar();
    }

    //Actualizamos el diseño de los colores dependiendo de si son seleccionadas, o si ya fueron encontradas
    public void actualizar() {
        for (int f = 0; f < tablero.getFilas(); f++) {
            for (int c = 0; c < tablero.getColumnas(); c++) {
                Casilla cas = tablero.getCasilla(f, c);
                Button btn = botones[f][c];
                //si esta activa es porque aun no fue eliminada
                if (cas.isActiva()) {
                    btn.setDisable(false);
                    btn.setText(String.valueOf(cas.getValor()));
                    if (cas.isSeleccionada())
                        btn.setStyle("-fx-background-color: #42a5f5; -fx-border-color: #ebebeb; -fx-border-width: 1; -fx-font-size: 20px; -fx-text-fill: white;");
                    else
                        btn.setStyle("-fx-background-color: white; -fx-border-color: #ebebeb; -fx-border-width: 1; -fx-font-size: 20px; -fx-text-fill: black;");
                }
                //si esta desactiva es porque ya encontraron su par
                else {
                    btn.setDisable(true);
                    btn.setText(cas.getValor() == 0 ? "" : String.valueOf(cas.getValor()));
                    btn.setStyle("-fx-background-color: white; -fx-border-color: #ebebeb; -fx-border-width: 1; -fx-font-size: 20px; -fx-text-fill: #d9d9d9;");
                }
            }
        }
    }

    //Metodo que cambia el color de las casillas para resaltar la pista
    public void resaltarPista(Casilla a, Casilla b) {
        Button btnA = botones[a.getFila()][a.getColumna()];
        Button btnB = botones[b.getFila()][b.getColumna()];

        btnA.setStyle("-fx-background-color: #4c64e4; -fx-border-color: #ebebeb; -fx-border-width: 1; -fx-font-size: 20px; -fx-text-fill: black;");
        btnB.setStyle("-fx-background-color: #4c64e4; -fx-border-color: #ebebeb; -fx-border-width: 1; -fx-font-size: 20px; -fx-text-fill: black;");
    }

    public GridPane getGridPane() { return gridPane; }
}