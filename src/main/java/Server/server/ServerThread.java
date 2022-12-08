/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server.server;

import Server.DAO.AssetDAO;
import Server.DAO.RoomDAO;
import Server.model.Asset;
import Server.model.Room;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class ServerThread extends Thread{
    private Socket socket;
    private int port;
    private AssetDAO aDAO;
    private RoomDAO rDAO;
    private ArrayList<Room> listRoom;
    private ArrayList<Asset> listAsset;

    public ServerThread() {
    }

    public ServerThread(Socket socket, int port, RoomDAO rDAO, ArrayList<Room> listRoom) {
        this.socket = socket;
        this.port = port;
        this.rDAO = rDAO;
        this.listRoom = listRoom;
    }

    public ServerThread(Socket socket, int port, AssetDAO aDAO, ArrayList<Asset> listAsset) {
        this.socket = socket;
        this.port = port;
        this.aDAO = aDAO;
        this.listAsset = listAsset;
    }
    
    @Override
    public void run() {
        
    }
    
    
}
