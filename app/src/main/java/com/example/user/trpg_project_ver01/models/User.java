package com.example.user.trpg_project_ver01.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class User {

    public String useruid;
//    public String birthday;
//    public String email;
//    public String nickname;
//    public String password;
//    public String phone;
//    public String realname;
//    public String reg_time;
//    public String sex;




    public int starCount = 0;
    public Map<String, Boolean> stars = new HashMap<>();

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)

    }

    public User(String useruid/*, String birthday, String email, String nickname, String password, String phone, String realname, String reg_time, String sex*/) {
        this.useruid = useruid;
//        this.birthday = birthday;
//        this.email = email;
//        this.nickname = nickname;
//        this.password = password;
//        this.phone = phone;
//        this.realname = realname;
//        this.reg_time = reg_time;
//        this.sex = sex;

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("useruid", useruid);
//        result.put("birthday", birthday);
//        result.put("email", email);
//        result.put("nickname", nickname);
//        result.put("password", password);
//        result.put("phone", phone);
//        result.put("realname", realname);
//        result.put("reg_time", reg_time);
//        result.put("sex", sex);


        return result;
    }

}