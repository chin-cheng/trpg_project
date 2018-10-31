package com.example.user.trpg_project_ver01.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Post {

    public String uid;
    //public String author;
   // public String title;

    //public String body;
    public String nickname;
    public String realname;
    public String phone;
    public String birthday;
    public String email;
    public String sex;
    public int starCount = 0;
    public Map<String, Boolean> stars = new HashMap<>();

    public Post() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Post(String uid, String author, String title, String body) {
        this.uid = uid;
        //this.author = author;
        //this.title = title;
        //this.body = body;


        this.nickname=nickname;
        this.realname=realname;
        this.phone=phone;
        this.birthday=birthday;
        this.email = email;
        this.sex=sex;

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        //result.put("author", author);
        //result.put("title", title);
        //result.put("body", body);
        result.put("starCount", starCount);
        result.put("stars", stars);
        result.put("email", email);
        result.put("nickname", nickname);
        result.put("realname",realname);
        result.put("phone",phone);
        result.put("birthday",birthday);
        result.put("sex",sex);

        return result;
    }

}