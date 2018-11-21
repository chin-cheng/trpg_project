package com.example.user.trpg_project_ver01.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class user {

    public String useruid;
    public String nickname;
    // public String title;

    //public String body;
//    public String nickname;
//    public String realname;
//    public String phone;
//    public String birthday;
//    public String email;
//    public String sex;
//    public int starCount = 0;
    public Map<String, Boolean> stars = new HashMap<>();

    public user() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public user(String uid, String author, String title, String body) {
        this.useruid = useruid;
        this.nickname = nickname;
        //this.title = title;
        //this.body = body;


    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("useruid", useruid);
        result.put("nickname", nickname);
        return result;
    }
}