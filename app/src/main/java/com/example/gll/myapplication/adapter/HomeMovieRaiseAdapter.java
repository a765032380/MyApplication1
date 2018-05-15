package com.example.gll.myapplication.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gll.myapplication.R;

import java.util.ArrayList;

/**
 * 首页电影众筹适配器
 */
public class HomeMovieRaiseAdapter extends RecyclerView.Adapter<HomeMovieRaiseAdapter.ViewHolder>{

    private ArrayList<String> mData;

    public HomeMovieRaiseAdapter(ArrayList<String> data) {
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_home_movie_raise, parent, false);

        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_title.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_home__movie_raise;
        ImageView iv_home_movie_raise;


        public ViewHolder(View itemView) {
            super(itemView);
            tv_title=itemView.findViewById(R.id.tv_title);
            tv_home__movie_raise=itemView.findViewById(R.id.tv_home__movie_raise);
            iv_home_movie_raise=itemView.findViewById(R.id.iv_home_movie_raise);

        }
    }
}
