package com.CargoSite.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String slug;
    private String content;
    private String author;
    private java.sql.Date pushishedOn;
    private java.sql.Date updatedOn;

    public Post() {

    }

    public Post(String title, String slug, String content, String author, java.sql.Date pushishedOn) {
        this.title = title;
        this.slug = slug;
        this.content = content;
        this.author = author;
        this.pushishedOn = pushishedOn;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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

    public Date getPushishedOn() {
        return pushishedOn;
    }

    public void setPushishedOn(Date pushishedOn) {
        this.pushishedOn = pushishedOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", slug='" + slug + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", pushishedOn=" + pushishedOn +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
