package com.strath.strathvoting.Models;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import java.util.List;
import java.util.UUID;
import com.google.gson.annotations.SerializedName;

public class VoteParent implements ParentObject{

    private List<Object> mChildrenList;
    private UUID _id;
    @SerializedName("position")
    private String position;

    public VoteParent(String position) {
        this.position = position;
        _id = UUID.randomUUID();
    }

    public UUID get_id() {
        return _id;
    }

    public void set_id(UUID _id) {
        this._id = _id;
    }

    public String getVote() {
        return position;
    }

    public void setVote(String vote) {
        this.position = position;
    }

    @Override
    public List<Object> getChildObjectList() {
        return mChildrenList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        mChildrenList = list;
    }
}
