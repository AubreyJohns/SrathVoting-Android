package com.strath.strathvoting;

import com.google.gson.annotations.SerializedName;

public class RetroUsers {

    private int id;

    private String name;

    private String position;

    private String manifesto;

    private String image;

    private int votes;




    public int getId() {
        return id;
    }
    public String getUser() {
        return name;
    }
    public String getPosition() {
        return position;
    }
    public String getManifesto() {
        return manifesto;
    }
    public String getImage() {
        return image;
    }
    public int getVotes() {
        return votes;
    }


    public void setId(int id) {
        this.id = id;
    }
    public void setUser(String name) {
        this.name = name;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setManifesto(String manifesto) {
        this.manifesto = manifesto;
    }
    public void setVotes(int votes) {
        this.votes = votes;
    }

}
