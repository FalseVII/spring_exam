package edu.tus.merchstore.demo.dto;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.*;

@Entity
@Table(name="products")
public class Product {

    @Id
    @Column(name="id")
    private Long id;

    @Column(name="brand")
    private String brand;

    @Column(name="type")
    private String type;

    @Column(name="title")
    private String title;

    @Column(name="rrp")
    private Double rrp;

    @Column(name="online")
    private Double online;

    @Column(name="image")
    private String image;

    @Lob
    @Column(name="description")
    private String description;

    public Product() {

    }

    public Product(Long id, String brand, String type, String title, Double rrp, Double online, String image,
                   String description) {

        this.id = id;
        this.brand = brand;
        this.type = type;
        this.title = title;
        this.rrp = rrp;
        this.online = online;
        this.image = image;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getRrp() {
        return rrp;
    }

    public void setRrp(Double rrp) {
        this.rrp = rrp;
    }

    public Double getOnline() {
        return online;
    }

    public void setOnline(Double online) {
        this.online = online;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
