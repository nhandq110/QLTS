package com.example.demo.Room_Asset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "room_asset")
public class Room_Asset {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "idRoom")
    private long idRoom;

    @Column(name = "nameRoom")
    private String nameRoom;

    @Column(name = "idAsset")
    private long idAsset;

    @Column(name = "nameAsset")
    private String nameAsset;

    @Column(name = "typeAsset")
    private String typeAsset;

    @Column(name = "priceAsset")
    private long priceAsset;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Long idRoom) {
        this.idRoom = idRoom;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public Long getIdAsset() {
        return idAsset;
    }

    public void setIdAsset(Long idAsset) {
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

    public Long getPriceAsset() {
        return priceAsset;
    }

    public void setPriceAsset(Long priceAsset) {
        this.priceAsset = priceAsset;
    }

    @Override
    public String toString() {
        return "Room_Asset{" +
                "id=" + id +
                ", idRoom=" + idRoom +
                ", nameRoom='" + nameRoom + '\'' +
                ", idAsset=" + idAsset +
                ", nameAsset='" + nameAsset + '\'' +
                ", typeAsset='" + typeAsset + '\'' +
                ", priceAsset=" + priceAsset +
                '}';
    }
}
