/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controladores;


import Modelos.Perfil;
import Modelos.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Jaime
 */
public class FXMLUsuarioController implements Initializable {

    @FXML
    private TextField txt_id;
    @FXML
    private TextField txt_usuario;
    @FXML
    private TextField txt_nombres;
    @FXML
    private TextField txt_clave;
    @FXML
    private ComboBox<Perfil> cbo_perfil;
    @FXML
    private Button btn_grabar;
    @FXML
    private Button btn_cancellar;
    @FXML
    private CheckBox chk_estado;

   Modelos.mod_General objG=new Modelos.mod_General();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.chk_estado.setSelected(true);
        this.txt_id.setDisable(true);
        this.cargarPerfiles();
    }

    @FXML
    private void acc_grabar(ActionEvent event) {
        try {
            boolean confirmacion=objG.botonConfirmacion("Está seguro de Grabar?");
            if(confirmacion){
               // System.out.println("Grabar");
               if(fun_validar()){
                   //proceso grabar 
                   if(fun_Grabar()){
                       objG.fun_mensajeInformacion("se registro con exito");
                   }else{
                       objG.fun_mensajeInformacion("Error al grabar el usuario");
                   }
               }
                
            }
        } catch (Exception e) {
        }
    }

    @FXML
    private void acc_cancelar(ActionEvent event) {
        this.cerrarFormulario();
    }

    public void cerrarFormulario() {
        try {
            Stage stage = (Stage) this.btn_cancellar.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
        }
    }

    
//cargarPerfiles()
   public void  cargarPerfiles(){
       try {
           String cadanaSql="select per_id,per_descripcion,per_estado from perfiles where per_estado='A'";
           Perfil objP=new Perfil();
           this.cbo_perfil.getItems().addAll(objP.getListaPerfiles(cadanaSql));
       } catch (Exception e) {
       }
        
    }
//fin cargarPerfiles()
   
   public void fun_recuperarUsuarioxID(int id){
       try {
           if(id==0){
               return ;
           }
           //recuperar el usuario x id
           String cadenaSql="select usr_id, usr_nombreCompleto,usr_usuario,usr_clave,usr_estado,per_id from usuario where usr_id="+id;
           Usuario objUsr=new Usuario();
           ObservableList<Usuario> usuarios=objUsr.getListaUsuarios(cadenaSql);
           if(usuarios.size()>=0){
               this.txt_id.setText(Integer.toString(usuarios.get(0).getUsr_id()));
               this.txt_nombres.setText(usuarios.get(0).getUsr_nombreCompleto());
                this.txt_usuario.setText(usuarios.get(0).getUsr_usuario());
                 this.txt_clave.setText(usuarios.get(0).getUsr_clave());
                 //falta recuperar el perfil que tiene asignado
                 fun_seleccionarPerfilId(usuarios.get(0).getPer_id());
                 //para recuperar el estado
                 if(usuarios.get(0).getUsr_estado().equals("A")){
                     this.chk_estado.setSelected(true);
                 }else{
                      this.chk_estado.setSelected(false);
                 }
           }
       } catch (Exception e) {
       }
   }
    
   //inicio fun_seleccionarPerfilId();
  public void fun_seleccionarPerfilId(int id ){
      for(Perfil perfil:cbo_perfil.getItems() ){
          if(perfil.getPer_id()==id){
              cbo_perfil.setValue(perfil);
              break;
          }
      }
  }
   //fin  fun_seleccionarPerfilId()
//fun validar
  public boolean fun_validar(){
      return true; 
  }
  
//fun fin_validar 
  
  
//fun grabar  
  public boolean fun_Grabar(){
      return true;
  }
//fun fin grabar  
  
}//fin clase
