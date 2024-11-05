/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controladores;


import Modelos.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jaime
 */
public class FXMLPantalla1Controller implements Initializable {

    @FXML
    private TableView<Usuario> tbv_usuarios;
    @FXML
    private Button btn_nuevo;
    @FXML
    private Button btn_cancelar;
    @FXML
    private TableColumn<Usuario, Integer> col_id;
    @FXML
    private TableColumn<Usuario, String> col_nombre;
    @FXML
    private TableColumn<Usuario, String> col_usuario;
    @FXML
    private TableColumn<Usuario, Integer> col_perfil;
    @FXML
    private TableColumn<Usuario, String> col_estado;
    @FXML
    private VBox vboxUsuarios;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     //iniciar el tbv_usuarios
     col_id.setCellValueFactory(new PropertyValueFactory<>("usr_id"));
     col_nombre.setCellValueFactory(new PropertyValueFactory<>("usr_nombreCompleto"));
     col_usuario.setCellValueFactory(new PropertyValueFactory<>("usr_usuario"));
     col_perfil.setCellValueFactory(new PropertyValueFactory<>("per_id"));
     col_estado.setCellValueFactory(new PropertyValueFactory<>("usr_estado"));
     
     this.fun_cargarUsuarios();
    }

    @FXML
    private void acc_nuevo(ActionEvent event) {
        try {
            this.fun_abrirModal(0);
        } catch (Exception e) {
        }
    }

    @FXML
    private void acc_cancelar(ActionEvent event) {
     vboxUsuarios.setVisible(false);
    }

   
    @FXML
    private void eventClicked(MouseEvent event) {
        try {
            if(event.getButton().equals(MouseButton.PRIMARY)&& event.getClickCount()==2){
               // System.out.println("Doble click");
               Usuario objSeleccionado=this.tbv_usuarios.getSelectionModel().getSelectedItem();
                //System.out.println(objSeleccionado.getUsr_id());
                if(objSeleccionado!=null){
                    this.fun_abrirModal(objSeleccionado.getUsr_id());
                }
                
            }
        } catch (Exception e) {
        }
        
    }//fin funcion
    
    
 public void fun_abrirModal(int miID){
    try {
        // Carga el archivo FXML de la nueva ventana
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/FXMLUsuario.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
       FXMLUsuarioController controlador = loader.getController();
       controlador.fun_recuperarUsuarioxID(miID);

        // Crea un nuevo Stage (ventana)
        Stage newStage = new Stage();
        newStage.getIcons().add(new Image("/logos/usuario.png"));
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setResizable(false);
        newStage.setScene(scene);
        
        // Muestra la nueva ventana
        newStage.showAndWait();     
        //retorna al cerrar el modal
        
    } catch (Exception e) {
        e.printStackTrace();
    }
 
    }
    //fun_cargarUsuarios()
    public void fun_cargarUsuarios(){
        try {
            String cadenaSQL="select usr_id, usr_nombreCompleto,usr_usuario,usr_clave,usr_estado,per_id from usuario where usr_estado='A'";
            Usuario objUsr=new Usuario();
            ObservableList<Usuario> ObsList=objUsr.getListaUsuarios(cadenaSQL);
            this.tbv_usuarios.setItems(ObsList);
        } catch (Exception e) {
        }
        
    }
     //fin fun_cargarUsuarios()
}//fin clase
