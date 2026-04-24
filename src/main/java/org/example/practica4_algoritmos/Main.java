package org.example.practica4_algoritmos;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        //creamos la imagen de fondo
        Image fondoImg = new Image(getClass().getResource("/img/fondo.png").toExternalForm());
        ImageView fondo = new ImageView(fondoImg);
        fondo.setFitWidth(1024);
        fondo.setFitHeight(500);

        //Botones
        Button btnJugar = new Button("JUGAR");
        Button btnSalir = new Button("SALIR");

        btnJugar.getStyleClass().add("boton-jugar");
        btnSalir.getStyleClass().add("boton-salir");

        //Este boton cierra la ventana y se acaba el juego
        btnSalir.setOnAction(e -> stage.close());

        //Este boton nos manda a otro Stage de la clase Juego y cambia de ventana
        btnJugar.setOnAction(e -> {
            Controlador controlador = new Controlador(stage);
            controlador.iniciar();
        });

        //guardamos los botones en un hbox y lo centramos
        HBox menu = new HBox(20, btnJugar, btnSalir);
        menu.setAlignment(Pos.BOTTOM_CENTER);

        //guardamos el fondo y los botones en un stackpane
        StackPane root = new StackPane(fondo,menu);

        //Creamos la escena, guardamos la root e implementamos css
        Scene scene = new Scene(root, 1024, 500);
        scene.getStylesheets().add("/estilo.css");

        //mostramos la escena
        stage.setTitle("Menu Inicial");
        stage.setScene(scene);
        stage.show();
    }

    //Ejecutamos la ventana
    public static void main(String[] args) {
        launch(args);
    }
}