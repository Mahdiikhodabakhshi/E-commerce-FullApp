package com.tfg.emp.activity.application.Dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class ActivitySimpleDTO implements Serializable {
    private Long id;
    private String title;
    private Date uploadDate;
    private byte[] image;
    private String imageType;

    public ActivitySimpleDTO() {
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

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivitySimpleDTO that = (ActivitySimpleDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(uploadDate, that.uploadDate) && Objects.deepEquals(image, that.image) && Objects.equals(imageType, that.imageType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, uploadDate, Arrays.hashCode(image), imageType);
    }

    @Override
    public String toString() {
        return "ActivitySimpleDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", uploadDate=" + uploadDate +
                ", image=" + Arrays.toString(image) +
                ", imageType='" + imageType + '\'' +
                '}';
    }
}
