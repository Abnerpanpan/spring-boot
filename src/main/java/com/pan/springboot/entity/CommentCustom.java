package com.pan.springboot.entity;

public class CommentCustom extends Comment {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
