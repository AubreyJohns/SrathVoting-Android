package com.strath.strathvoting;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ViewHolder> {
    String url="https://red-mountie-10018.herokuapp.com/uploads/";
    private List<VoteList> dataList;
    private Context context;

    public ResultsAdapter(Context context, List<VoteList> dataList) {
        this.context=context;
        this.dataList=dataList;
    }

    @Override
    public ResultsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.results_layout, parent, false);
        ResultsAdapter.ViewHolder viewHolder = new ResultsAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ResultsAdapter.ViewHolder holder, final int position) {
        holder.mItem = dataList.get(position);
        holder.packageName.setText(dataList.get(position).getPosition());
        Picasso.get().load(url+dataList.get(position).getImage()).placeholder(R.drawable.a_avator).into(new Target(){

            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                holder.rLayout.setBackground(new BitmapDrawable(context.getResources(), bitmap));
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                Log.d("TAG", "FAILED");
            }

            @Override
            public void onPrepareLoad(final Drawable placeHolderDrawable) {
                Log.d("TAG", "Prepare Load");
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ResultsDetailActivity.class);
                try {
                    intent.putExtra(ResultsDetailActivity.ARG_ITEM_POSITION, holder.mItem.getPosition());
                }catch (NullPointerException e){}
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { return dataList.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public VoteList mItem;
        public TextView packageName;
        RelativeLayout rLayout;
        //public RadioGroup priceGroup;

        public ViewHolder(View itemView) {
            super(itemView);
            packageName = (TextView) itemView.findViewById(R.id.package_name);
            rLayout=(RelativeLayout) itemView.findViewById(R.id.relativeLayout);

        }
    }
}
