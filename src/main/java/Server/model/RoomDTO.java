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
public class RoomDTO implements Serializable{
    
    private int status;
    
    private int id;
    private String name;
    private String description;

    public RoomDTO(int status, int id, String name, String description) {
        this.status = status;
        this.id = id;
        this.name = name;
        this.description = description;
    }



    public RoomDTO() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
