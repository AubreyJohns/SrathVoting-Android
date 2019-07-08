package com.strath.strathvoting.ViewHolders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.strath.strathvoting.R;

public class VoteParentViewHolder extends ParentViewHolder {
    public TextView _textView;
    public ImageButton _imageButton;

    public VoteParentViewHolder(View itemView) {
        super(itemView);
        _textView = (TextView)itemView.findViewById(R.id.parentTitle);
        _imageButton = (ImageButton) itemView.findViewById(R.id.expandArrow);
    }
}
