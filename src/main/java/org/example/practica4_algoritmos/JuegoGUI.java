package org.example.practica4_algoritmos;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JuegoGUI {
    private Controlador controlador;
    private Label lblPuntos, lblEncontradas, lblPendientes, lblPistas;

    public JuegoGUI(Controlador controlador) {
        this.controlador = controlador;
    }

    public void mostrar(Stage stage, TableroGUI tableroGUI) {
        BorderPane raiz = new BorderPane();

        //agregamos el fondo del juego
        raiz.setStyle(
                "-fx-background-image: url('/fondoJUEGO.jpeg');" +
                        "-fx-background-size: cover;" +
                        "-fx-background-position: center center;"
        );

        //TEXTOS!!
        Label iconoTrofeo = new Label("🏆");
        iconoTrofeo.setStyle("-fx-font-size: 22px;");

        lblPuntos = new Label("0");
        lblPuntos.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        lblEncontradas = new Label("Encontradas: 0");
        lblEncontradas.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #4f5b62;");

        lblPendientes = new Label("Pendientes: 0");
        lblPendientes.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #4f5b62;");

        lblPistas = new Label("Pistas: 5");
        lblPistas.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #4f5b62;");


        //Acomodamos la informacion de los textos en diferentes espacios y guardamos todo
        //en un solo panel
        HBox filaPuntos = new HBox(6, iconoTrofeo, lblPuntos);
        filaPuntos.setAlignment(Pos.CENTER);

        HBox filaPanels = new HBox(6, lblEncontradas, lblPendientes, lblPistas);
        filaPuntos.setAlignment(Pos.CENTER);

        VBox panelInfo = new VBox(6, filaPuntos, filaPanels);
        panelInfo.setAlignment(Pos.CENTER);

        //Sirve para crear relleno y que sea vea mejor estructurada la zona
        Region relleno = new Region();
        relleno.setMinWidth(42);

        BorderPane panelSuperior = new BorderPane();
        panelSuperior.setCenter(panelInfo);
        panelSuperior.setRight(relleno);
        panelSuperior.setPadding(new Insets(0, 0, 15, 0));

        //lo centramos
        HBox wrapperTablero = new HBox(tableroGUI.getGridPane());
        wrapperTablero.setAlignment(Pos.CENTER);
        wrapperTablero.setStyle("-fx-background-color: transparent;");

        //Ingresamos el tablero en un scrollpane para que en caso sea un tablero muy grande
        //se visualice de forma correcta
        ScrollPane contenedorTablero = new ScrollPane(wrapperTablero);
        contenedorTablero.setFitToWidth(true);
        contenedorTablero.setPannable(true);
        contenedorTablero.setPrefViewportHeight(330);
        contenedorTablero.setMinViewportHeight(330);
        contenedorTablero.setMaxHeight(360);

        //agregamos estilo
        contenedorTablero.setStyle(
                "-fx-background: transparent;" +
                        "-fx-background-color: transparent;"
        );

        //BOTONES
        Button btnPista = new Button("Pista");
        btnPista.getStyleClass().add("boton-estilo");
        btnPista.setOnAction(e -> controlador.pedirPista());

        Button btnDeshacer = new Button("Deshacer");
        btnDeshacer.getStyleClass().add("boton-estilo");
        btnDeshacer.setOnAction(e -> controlador.deshacer());


        //Ordenamos los botones y textos
        HBox panelInferior = new HBox(30, btnPista, btnDeshacer);
        panelInferior.setAlignment(Pos.CENTER);
        panelInferior.setPadding(new Insets(15, 0, 10, 0));

        VBox centro = new VBox(15,contenedorTablero, panelInferior);
        centro.setAlignment(Pos.TOP_CENTER);

        raiz.setTop(panelSuperior);
        raiz.setCenter(centro);

        //Creamos la escena, guardamos la root e implementamos css
        Scene scene = new Scene(raiz, 760, 620);
        scene.getStylesheets().add("/estilo.css");
        stage.setTitle("Number Match");
        stage.setScene(scene);
        stage.setMinWidth(720);
        stage.setMinHeight(620);
        stage.show();
    }

    //Actualizamos la informacion de los textos dependiendo de si hubo modificaciones en el juego
    public void actualizarInfo(int puntos, int encontradas, int pendientes, int pistas) {
        lblPuntos.setText(String.valueOf(puntos));
        lblEncontradas.setText("Encontradas: " + encontradas);
        lblPendientes.setText("Pendientes: " + pendientes);
        lblPistas.setText("Pistas: " + pistas);
    }
}