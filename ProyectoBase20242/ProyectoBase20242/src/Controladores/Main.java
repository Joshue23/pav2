/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Controladores;

import Modelos.mod_BD;
import java.awt.Button;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.image.Image;
/**
 *
 * @author Jaime
 */
public class Main extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

    @Override
    public void start(Stage primerstage) throws Exception {
        try {
            Modelos.mod_BD mod_BD=new Modelos.mod_BD();     
           if( mod_BD.conectarBD()){
               System.out.println("se conectó con la BD");
           }else{
                System.out.println("Error al conectar a la BD");
               return;
           }
                   
            //armar el boton que confirme si desea salir de la aplicacion
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirme la salida del sistema");
            alert.setContentText("Desea salir de la aplicación? ");
            Button closeButton=new Button("Cerrar la aplicación");
            Button addStage =new Button("Abrir la ventana");
            
            //iniciar la pantalla principal
           VBox mainPanel = 
                   (VBox) FXMLLoader.load(getClass().getResource("/Vistas/FXMLPrincipal.fxml"));
            Scene scene = new Scene(mainPanel);
            primerstage.setTitle("Mi Aplicación");
            primerstage.getIcons().add(new Image(getClass().getResourceAsStream("/logos/libreta-de-contactos.png")));
            primerstage.setScene(scene);
            primerstage.setMaximized(true);
            //modificar para cerrar la aplicación
                primerstage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    event.consume();           //Consumar el evento
                    alert.showAndWait();
                    if (alert.getResult().equals(ButtonType.OK)) {
                        primerstage.close();
                        Platform.exit();
                    } else {
                        System.out.println("cancel");
                    }

                }
            });
            //fin de la modificación
            //
            // Ajustar el tamaño de la ventana al tamaño de la pantalla
               // Obtener las dimensiones de la pantalla
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();

        // Crear la escena
        //Scene scene = new Scene(dataPane, bounds.getWidth() * 0.5, bounds.getHeight() * 0.5); // 50% de la pantalla

        primerstage.setScene(scene);
        primerstage.setTitle("Formulario Responsivo");
        primerstage.setX(bounds.getMinX());
        primerstage.setY(bounds.getMinY());
        primerstage.setWidth(bounds.getWidth());
        primerstage.setHeight(bounds.getHeight());
            //
            primerstage.show();
            
        } catch (Exception e) {
            System.out.println(""+e.getMessage());
             
        }
    }
    
}
