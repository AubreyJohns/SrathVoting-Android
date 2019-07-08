package com.strath.strathvoting.ViewHolders;

import android.view.View;
import android.widget.TextView;


import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.strath.strathvoting.R;

public class VoteChildViewHolder extends ChildViewHolder {
    public TextView option1;
    public VoteChildViewHolder(View itemView) {
        super(itemView);
        option1 = (TextView)itemView.findViewById(R.id.option1);
    }
}
