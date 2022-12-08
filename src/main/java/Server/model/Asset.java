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
public class Asset implements Serializable{
    
    private int id;
    private String name;
    private String Type;
    private String currentLocation;
    private int price;

    public Asset(int id, String name, String Type, String currentLocation, int price) {
        this.id = id;
        this.name = name;
        this.Type = Type;
        this.currentLocation = currentLocation;
        this.price = price;
    }

    public Asset() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    
    
    
}
