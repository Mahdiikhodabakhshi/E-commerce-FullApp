package com.tfg.emp.promotion.application.Dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class PromotionDTO implements Serializable {
    private Long id;
    private String title;
    private String description;
    private String location;
    private Date uploadDate;
    private byte[] image;
    private String imageType;

    public PromotionDTO() {
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
        PromotionDTO that = (PromotionDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(location, that.location) && Objects.equals(uploadDate, that.uploadDate) && Objects.deepEquals(image, that.image) && Objects.equals(imageType, that.imageType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, location, uploadDate, Arrays.hashCode(image), imageType);
    }

    @Override
    public String toString() {
        return "PromotionDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", uploadDate=" + uploadDate +
                ", image=" + Arrays.toString(image) +
                ", imageType='" + imageType + '\'' +
                '}';
    }
}
