package com.example.user.trpg_project_ver01;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Postoption {
    public Long arrive_chapter;
    public Long arrive_branch;
    public String option_name;
    public Long option_position;
    public int starCount = 0;
    public Map<String, Boolean> stars = new HashMap<>();
    public Postoption() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)

    }


    public Postoption(Long arrive_chapter, Long arrive_branch, String option_name, Long option_position) {
        this.arrive_chapter = arrive_chapter;
        this.arrive_branch = arrive_branch;
        this.option_name = option_name;
        this.option_position = option_position;
    }


    public void setArrive_chapter(Long arrive_chapter) {
        this.arrive_chapter = arrive_chapter;
    }

    public void setArrive_branch(Long arrive_branch) {
        this.arrive_branch = arrive_branch;
    }

    public void setOption_name(String option_name) {
        this.option_name = option_name;
    }
    public void setOption_position(Long option_position) {
        this.option_position = option_position;
    }
    //    public Postoption(String uid, String author, String title, String body) {
//        this.arrive_chapter = arrive_chapter;
//        this.arrive_branch = arrive_branch;
//    }
//    public Map<String, Object> toMap() {
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("arrive_branch",arrive_branch);
//        result.put("arrive_chapter",arrive_chapter);
//
//        return result;
//    }



}