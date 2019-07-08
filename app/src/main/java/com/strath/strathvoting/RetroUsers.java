package com.strath.strathvoting;

import com.google.gson.annotations.SerializedName;

public class RetroUsers {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("position")
    private String position;
    @SerializedName("manifesto")
    private String manifesto;
    @SerializedName("image")
    private String image;
    @SerializedName("votes")
    private int votes;


    public RetroUsers(int id, String name,String position, String manifesto, String image, int votes) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.manifesto = manifesto;
        this.image = image;
        this.votes = votes;
    }

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
