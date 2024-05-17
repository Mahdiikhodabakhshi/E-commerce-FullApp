package com.tfg.emp.home.domain.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "homes")
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "houseSequence")
    private Long id;

    @Column(name = "Title" , nullable = false , length = 100)
    private String title;

    @Column(name = "Description" , nullable = false , length = 300)
    private String description;

    @Column(name = "Location" , nullable = false , length = 100)
    private String location;

    @Column(name = "price" , nullable = false , length = 100)
    private double price;

    @Column(name = "home_type" , nullable = false , length = 100)
    @Enumerated(EnumType.STRING)
    private HomeType homeType;

    @Column(name = "upload_date" )
    private Date uploadDate;

    @Lob
    private byte[] image;

    @Column(name = "image_type")
    private String imageType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public HomeType getHomeType() {
        return homeType;
    }

    public void setHomeType(HomeType homeType) {
        this.homeType = homeType;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
}
