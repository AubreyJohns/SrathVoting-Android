package com.strath.strathvoting.Models;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
public class VoteCreator {
    static VoteCreator _voteCreator;
    static List<VoteParent> _voteParents;
    static List<VoteParent> positionsList;

    public VoteCreator(Context context, List<VoteParent> positionsList) {
        this.positionsList=positionsList;
        _voteParents = new ArrayList<>();

        if(positionsList!= null && positionsList.size() !=0) {
            for(int i=0;i<positionsList.size();i++)
            {
                VoteParent vote = new VoteParent(positionsList.get(i).getVote());
                _voteParents.add(vote);
            }
        }else{
            VoteParent vote = new VoteParent("position");
            _voteParents.add(vote);
        }

    }

    public static VoteCreator get(Context context)
    {
        if(_voteCreator == null)
            _voteCreator = new VoteCreator(context,positionsList );
        return _voteCreator;
    }

    public static List<VoteParent> getAll() {
        return _voteParents;
    }
}
