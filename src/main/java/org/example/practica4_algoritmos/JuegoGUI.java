package org.example.practica4_algoritmos;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        //creamos la imagen de fondo
        Image fondoImg = new Image(getClass().getResource("/img/fondoJUEGO.jpeg").toExternalForm());
        ImageView fondo = new ImageView(fondoImg);
        fondo.setFitWidth(1024);
        fondo.setFitHeight(500);

        BorderPane raiz = new BorderPane();
        raiz.setStyle("-fx-background-color: white;");
        raiz.setPadding(new Insets(15));

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

        HBox filaPuntos = new HBox(6, iconoTrofeo, lblPuntos);
        filaPuntos.setAlignment(Pos.CENTER);

        VBox panelInfo = new VBox(6, filaPuntos, lblEncontradas, lblPendientes, lblPistas);
        panelInfo.setAlignment(Pos.CENTER);

        Region relleno = new Region();
        relleno.setMinWidth(42);
        BorderPane panelSuperior = new BorderPane();
        panelSuperior.setCenter(panelInfo);
        panelSuperior.setRight(relleno);
        panelSuperior.setPadding(new Insets(0, 0, 15, 0));

        ScrollPane contenedorTablero = new ScrollPane(tableroGUI.getGridPane());
        contenedorTablero.setFitToWidth(true);
        contenedorTablero.setPannable(true);
        contenedorTablero.setPrefViewportHeight(330);
        contenedorTablero.setMinViewportHeight(330);
        contenedorTablero.setMaxHeight(360);

        Button btnPista = new Button("💡");
        btnPista.setStyle("-fx-background-color: #4f5b62; -fx-text-fill: white; -fx-font-size: 22px; -fx-background-radius: 50%; -fx-min-width: 62px; -fx-min-height: 62px; -fx-max-width: 62px; -fx-max-height: 62px;");
        btnPista.setOnAction(e -> controlador.pedirPista());

        Button btnDeshacer = new Button("↶");
        btnDeshacer.setStyle("-fx-background-color: #ff9800; -fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: bold; -fx-background-radius: 50%; -fx-min-width: 62px; -fx-min-height: 62px; -fx-max-width: 62px; -fx-max-height: 62px;");
        btnDeshacer.setOnAction(e -> controlador.deshacer());

        HBox panelInferior = new HBox(30, btnPista, btnDeshacer);
        panelInferior.setAlignment(Pos.CENTER);
        panelInferior.setPadding(new Insets(15, 0, 10, 0));

        VBox centro = new VBox(15,contenedorTablero, panelInferior);
        centro.setAlignment(Pos.TOP_CENTER);

        raiz.setTop(panelSuperior);
        raiz.setCenter(centro);

        Scene escena = new Scene(raiz, 760, 620);
        stage.setTitle("Number Match");
        stage.setScene(escena);
        stage.setMinWidth(720);
        stage.setMinHeight(620);
        stage.show();
    }

    public void actualizarInfo(int puntos, int encontradas, int pendientes, int pistas) {
        lblPuntos.setText(String.valueOf(puntos));
        lblEncontradas.setText("Encontradas: " + encontradas);
        lblPendientes.setText("Pendientes: " + pendientes);
        lblPistas.setText("Pistas: " + pistas);
    }
}