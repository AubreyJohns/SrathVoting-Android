package com.strath.strathvoting;

import com.google.gson.annotations.SerializedName;

public class VoteList {
    @SerializedName("id")
    private int id;
    @SerializedName("position")
    private String position;
    @SerializedName("image")
    private String image;

    public VoteList(int id, String position, String image) {
        this.id = id;
        this.position = position;
        this.image = image;
    }

    public int getId() {
        return id;
    }
    public String getPosition() {
        return position;
    }
    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
