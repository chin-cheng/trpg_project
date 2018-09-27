package com.example.user.trpg_project_ver01;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Poststory {

    public String author;
   public String title;

    public String body;

    public String ans1;
    public String ans1page;
    public String ans2;
    public String ans2page;
    public Long branch;
    public Long chapter;
    public String story;


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

        this.ans1=ans1;
        this.ans1page=ans1page;
        this.ans2=ans2;
        this.ans2page=ans2page;
        this.branch=branch;
        this.chapter=chapter;
        this.story=story;

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("starCount", starCount);
        result.put("ans1",ans1);
        result.put("ans1page",ans1page);
        result.put("ans2",ans2);
        result.put("ans2page",ans2page);
        result.put("branch",branch);
        result.put("chapter",chapter);
        result.put("story",story);

        return result;
    }

}