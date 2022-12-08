/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server.DAO;

import Server.model.Asset;
import Server.model.Room;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class RoomDAO extends DAO{
    
    Statement st;
    
    public ArrayList<Room> getListRoom() {
        String sql = "SELECT * FROM room ";
        ArrayList<Room> rooms = new ArrayList<>();
        try{
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Room room = new Room(rs.getInt("id"), rs.getString("name"), rs.getString("description"));
                rooms.add(room);
            }
        }catch (SQLException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("lỗi validate");
        }
        return rooms;
    }
    
    public void addRoom(Room r) {
        String sql = "INSERT INTO `room`(`name`, `description`) "
                    + "VALUES ('" + r.getName()+ "','" + r.getDescription() + "')";
        
        try{
            st = con.createStatement();
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateRoom(Room r) {
        String sql = "UPDATE `room`SET name='" + r.getName() + "',description='" + r.getDescription()  + "'WHERE id='" + r.getId() + "'";
        try {
            st = con.createStatement();
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void DeleteRoom(Room r) {
        String sql = "DELETE FROM `room` WHERE id='" + r.getId() + "'";
        try {
            st = con.createStatement();
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Room> getListSearchById(Room r) {
        String sql = "SELECT * FROM `room` WHERE id='" + r.getId() + "'";
        ArrayList<Room> rooms = new ArrayList<>();
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Room room = new Room(rs.getInt("id"), rs.getString("name"), rs.getString("description"));
                rooms.add(room);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rooms;
    }
    
    public ArrayList<Room> getListSearchByName(Room r) {
        String sql = "SELECT * FROM `room` WHERE name='" + r.getName() + "'";
        ArrayList<Room> rooms = new ArrayList<>();
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Room room = new Room(rs.getInt("id"), rs.getString("name"), rs.getString("description"));
                rooms.add(room);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rooms;
    }
    
}
