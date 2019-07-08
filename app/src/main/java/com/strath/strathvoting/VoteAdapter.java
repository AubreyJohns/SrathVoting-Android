package com.strath.strathvoting;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.List;

public class VoteAdapter extends RecyclerView.Adapter<VoteAdapter.ViewHolder>{
    String url="https://red-mountie-10018.herokuapp.com/uploads/";
    private List<VoteList> dataList;
    private List<RetroUsers> usersList;
    private Context context;

    public VoteAdapter(Context context, List<VoteList> dataList, List<RetroUsers> usersListList) {
        this.context=context;
        this.dataList=dataList;
        this.usersList=usersList;
    }

    @Override
    public VoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vote_layout, parent, false);
        VoteAdapter.ViewHolder viewHolder = new VoteAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final VoteAdapter.ViewHolder holder, int position) {
        holder.packageName.setText(dataList.get(position).getPosition());

    }

    @Override
    public int getItemCount() { return dataList.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView packageName;
        RelativeLayout rLayout;
        //public RadioGroup priceGroup;

        public ViewHolder(View view) {
            super(view);
            packageName = (TextView) view.findViewById(R.id.package_name);
            rLayout=(RelativeLayout) view.findViewById(R.id.relativeLayout);
            /*
            priceGroup = (RadioGroup) view.findViewById(R.id.price_grp);

            priceGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {

                        Toast.makeText(PackageRecyclerViewAdapter.this.context,
                                "Radio button clicked " + radioGroup.getCheckedRadioButtonId(),
                                Toast.LENGTH_SHORT).show();

                }
            });
            */
        }
    }
}
