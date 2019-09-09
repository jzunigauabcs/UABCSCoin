/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import DB.ConnectionManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private Date fechaNacimiento;
    private static final String TABLE = "usuario";

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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(java.util.Date fechaNacimiento) {
        this.fechaNacimiento = new Date(fechaNacimiento.getTime());
    }
    
    public static Usuario findByLogin(String email, String password) {
        Usuario usuario = null;
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            String query = "SELECT * FROM " + TABLE + 
                    " WHERE email = ? " +
                    "AND PASSWORD = sha1(?)";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, email);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setEmail("jzuniga@uabcs.mx");
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }
    
    public boolean create() {
        boolean created = false;
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            String query = "INSERT INTO " + TABLE +
                    "(nombre, apellido, email, password, fecha_nacimiento) " +
                    "VALUES(?, ?, ?, sha1(?), ?)";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, this.nombre);
            pstm.setString(2, this.apellido);
            pstm.setString(3, this.email);
            pstm.setString(4, this.password);
            pstm.setDate(5, this.getFechaNacimiento());
            int row = pstm.executeUpdate();
            created = row == 1;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return created;
    }
}
