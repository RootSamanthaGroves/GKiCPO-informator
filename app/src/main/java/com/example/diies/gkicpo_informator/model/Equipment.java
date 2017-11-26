package com.example.diies.gkicpo_informator.model;

/**
 * Created by Dii on 2017-11-19.
 */
public class Equipment {


    private long id;
    private String name;
    private String description;
    private byte[] image;

    public Equipment() {
    }

    public Equipment(String name, String description, byte[] image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName(String name) {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


}
