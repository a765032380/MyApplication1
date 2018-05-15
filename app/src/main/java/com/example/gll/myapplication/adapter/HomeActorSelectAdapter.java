package com.example.gll.myapplication.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gll.myapplication.R;

import java.util.ArrayList;

/**
 * 首页演员选拔适配器
 */
public class HomeActorSelectAdapter extends RecyclerView.Adapter<HomeActorSelectAdapter.ViewHolder>{

    private ArrayList<String> mData;

    public HomeActorSelectAdapter(ArrayList<String> data) {
        this.mData = data;
    }

    public void updateData(ArrayList<String> data) {
        this.mData = data;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_home_actor_select, parent, false);

        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.tv_home_actor_select_in.setText(mData.get(position));
        holder.tv_home_actor_select_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.tv_home_actor_select_in.getContext(),"你点击了我要参演",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_home_actor_select;
        ImageView iv_home_actor_select;
        TextView tv_home_actor_select_in;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_home_actor_select=itemView.findViewById(R.id.tv_home_actor_select);
            iv_home_actor_select=itemView.findViewById(R.id.iv_home_actor_select);
            tv_home_actor_select_in=itemView.findViewById(R.id.tv_home_actor_select_in);
        }
    }
}
