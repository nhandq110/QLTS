/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server.DAO;

import static Server.DAO.DAO.con;
import Server.model.Asset;
import Server.model.RoomAsset;
import Server.model.RoomAssetDTO;
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
public class RoomAssetDAO extends DAO{
    
    Statement st;
    
    public ArrayList<RoomAsset> getListRoomAsset() {

        String sql = "SELECT * FROM room_asset ";
        ArrayList<RoomAsset> roomAssets = new ArrayList<>();
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                RoomAsset ra = new RoomAsset(rs.getInt("id"),rs.getInt("id_room"),rs.getString("name_Room"),rs.getInt("id_asset"),rs.getString("name_asset"),rs.getString("type_asset"),rs.getInt("price_asset"));
                
                roomAssets.add(ra);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("lỗi validate");
        }
        return roomAssets;
    }
    
    public void addRoomAsset(RoomAssetDTO ra) {
        String sql = "INSERT INTO `room_asset`(`id_room`, `name_room`, `id_asset`, `name_asset`, `type_asset`, `price_asset`) "
                + "VALUES ('" + ra.getIdRoom() + "','" + ra.getNameRoom() + "','" + ra.getIdAsset() + "','" + ra.getNameAsset()+ "','" + ra.getTypeAsset()+ "','" + ra.getPriceAsset() + "')";

        try {
            st = con.createStatement();
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<RoomAsset> getListSearchByIdRoom(RoomAssetDTO ra) {
        String sql = "SELECT id_asset FROM `room_asset` WHERE id_room='" + ra.getIdRoom() + "'";
        ArrayList<RoomAsset> roomAssetsList = new ArrayList<>();
        
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                RoomAsset roomAssets = new RoomAsset();
                roomAssets.setIdAsset(rs.getInt("id_asset"));
                roomAssetsList.add(roomAssets);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return roomAssetsList;
    }
    
    public ArrayList<RoomAsset> getListSearchByNameRoom(RoomAssetDTO ra) {
        String sql = "SELECT id_asset FROM `room_asset` WHERE name_room='" + ra.getNameRoom() + "'";
        ArrayList<RoomAsset> roomAssetsList = new ArrayList<>();
        
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                RoomAsset roomAssets = new RoomAsset();
                roomAssets.setIdAsset(rs.getInt("id_asset"));
                roomAssetsList.add(roomAssets);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return roomAssetsList;
    }
    
}
