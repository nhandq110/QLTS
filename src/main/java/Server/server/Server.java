/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server.server;

import Server.DAO.AssetDAO;
import Server.DAO.RoomAssetDAO;
import Server.DAO.RoomDAO;
import Server.model.Asset;
import Server.model.AssetDTO;
import Server.model.Room;
import Server.model.RoomAsset;
import Server.model.RoomAssetDTO;
import Server.model.RoomDTO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class Server {
    
    private int port;
    
    private DatagramSocket myServer,myServerAsset,myServerRoom;
    private DatagramPacket receivePacket;
    
    private AssetDAO adao;
    private RoomDAO rdao;
    private RoomAssetDAO radao;
    
    ArrayList<Asset> assetList;
    ArrayList<Room> roomList;
    ArrayList<RoomAsset> roomAssetList;
    
    ArrayList<AssetDTO> assetDTOList;
    ArrayList<RoomDTO> roomDTOList;
    ArrayList<RoomAsset> roomAssetDTOList;
    
    AssetDAO assetDao;
    RoomDAO roomDao;
    RoomAssetDAO raDao;

    public Server() {
        port = 1234;
        
        openConnection();
        System.out.println("Server is running");
        
        AssetDTO aDTO = new AssetDTO();
        RoomDTO rDTO = new RoomDTO();
        
        assetDao = new AssetDAO();
        roomDao = new RoomDAO();
        
        while(true){
            
              receive();
        }
        
    }
    
    
   
    public void showMessage(String Message){
        System.out.println(Message);
    }

    public void openConnection() {
        try{
            myServer = new DatagramSocket(port);
            
            
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void receive(){
        AssetDTO assetDTO = null;
        RoomDTO roomDTO = null;
        RoomAssetDTO roomAssetDTO = null;
        Object o = null;
        try {
            
            byte[] data = new byte[1024];
            receivePacket = new DatagramPacket(data, data.length);
            myServer.receive(receivePacket);
            ByteArrayInputStream bais = new ByteArrayInputStream(receivePacket.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            o = ois.readObject();
            
            try{
                assetDTO =  (AssetDTO) o;
                executeAsset(assetDTO);
            }catch (Exception e) {
                try {
                    roomDTO = (RoomDTO) o;
                    executeRoom(roomDTO);
                } catch (Exception a) {
                    roomAssetDTO = (RoomAssetDTO) o;
                    executeRoomAsset(roomAssetDTO);
                }
        }

        } catch (Exception e) {
          //  e.printStackTrace();
        }
        
        
        
    }
    

    
    
    public void fetchAsset(){
        assetDao = new AssetDAO();
        
        
        assetList =  assetDao.getListAsset();
        
        
        sendAssetListFor1234(assetList);
        
    }
    
    public void fetchRoom(){
        
        roomDao = new RoomDAO();
        
        
        roomList = roomDao.getListRoom();
        
        
        sendRoomListFor1234(roomList);
    }
    
    public void fetchRoomAsset(){
        raDao = new RoomAssetDAO();
        
        roomAssetList = raDao.getListRoomAsset();
        
        sendRoomAssetListFor1234(roomAssetList);
    }
    
    public void sendAssetListFor1234(ArrayList<Asset> assetList){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(assetList);
            byte[] data = baos.toByteArray();
            DatagramPacket dp = new DatagramPacket(data, data.length,receivePacket.getAddress(),receivePacket.getPort());
            myServer.send(dp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void sendRoomListFor1234(ArrayList<Room> roomList){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(roomList);
            byte[] data = baos.toByteArray();
            DatagramPacket dp = new DatagramPacket(data, data.length,receivePacket.getAddress(),receivePacket.getPort());
            myServer.send(dp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void sendRoomAssetListFor1234(ArrayList<RoomAsset> roomAssetList) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(roomAssetList);
            byte[] data = baos.toByteArray();
            DatagramPacket dp = new DatagramPacket(data, data.length, receivePacket.getAddress(), receivePacket.getPort());
            myServer.send(dp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void executeAsset(AssetDTO a) {
        int i = a.getStatus();
        switch(i){
            case 1:
                fetchAsset();
                System.out.println("Update list tai san");
                break;
            case 2:
                Asset a2 = new Asset();

                a2.setName(a.getName());
                a2.setType(a.getType());
                a2.setCurrentLocation(a.getCurrentLocation());
                a2.setPrice(a.getPrice());

                assetDao.addAsset(a2);

                System.out.println("Da nhan va thanh tai san");
                break;
            case 3:
                Asset a3 = new Asset();
                
                a3.setId(a.getId());
                a3.setName(a.getName());
                a3.setType(a.getType());
                a3.setCurrentLocation(a.getCurrentLocation());
                a3.setPrice(a.getPrice());
                
                assetDao.updateAsset(a3);
                System.out.println("Sua tai san");
                break;
            case 4:
                Asset a4 = new Asset();
                a4.setId(a.getId());
                assetDao.DeleteAsset(a4);
                System.out.println("Xoa tai san");
                break;
                
            case 5 : //search by id
                Asset a5 = new Asset();
                a5.setId(a.getId());
                assetList = new ArrayList<>();
                assetList = assetDao.getListSearchById(a5);
                
                sendAssetListFor1234(assetList);
                System.out.println("Tim kiem tai san bang id");
                break;
                
            case 6 : //search by name
                Asset a6 = new Asset();
                a6.setName(a.getName());
                assetList = new ArrayList<>();
                assetList = assetDao.getListSearchByName(a6);
                
                sendAssetListFor1234(assetList);
                System.out.println("Tim kiem tai san bang ten");
                break;
            case 7 : //search by type
                Asset a7 = new Asset();
                a7.setType(a.getType());
                assetList = new ArrayList<>();
                assetList = assetDao.getListSearchByType(a7);

                sendAssetListFor1234(assetList);
                System.out.println("Tim kiem tai san bang loai");
                break;
            case 8 : //search by currentLocation
                Asset a8 = new Asset();
                a8.setCurrentLocation(a.getCurrentLocation());
                assetList = new ArrayList<>();
                assetList = assetDao.getListSearchByCurrentLocation(a8);

                sendAssetListFor1234(assetList);
                System.out.println("Tim kiem tai san bang vi tri");
               break;
            case 9 : //search by price
                Asset a9 = new Asset();
                a9.setPrice(a.getPrice());
                assetList = new ArrayList<>();
                assetList = assetDao.getListSearchByPrice(a9);

                sendAssetListFor1234(assetList);
                System.out.println("Tim kiem tai san bang gia");
               break;
        }
    }

    private void executeRoom(RoomDTO r) {
        int i = r.getStatus();
        switch(i){
            case 1:
                fetchRoom();
                System.out.println("Update list phong");
                break;
            case 2:
                Room r2 = new Room();

                r2.setName(r.getName());
                r2.setDescription(r.getDescription());

                roomDao.addRoom(r2);

                System.out.println("Da nhan room va add");
                break;
            case 3:
                Room r3 = new Room();
                
                r3.setId(r.getId());
                r3.setName(r.getName());
                r3.setDescription(r.getDescription());
                
                roomDao.updateRoom(r3);
                System.out.println("Da update room ");
                break;
            case 4:
                Room r4 = new Room();
                r4.setId(r.getId());
                roomDao.DeleteRoom(r4);
                System.out.println("xoa phong");
                break;
            case 5 : //search by id
                Room r5 = new Room();
                r5.setId(r.getId());
                roomList = new ArrayList<>();
                roomList = roomDao.getListSearchById(r5);
                
                sendRoomListFor1234(roomList);
                System.out.println("Tim kiem phong bang id");
                break;
            case 6 : //search by name
                Room r6 = new Room();
                r6.setName(r.getName());
                roomList = new ArrayList<>();
                roomList = roomDao.getListSearchByName(r6);
                
                sendRoomListFor1234(roomList);
                System.out.println("Tim kiem tai san bang ten");
                break;
            
        }
    }

    private void executeRoomAsset(RoomAssetDTO ra) {
        int i = ra.getStatus();
        switch(i){
            case 1 :
                fetchRoomAsset();
                System.out.println("update list phong tai san");
                break;
            case 2 :
                raDao.addRoomAsset(ra);
                System.out.println("Them tai san vao phong");
                break;
                
            case 3 : 
                roomAssetList = new ArrayList<>();
                roomAssetList = raDao.getListSearchByIdRoom(ra);
                
                sendRoomAssetListFor1234(roomAssetList);
                System.out.println("Tim kiem tai san bang id phong");
                break;
            case 4:
                roomAssetList = new ArrayList<>();
                roomAssetList = raDao.getListSearchByNameRoom(ra);
                sendRoomAssetListFor1234(roomAssetList);
                System.out.println("Tim kiem tai san bang ten phong");
                break;
                
           
                
        }
    }
}
