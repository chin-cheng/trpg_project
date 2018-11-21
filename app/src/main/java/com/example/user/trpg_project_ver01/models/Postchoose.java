package com.example.user.trpg_project_ver01.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Postchoose {
    public String storyintro;
    public String style;
    public String author_uid;
    public String title;
    public Map<String, Boolean> stars = new HashMap<>();

    public Postchoose() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Postchoose(String uid, String author, String title, String body) {
        this.author_uid = author_uid;
        this.title = title;
        this.storyintro = storyintro;
        this.style = style;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor_uid() {
        return author_uid;
    }
    public void setAuthor_uid(String author_uid) {
        this.author_uid = author_uid;
    }
    public String getStyle() {
        return style;
    }
    public void setStyle(String style) {
        this.style = style;
    }
    public String getStoryintro() {
        return storyintro;
    }
    public void setStoryintro(String storyintro) {
        this.storyintro = storyintro;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
       result.put("author_uid", author_uid);
        result.put("title", title);
        result.put("storyintro", storyintro);
        result.put("style", style);

        return result;
    }

}