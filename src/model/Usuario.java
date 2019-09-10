/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import DB.DBConnec;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    private Date date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = new Date(date.getTime());
    }
    
    public static Usuario findByLogin(String email, String password) {
        Usuario usuario = null;
        Connection conn = null;
        try {
            String query = "SELECT * from " + Usuario.TABLE +" WHERE email = ? AND password = sha1(?)";
            conn = DBConnec.getConnection();
            PreparedStatement  pstm = conn.prepareStatement(query);
            pstm.setString(1, email);
            pstm.setString(2, password);
            
            ResultSet rs = pstm.executeQuery();
            System.out.println(pstm);
            
            if(rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setEmail(rs.getString("email"));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(conn != null)
                try {
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return usuario;
    }
    
    public int create() {
        int created = 0;
        Connection conn = null;
        try {
            conn = DBConnec.getConnection();
            String query = "INSERT INTO " + TABLE +
                    "(nombre, apellido, email, password, fecha_nacimiento) " +
                    "VALUE(?, ?, ?, SHA1(?), ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, this.nombre);
            pst.setString(2, this.apellido);
            pst.setString(3, this.email);
            pst.setString(4, this.password);
            pst.setDate(5, this.date);
            
            return pst.executeUpdate();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return created;
    }
    
}
