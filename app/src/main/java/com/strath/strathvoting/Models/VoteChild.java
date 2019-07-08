package com.strath.strathvoting.Models;

import com.google.gson.annotations.SerializedName;

public class VoteChild {
    @SerializedName("name")
    public String name;

    public VoteChild(String name) {
        this.name = name;
    }

    public String getOption1() {
        return name;
    }

    public void setOption1(String name) {
        this.name = name;
    }

}
