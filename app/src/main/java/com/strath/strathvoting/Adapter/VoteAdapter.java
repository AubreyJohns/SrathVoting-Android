package com.strath.strathvoting.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.strath.strathvoting.Models.VoteChild;
import com.strath.strathvoting.Models.VoteParent;
import com.strath.strathvoting.R;
import com.strath.strathvoting.RetroUsers;
import com.strath.strathvoting.ViewHolders.VoteChildViewHolder;
import com.strath.strathvoting.ViewHolders.VoteParentViewHolder;
import com.strath.strathvoting.VoteList;

import java.util.List;

public class VoteAdapter extends ExpandableRecyclerAdapter<VoteParentViewHolder, VoteChildViewHolder> {
    LayoutInflater inflater;
    private Context context;

    public VoteAdapter(Context context,List<ParentObject> parentItemList) {
        super(context, parentItemList);
        inflater = LayoutInflater.from(context);
        this.context=context;
    }

    @Override
    public VoteParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.parent_vote,viewGroup,false);
        return new VoteParentViewHolder(view);
    }

    @Override
    public VoteChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.child_vote,viewGroup,false);
        return new VoteChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(VoteParentViewHolder voteParentViewHolder, int i, Object o) {
        VoteParent vote = (VoteParent)o;
        voteParentViewHolder._textView.setText(vote.getVote());

    }

    @Override
    public void onBindChildViewHolder(VoteChildViewHolder voteChildViewHolder, int i, Object o) {
        VoteChild vote = (VoteChild)o;
        voteChildViewHolder.option1.setText(vote.getOption1());
    }
}
