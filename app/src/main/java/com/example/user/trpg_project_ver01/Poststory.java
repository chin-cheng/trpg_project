package com.example.user.trpg_project_ver01;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Poststory {

    public String author_uid;
    public String title;
    public String plot;
    public String position;
   // public Long arrive_chapter;
   // public Long arrive_branch;
   // public String option_name;
    public Long endcheck;
    //public String option;
    /*
    public String body;

    public String ans1;
    public String ans1page;
    public String ans2;
    public String ans2page;*/
   // public Long branch;
    //public Long chapter;
    public String story;




    public int starCount = 0;
    public Map<String, Boolean> stars = new HashMap<>();

    public Poststory() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)

    }

    public Poststory(String uid, String author, String title, String body) {
        this.author_uid=author_uid;
        this.plot=plot;
        this.position=position;
       // this.arrive_chapter=arrive_chapter;
       // this.arrive_branch=arrive_branch;
      //  this.option_name=option_name;
     //   this.branch=branch;
     //   this.chapter=chapter;
        this.endcheck=endcheck;
        //this.option=option;
        /*
        this.ans1=ans1;
        this.ans1page=ans1page;
        this.ans2=ans2;
        this.ans2page=ans2page;

        this.story=story;*/



    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("author_uid",author_uid);
        result.put("plot",plot);
        result.put("position",position);
        //result.put("arrive_chapter",arrive_chapter);
        //result.put("arrive_branch",arrive_branch);
       // result.put("option_name",option_name);
     //   result.put("branch",branch);
     //   result.put("chapter",chapter);
        result.put("endcheck",endcheck);
       // result.put("option",option);
        /*result.put("starCount", starCount);
        result.put("ans1",ans1);
        result.put("ans1page",ans1page);
        result.put("ans2",ans2);
        result.put("ans2page",ans2page);
        result.put("branch",branch);
        result.put("chapter",chapter);
        result.put("story",story);*/

        return result;
    }

}