package com.mongodb.entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "students") //jab ham jpa use krte the tab ham @enitity se krte the mongodb ke case mai document ayga
public class Student
{

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCollage(String collage) {
        this.collage = collage;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getCollage() {
        return collage;
    }

    public Student(int id, String name, String city, String collage) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.collage = collage;
    }

    private int id;
    private String name;


    private  String city;
    private  String collage;


    public Student() {
    }

}
