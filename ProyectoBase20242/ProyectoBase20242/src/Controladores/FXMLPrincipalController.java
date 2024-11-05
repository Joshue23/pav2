/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Jaime
 */
public class FXMLPrincipalController implements Initializable {

    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuArchivo;
    @FXML
    private MenuItem menuItemPantalla1;
    @FXML
    private MenuItem menuItemPantalla2;
    @FXML
    private MenuItem menuItemCerrar;
    @FXML
    private Menu menuProceso;
    @FXML
    private Menu menuReportes;
    @FXML
    private VBox dataPane;
    @FXML
    private Button btn_patalla1;
    @FXML
    private Button btn_Pantalla2;
    @FXML
    private Label lbl_servidor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           this.lbl_servidor.setText(Modelos.mod_General.nombregestorBD);
        System.out.println("serv:"+Modelos.mod_General.nombregestorBD);
    }    

    @FXML
    private void acc_Pantalla1(ActionEvent event) throws IOException {
        String pantalla="/Vistas/FXMLPantalla1.fxml";
        setDataPane(fadeAnimate(pantalla));
     
        
    }

    @FXML
    private void acc_Pantalla2(ActionEvent event) throws IOException {
        String pantalla="/Vistas/FXMLPantalla2.fxml";
        setDataPane(fadeAnimate(pantalla));
    }

    @FXML
    private void acc_Cerrar(ActionEvent event) {
       Platform.exit();
    }
    
    
       public VBox fadeAnimate(String url) throws IOException{
        VBox v=FXMLLoader.load(getClass().getResource(url));
        FadeTransition ft=new FadeTransition(Duration.millis(1500));
        ft.setNode(v);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        return v;
    }
       
       public void setDataPane(Node node){
           dataPane.getChildren().setAll(node);
           dataPane.setPadding(new Insets(100,300,70,300));      
       }

    @FXML
    private void acc_btnPantalla1(ActionEvent event) throws IOException {
          String pantalla="/Vistas/FXMLPantalla1.fxml";
        setDataPane(fadeAnimate(pantalla));
    }

    @FXML
    private void acc_btnPantalla2(ActionEvent event) throws IOException {
          String pantalla="/Vistas/FXMLPantalla2.fxml";
        setDataPane(fadeAnimate(pantalla));
    }
    
    
}
