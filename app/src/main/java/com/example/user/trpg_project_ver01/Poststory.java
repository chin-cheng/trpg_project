package com.example.user.trpg_project_ver01;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Poststory {

    public String plot_1;
    //public String author;
   // public String title;

    //public String body;

    public String plot1_1;
    public String plot1_2;
    public String plot1_3;
    public String plot1_4;
    public String plot1_5;
    public String infor1_1_c_1;
    public String infor1_1_c_2;
    public String infor1_2_c_1;
    public String infor1_2_c_2;




    public String realname;
    public String phone;
    public String birthday;
    public String email;
    public String sex;
    public int starCount = 0;
    public Map<String, Boolean> stars = new HashMap<>();

    public Poststory() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)

    }

    public Poststory(String uid, String author, String title, String body) {
        //this.uid = uid;
        //this.author = author;
        //this.title = title;
        //this.body = body;


        this.plot1_1=plot1_1;
        this.plot1_2=plot1_2;
        this.plot1_3=plot1_3;
        this.plot1_4=plot1_4;
        this.plot1_5=plot1_5;
        this.infor1_1_c_1=infor1_1_c_1;
        this.infor1_1_c_2=infor1_1_c_2;
        this.infor1_2_c_1=infor1_2_c_1;
        this.infor1_2_c_2=infor1_2_c_2;

        this.realname=realname;
        this.phone=phone;
        this.birthday=birthday;
        this.email = email;
        this.sex=sex;

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        //result.put("uid", uid);
        //result.put("author", author);
        //result.put("title", title);
        //result.put("body", body);
        result.put("starCount", starCount);
        result.put("stars", stars);
        result.put("email", email);

        result.put("realname",realname);
        result.put("phone",phone);
        result.put("birthday",birthday);
        result.put("sex",sex);


        result.put("plot1_1", plot1_1);
        result.put("plot1_2", plot1_2);
        result.put("plot1_3", plot1_3);
        result.put("plot1_4", plot1_4);
        result.put("plot1_5", plot1_5);
        result.put("infor1_1_c_1",infor1_1_c_1);
        result.put("infor1_1_c_2",infor1_1_c_2);
        result.put("infor1_2_c_1",infor1_2_c_1);
        result.put("infor1_2_c_2",infor1_2_c_2);


        return result;
    }

}