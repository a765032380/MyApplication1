package com.example.gll.myapplication.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gll.myapplication.R;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

        private ArrayList<String> mData;

        public HomeAdapter(ArrayList<String> data) {
            this.mData = data;
        }

        public void updateData(ArrayList<String> data) {
            this.mData = data;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
            // 实例化展示的view
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_home, parent, false);

            // 实例化viewholder
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull final HomeAdapter.ViewHolder holder, final int position) {
            holder.tv_home_title.setText(mData.get(position));
            holder.tv_home_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(holder.tv_home_more.getContext(),"你点击了第"+position+"条",Toast.LENGTH_SHORT).show();
                }
            });
            switch (position){
                case 0:
                    HomePopularAdapter popularAdapter = new HomePopularAdapter(getData());
                    setRecycleAdapter(popularAdapter,holder,LinearLayoutManager.HORIZONTAL);
                    break;
                case 1:
                    HomeVarietyAdapter varietyAdapter=new HomeVarietyAdapter(getData());
                    setRecycleAdapter(varietyAdapter,holder,LinearLayoutManager.HORIZONTAL);
                    break;
                case 2:
                    HomeActorSelectAdapter actorSelectAdapter=new HomeActorSelectAdapter(getData());
                    setRecycleAdapter(actorSelectAdapter,holder,LinearLayoutManager.HORIZONTAL);
                    break;
                case 3:
                    HomeMovieRaiseAdapter movieRaiseAdapter=new HomeMovieRaiseAdapter(getData());
                    setRecycleAdapter(movieRaiseAdapter,holder,LinearLayoutManager.VERTICAL);
                    break;
            }

        }

    private void setRecycleAdapter(RecyclerView.Adapter adapter,HomeAdapter.ViewHolder holder,int linearLayoutType){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.rv_home_content.getContext(), linearLayoutType, false);
        holder.rv_home_content.setLayoutManager(linearLayoutManager);
        holder.rv_home_content.setAdapter(adapter);
    }

        @Override
        public int getItemCount() {
            return mData == null ? 0 : mData.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {

            TextView tv_home_title;
            TextView tv_home_more;
            RecyclerView rv_home_content;

            public ViewHolder(View itemView) {
                super(itemView);
                tv_home_title = itemView.findViewById(R.id.tv_home_title);
                tv_home_more = itemView.findViewById(R.id.tv_home_more);
                rv_home_content = itemView.findViewById(R.id.rv_home_content);
            }
        }
    private ArrayList<String> getData(){
        ArrayList<String> mList=new ArrayList<>();
        for (int i=0;i<10;i++){
            mList.add("测试数据"+i);
        }
        return mList;
    }
}
