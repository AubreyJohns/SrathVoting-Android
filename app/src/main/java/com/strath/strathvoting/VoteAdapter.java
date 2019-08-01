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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class VoteAdapter extends RecyclerView.Adapter<VoteAdapter.ViewHolder> {
    String url="https://red-mountie-10018.herokuapp.com/uploads/";
    private List<VoteList> dataList;
    private Context context;

    public VoteAdapter(Context context, List<VoteList> dataList) {
        this.context=context;
        this.dataList=dataList;
    }

    @Override
    public VoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.results_layout, parent, false);
        VoteAdapter.ViewHolder viewHolder = new VoteAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final VoteAdapter.ViewHolder holder, final int position) {
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
                Intent intent = new Intent(context, VoteDetailActivity.class);
                try {
                    intent.putExtra(VoteDetailActivity.ARG_ITEM_POSITION, holder.mItem.getPosition());
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
            /*
            priceGroup = (RadioGroup) view.findViewById(R.id.price_grp);
            priceGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        Toast.makeText(VoteAdapter.this.context,
                                "Radio button clicked " + radioGroup.getCheckedRadioButtonId(),
                                Toast.LENGTH_SHORT).show();
                }
            });
            */
        }
    }
}

