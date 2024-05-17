package com.tfg.emp.contactUs.application.Dto;



import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class ContactUsDto implements Serializable {
    private Long id;
    private String subject;
    private String email;
    private String message;
    private Date sendDate;

    public ContactUsDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactUsDto that = (ContactUsDto) o;
        return Objects.equals(id, that.id) && Objects.equals(subject, that.subject) && Objects.equals(email, that.email) && Objects.equals(message, that.message) && Objects.equals(sendDate, that.sendDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, email, message, sendDate);
    }

    @Override
    public String toString() {
        return "ContactUsDto{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", email='" + email + '\'' +
                ", message='" + message + '\'' +
                ", sendDate=" + sendDate +
                '}';
    }
}
