package com.example.javaalgorithmsrecipes;

public class CodeObject {
    String title;
    String author;
    int image;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public CodeObject(){}
    public CodeObject(String title, String author, int img) {
        this.title = title;
        this.author = author;
        this.image=img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String id) {
        this.author = id;
    }

}
