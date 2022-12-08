/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ACER
 */
public class DAO {
    public static Connection con;
    private final String jdbcUrl = "jdbc:mysql://localhost:3306/sss";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "nhan";
    
    public DAO() {
        if (con == null) {
            String dbUrl
                    = "jdbc:mysql://localhost:3306/hotel";
            String dbClass = "com.mysql.jdbc.Driver";
            try {
                Class.forName(dbClass);
                con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
