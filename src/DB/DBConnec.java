/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author jzuniga
 */
public class DBConnec {
    private static final String DB_NAME= "UABCSCOIN?autoRecconect=true&useSSL=false";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    private static final String BD_USER = "root";
    private static final String DB_PASSWORD = "root";
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
           Connection connection = null;
           Class.forName(DRIVER_NAME);
           connection = DriverManager.getConnection(URL, BD_USER, DB_PASSWORD);
           return connection;
    }
}
