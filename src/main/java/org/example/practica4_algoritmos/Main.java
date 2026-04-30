package org.example.practica4_algoritmos;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.TextInputDialog;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        //creamos la imagen de fondo
        Image fondoImg = new Image(getClass().getResource("/img/fondo.jpeg").toExternalForm());
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
        // Dentro del evento del botón JUGAR:
        btnJugar.setOnAction(e -> {
            int filas = pedirNumero("Ingresa renglones (mínimo 4):", 4, 10);
            int columnas = pedirNumero("Ingresa columnas (mínimo 10):", 10, 12);
            Controlador controlador = new Controlador();
            controlador.iniciar(stage, filas, columnas);
        });
// Nota: pedirNumero debe ser un método auxiliar en Main o moverlo a Controlador.

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

    private int pedirNumero(String mensaje, int minimo, int maximo) {
        while (true) {
            TextInputDialog dialogo = new TextInputDialog();
            dialogo.setTitle("Configuración");
            dialogo.setHeaderText(null);
            dialogo.setContentText(mensaje + " Rango: " + minimo + " a " + maximo);

            java.util.Optional<String> resultado = dialogo.showAndWait();
            if (resultado.isEmpty()) {
                System.exit(0); // El usuario cerró el diálogo → salir del juego
            }

            try {
                int valor = Integer.parseInt(resultado.get());
                if (valor >= minimo && valor <= maximo) {
                    return valor;
                }
                // Mostrar error si está fuera de rango
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Dato inválido");
                alerta.setHeaderText(null);
                alerta.setContentText("Ingresa un valor entre " + minimo + " y " + maximo + ".");
                alerta.showAndWait();
            } catch (NumberFormatException e) {
                // Mostrar error si no es número
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Dato inválido");
                alerta.setHeaderText(null);
                alerta.setContentText("Debes escribir un número entero.");
                alerta.showAndWait();
            }
        }
    }

    //Ejecutamos la ventana
    public static void main(String[] args) {
        launch(args);
    }
}