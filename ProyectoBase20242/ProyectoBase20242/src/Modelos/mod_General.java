/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.awt.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Jaime
 */
public class mod_General {

    public static int gestorBD = 2;//1:MYSQL -- 2:SQLSERVER 
    public static String nombregestorBD = "";
    public boolean botonConfirmacion(String mensaje) {
        //armar el boton que confirme si desea salir de la aplicacion
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Mensaje del Sistema");
        alert.setContentText(mensaje);
        Button closeButton = new Button("Cancelar");
        Button addStage = new Button("Aceptar");
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.OK)) {
            return true;
        } else {
            return false;
        }
    }

    public void fun_mensajeInformacion(String mensaje) {
        Alert alertInfo = new Alert(Alert.AlertType.CONFIRMATION);
        alertInfo.setHeaderText("");
        alertInfo.setTitle("System Message");
        alertInfo.setContentText(mensaje);
        alertInfo.showAndWait();
    }

    public void fun_mensajeInformacionError(String mensaje) {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setHeaderText("");
        alertError.setTitle("System Message");
        alertError.setContentText(mensaje);
        alertError.showAndWait();
    }

}
