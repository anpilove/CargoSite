package com.CargoSite.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//CREATE TABLE UserCargo (
//        LOGIN varchar(255) NOT NULL,
//        PASSWORD varchar(255) NOT NULL,
//        CLASS int(15) NOT NULL,
//        PRIMARY KEY (LOGIN)
//        ) ENGINE=InnODB DEFAULT CHARSET=UTF8

@Entity
@Table(name="UserCargo", schema="warehouse")
public class UserCargo {

    @Id
    @Column(name = "LOGIN")
    private String login;

    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "CLASS")
    private int role;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
