/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import javafx.collections.ObservableList;

/**
 *
 * @author Jaime
 */
public class Usuario {
   private int usr_id;
   private String usr_nombreCompleto;
   private String usr_usuario;
   private String usr_clave;
   private String usr_estado;
   private int per_id;

    public Usuario() {
    }

    public Usuario(int usr_id, String usr_nombreCompleto, String usr_usuario, String usr_clave, String usr_estado, int per_id) {
        this.usr_id = usr_id;
        this.usr_nombreCompleto = usr_nombreCompleto;
        this.usr_usuario = usr_usuario;
        this.usr_clave = usr_clave;
        this.usr_estado = usr_estado;
        this.per_id = per_id;
    }

    public int getUsr_id() {
        return usr_id;
    }

    public String getUsr_nombreCompleto() {
        return usr_nombreCompleto;
    }

    public String getUsr_usuario() {
        return usr_usuario;
    }

    public String getUsr_clave() {
        return usr_clave;
    }

    public String getUsr_estado() {
        return usr_estado;
    }

    public int getPer_id() {
        return per_id;
    }
   
   
    public ObservableList<Usuario> getListaUsuarios(String cadenaSQL){
        Modelos.mod_BD BD=new Modelos.mod_BD();
        ObservableList<Usuario> usuarios=BD.getlistaConsultar(cadenaSQL,
                rs->{
                    try {
                        return new Usuario(
                           rs.getInt("usr_id"),
                           rs.getString("usr_nombreCompleto"),
                           rs.getString("usr_usuario"),
                           rs.getString("usr_clave"),
                            rs.getString("usr_estado"),
                            rs.getInt("per_id")
                   ) ;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                });
        return usuarios;
    }
}
