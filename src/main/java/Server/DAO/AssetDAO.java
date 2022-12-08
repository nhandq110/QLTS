/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server.DAO;

import Server.model.Asset;
import Server.model.Room;
import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class AssetDAO extends DAO{
    
    Statement st;
    
    public ArrayList<Asset> getListAsset() {
        
        String sql = "SELECT * FROM asset ";
        ArrayList<Asset> assets = new ArrayList<>();
        try{
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Asset asset = new Asset(rs.getInt("id"), rs.getString("name"), rs.getString("type"), rs.getString("current_location"), rs.getInt("price"));
                assets.add(asset);
            }
        }catch (SQLException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("lỗi validate");
        } 
        return assets;
    }
    
    public void addAsset(Asset a) {
        String sql = "INSERT INTO `asset`(`name`, `type`, `current_location`, `price`) "
                    + "VALUES ('" + a.getName() + "','" + a.getType() + "','" + a.getCurrentLocation() + "','" + a.getPrice() + "')";
        
        try{
            st = con.createStatement();
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateAsset(Asset a) {
        
        String sql = "UPDATE `asset`SET name='" + a.getName() + "',type='" + a.getType() + "',current_location='" + a.getCurrentLocation() + "',price='" + a.getPrice() + "'WHERE id='" + a.getId() + "'";
        try {
            st = con.createStatement();
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void DeleteAsset(Asset a) {
        
        String sql = "DELETE FROM `asset` WHERE id='" + a.getId() + "'";
        try {
            st = con.createStatement();
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public ArrayList<Asset> getListSearchById(Asset a) {
        String sql = "SELECT * FROM `asset` WHERE id='" + a.getId() + "'";
        ArrayList<Asset> assets = new ArrayList<>();
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Asset asset = new Asset(rs.getInt("id"), rs.getString("name"), rs.getString("type"), rs.getString("current_location"), rs.getInt("price"));
                assets.add(asset);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return assets;
    }
    
    public ArrayList<Asset> getListSearchByName(Asset a) {
        String sql = "SELECT * FROM `asset` WHERE name='" + a.getName() + "'";
        ArrayList<Asset> assets = new ArrayList<>();
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Asset asset = new Asset(rs.getInt("id"), rs.getString("name"), rs.getString("type"), rs.getString("current_location"), rs.getInt("price"));
                assets.add(asset);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return assets;
    }
    
    public ArrayList<Asset> getListSearchByType(Asset a) {
        String sql = "SELECT * FROM `asset` WHERE type='" + a.getType() + "'";
        ArrayList<Asset> assets = new ArrayList<>();
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Asset asset = new Asset(rs.getInt("id"), rs.getString("name"), rs.getString("type"), rs.getString("current_location"), rs.getInt("price"));
                assets.add(asset);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return assets;
    }
    
    public ArrayList<Asset> getListSearchByCurrentLocation(Asset a) {
        String sql = "SELECT * FROM `asset` WHERE current_location='" + a.getCurrentLocation() + "'";
        ArrayList<Asset> assets = new ArrayList<>();
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Asset asset = new Asset(rs.getInt("id"), rs.getString("name"), rs.getString("type"), rs.getString("current_location"), rs.getInt("price"));
                assets.add(asset);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return assets;
    }
    
    public ArrayList<Asset> getListSearchByPrice(Asset a) {
        String sql = "SELECT * FROM `asset` WHERE price='" + a.getPrice() + "'";
        ArrayList<Asset> assets = new ArrayList<>();
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Asset asset = new Asset(rs.getInt("id"), rs.getString("name"), rs.getString("type"), rs.getString("current_location"), rs.getInt("price"));
                assets.add(asset);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return assets;
    }
    
}
