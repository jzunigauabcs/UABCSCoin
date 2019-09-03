/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import DB.DBConnec;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author jzuniga
 */
public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String date;
    public static final String TABLE = "usuario";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public static Usuario findByLogin(String email, String password) throws SQLException, ClassNotFoundException {
        
        String query = "SELECT * from " + TABLE +" where email = ? and password = sha1(?)";
        Connection conn = DBConnec.getConnection();
        PreparedStatement  pstm = conn.prepareStatement(query);
        pstm.setString(1, email);
        pstm.setString(2, password);
        
        ResultSet rs = pstm.executeQuery();
        
        Usuario usuario = null;
        
        if(rs.next()) {
            usuario = new Usuario();
            usuario.setId(rs.getInt("id"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setApellido(rs.getString("apellido"));
            usuario.setEmail(rs.getString("email"));
        }
        return usuario;
    }
    
}
