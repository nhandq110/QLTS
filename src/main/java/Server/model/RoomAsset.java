/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server.model;

import java.io.Serializable;

/**
 *
 * @author ACER
 */
public class RoomAsset implements Serializable{
    private int id;
    private int idRoom;
    private String nameRoom;
    private int idAsset;
    private String nameAsset;
    private String typeAsset;
    private int priceAsset;

    public RoomAsset(int id, int idRoom, String nameRoom, int idAsset, String nameAsset, String typeAsset, int priceAsset) {
        this.id = id;
        this.idRoom = idRoom;
        this.nameRoom = nameRoom;
        this.idAsset = idAsset;
        this.nameAsset = nameAsset;
        this.typeAsset = typeAsset;
        this.priceAsset = priceAsset;
    }

    public RoomAsset() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public int getIdAsset() {
        return idAsset;
    }

    public void setIdAsset(int idAsset) {
        this.idAsset = idAsset;
    }

    public String getNameAsset() {
        return nameAsset;
    }

    public void setNameAsset(String nameAsset) {
        this.nameAsset = nameAsset;
    }

    public String getTypeAsset() {
        return typeAsset;
    }

    public void setTypeAsset(String typeAsset) {
        this.typeAsset = typeAsset;
    }

    public int getPriceAsset() {
        return priceAsset;
    }

    public void setPriceAsset(int priceAsset) {
        this.priceAsset = priceAsset;
    }
    
    
}
