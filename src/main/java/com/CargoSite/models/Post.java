package com.CargoSite.models;


import jakarta.persistence.*;

import java.sql.Date;

//CREATE TABLE Post (
//        ID int(15) NOT NULL AUTO_INCREMENT,
//        TITLE varchar(255),
//        DATE_CREATE datetime,
//        AUTHOR varchar(255),
//        CONTENT text,
//
//
//        PRIMARY KEY (ID)
//        ) ENGINE=InnODB DEFAULT CHARSET=UTF8

@Entity
@Table(name="Post", schema="warehouse")
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "DATE_CREATE")
    private Date dateCreated;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

}
