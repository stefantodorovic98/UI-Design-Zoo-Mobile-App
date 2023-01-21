package com.example.pki.models;

public class Event {
    private String name;
    private String description;
    private int likes;
    private int image;

    public Event(String name, String description, int likes, int image) {
        this.name = name;
        this.description = description;
        this.likes = likes;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
