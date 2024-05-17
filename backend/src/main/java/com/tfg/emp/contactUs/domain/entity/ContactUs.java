package com.tfg.emp.contactUs.domain.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "contact_us")
public class ContactUs {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contactUsSequence")
    private Long id;

    @Column(name = "subject" , nullable = false , length = 100)
    private String subject;
    @Column(name = "email" , nullable = false , length = 200)
    private String email;
    @Column(name = "message" , nullable = false , length = 2000)
    private String message;

    @Column(name = "send_date" )
    private Date sendDate;

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
}
