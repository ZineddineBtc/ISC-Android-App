package com.example.isc.Core;

public class MyPost {
    private String postedText, myPostLevel, myPostTagColleague, myPostEvents;
    private int postedImage;
    private MyUser myUser;



    public MyPost(MyUser myUser, String postedText, int postedImage,
                  String myPostLevel, String myPostTagColleague, String myPostEvents){
        this.postedText = postedText;
        this.postedImage = postedImage;
        this.myUser = myUser;
        this.myPostLevel = myPostLevel;
        this.myPostTagColleague = myPostTagColleague;
        this.myPostEvents = myPostEvents;
    }

    public String getMyPostEvents() {
        return myPostEvents;
    }

    public void setMyPostEvents(String myPostEvents) {
        this.myPostEvents = myPostEvents;
    }

    public String getMyPostLevel() {
        return myPostLevel;
    }

    public void setMyPostLevel(String myPostLevel) {
        this.myPostLevel = myPostLevel;
    }

    public String getMyPostTagColleague() {
        return myPostTagColleague;
    }

    public void setMyPostTagColleague(String myPostTagColleague) {
        this.myPostTagColleague = myPostTagColleague;
    }

    public String getPostedText() {
        return postedText;
    }

    public void setPostedText(String postedText) {
        this.postedText = postedText;
    }

    public MyUser getMyUser() {
        return myUser;
    }

    public void setMyUser(MyUser myUser) {
        this.myUser = myUser;
    }

    public int getPostedImage() {
        return postedImage;
    }

    public void setPostedImage(int postedImage) {
        this.postedImage = postedImage;
    }
}
