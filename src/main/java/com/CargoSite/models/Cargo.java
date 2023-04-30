package com.CargoSite.models;


import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

// SQL statement for creating table
//CREATE TABLE cargo (
//        ID int (15) NOT NULL AUTO_INCREMENT,
//        name_load varchar (255) NOT NULL,
//        content varchar (255) NOT NULL,
//        city_from varchar (255) NOT NULL,
//        city_to varchar (255) NOT NULL,
//        data_input datetime NOT NULL,
//        data_output datetime NOT NULL,
//        PRIMARY KEY (ID)
//        ) ENGINE=InnODB DEFAULT CHARSET=UTF8
@Entity
@Table(name="cargo", schema="WAREHOUSE")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long ID;
    @NotNull
    @Column(name = "name_load")
    private String nameLoad;
    @NotNull
    @Column(name = "content")
    private String content;
    @Column(name = "city_from")
    private String cityFrom;
    @NotNull
    @Column(name = "city_to")
    private String cityTo;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_output")
    private String dataOutput;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_input")
    private String dataInput;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getNameLoad() {
        return nameLoad;
    }

    public void setNameLoad(String nameLoad) {
        this.nameLoad = nameLoad;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public String getDataOutput() {
        return dataOutput;
    }

    public void setDataOutput(String dataOutput) {
        this.dataOutput = dataOutput;
    }

    public String getDataInput() {
        return dataInput;
    }

    public void setDataInput(String dataInput) {
        this.dataInput = dataInput;
    }
}