package com.example.pki.models;

import java.util.ArrayList;
import java.util.List;

public class Animal {
    private String name;
    private int image;
    private String description;
    private List<Comment> commentList;

    public Animal(String name, int image, String description) {
        this.name = name;
        this.image = image;
        this.description = description;
        Comment comment1 = new Comment("jovica", "Opasna životinja!");
        Comment comment2 = new Comment("marica", "Neću više doći u ovaj zoološki vrt!");
        commentList = new ArrayList<>();
        commentList.add(comment1);
        commentList.add(comment2);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
