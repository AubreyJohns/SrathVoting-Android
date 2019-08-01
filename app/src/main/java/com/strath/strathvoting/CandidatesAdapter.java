package com.strath.strathvoting;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class CandidatesAdapter extends RecyclerView.Adapter<CandidatesAdapter.CustomViewHolder> {
    String url="https://red-mountie-10018.herokuapp.com/uploads/";
    private List<RetroUsers> dataList;
    private Context context;

    public CandidatesAdapter(Context context, List<RetroUsers> dataList){
        this.dataList = dataList;
        this.context=context;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        public RetroUsers mItem;
        CircleImageView avator;
        TextView textUser;
        TextView textDesc;

        public CustomViewHolder(View itemView) {
            super(itemView);
            avator = (de.hdodenhof.circleimageview.CircleImageView) itemView.findViewById(R.id.list_avatar);
            textUser = (TextView)itemView.findViewById(R.id.user);
            textDesc = (TextView)itemView.findViewById(R.id.list_desc);
        }
    }

    @Override

    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.candidates_layout, parent, false);
        return new CustomViewHolder(view);
    }

    @Override

    public void onBindViewHolder(final CustomViewHolder holder, int position) {
        holder.mItem = dataList.get(position);
        if(dataList.get(position).getImage()!=null){
            Picasso.get().load(url+dataList.get(position).getImage()).placeholder(R.drawable.a_avator).into(holder.avator);
            holder.textUser.setText(dataList.get(position).getUser());
            holder.textDesc.setText(dataList.get(position).getPosition());
        }else {
            holder.textUser.setText("Error");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, CandidatesDetailActivity.class);
                intent.putExtra(CandidatesDetailActivity.ARG_ITEM_ID, holder.mItem.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
