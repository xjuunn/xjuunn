package com.example.myapplication.utils;

public class ListModel {
    private int id;
    private String imgurl;
    private String title;
    private String content;
    private String left;
    private String right;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public ListModel(int id, String imgurl, String title, String content, String left, String right) {
        this.id = id;
        this.imgurl = imgurl;
        this.title = title;
        this.content = content;
        this.left = left;
        this.right = right;
    }

    public ListModel(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public ListModel(int id, String imgurl, String title) {
        this.id = id;
        this.imgurl = imgurl;
        this.title = title;
    }

    public ListModel() {
    }


    public ListModel(String imgurl, String title) {
        this.imgurl = imgurl;
        this.title = title;
    }


    public ListModel(String imgurl, String title, String content, String left, String right) {
        this.imgurl = imgurl;
        this.title = title;
        this.content = content;
        this.left = left;
        this.right = right;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
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

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }
}
