package com.tfg.emp.home.application.Dto;

import com.tfg.emp.home.domain.entity.HomeType;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class HomeDto implements Serializable {
    private Long id;

    private String title;

    private String description;

    private String location;

    private double price;

    private HomeType homeType;

    private Date uploadDate;

    @Lob
    private byte[] image;

    @Column(name = "image_type")
    private String imageType;

    public HomeDto() {
    }

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


    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HomeDto homeDto = (HomeDto) o;
        return Objects.equals(id, homeDto.id) && Objects.equals(title, homeDto.title) && Objects.equals(description, homeDto.description) && Objects.equals(location, homeDto.location) && Objects.equals(price, homeDto.price) && homeType == homeDto.homeType && Objects.equals(uploadDate, homeDto.uploadDate) && Objects.deepEquals(image, homeDto.image) && Objects.equals(imageType, homeDto.imageType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, location, price, homeType, uploadDate, Arrays.hashCode(image), imageType);
    }

    @Override
    public String toString() {
        return "HomeDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", price='" + price + '\'' +
                ", homeType=" + homeType +
                ", uploadDate=" + uploadDate +
                ", image=" + Arrays.toString(image) +
                ", imageType='" + imageType + '\'' +
                '}';
    }
}
