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
public class Perfil {
    private int per_id;
    private String per_descripcion;
    private String per_estado ;

    public Perfil() {
    }

    public Perfil(int per_id, String per_descripcion, String per_estado) {
        this.per_id = per_id;
        this.per_descripcion = per_descripcion;
        this.per_estado = per_estado;
    }

    public int getPer_id() {
        return per_id;
    }

    public String getPer_descripcion() {
        return per_descripcion;
    }

    public String getPer_estado() {
        return per_estado;
    }

    @Override
    public String toString() {
        return per_descripcion ;
    }
    
     public ObservableList<Perfil> getListaPerfiles(String cadenaSQL){
        Modelos.mod_BD BD=new Modelos.mod_BD();
        ObservableList<Perfil> perfiles=BD.getlistaConsultar(cadenaSQL,
                rs->{
                    try {
                        return new Perfil(
                           rs.getInt("per_id"),
                           rs.getString("per_descripcion"),
                           rs.getString("per_estado")
                           ) ;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                });
        return perfiles;
    }
    
}
