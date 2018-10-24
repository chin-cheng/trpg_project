package com.example.user.trpg_project_ver01.post;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class user {

    String userId;
    String email;
    Boolean userHasInterest;
    String eventId;
/*
    public String getUserId() {
        return userId;
    }
*/
    public String getUserEmail() {
        return email;
    }
/*
    public Boolean getUserHasInterest() {
        return userHasInterest;
    }

    public String getEventId() {
        return eventId;
    }*/

    public user(String email) {
       // this.userId = userId;
        this.email = email;
      //  this.userHasInterest = userHasInterest;
       // this.eventId = eventId;
    }
}