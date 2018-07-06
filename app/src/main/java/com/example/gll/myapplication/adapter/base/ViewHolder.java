package com.example.gll.myapplication.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.bumptech.glide.Glide;
import com.example.gll.myapplication.view.CustomImageView;
import com.example.gll.myapplication.view.RoundByXfermode;

public class ViewHolder extends RecyclerView.ViewHolder
{
    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;

    public ViewHolder(Context context, View itemView, ViewGroup parent)
    {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<View>();
    }


    public static ViewHolder get(Context context, ViewGroup parent, int layoutId)
    {

        View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        ViewHolder holder = new ViewHolder(context, itemView, parent);
        return holder;
    }


    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId)
    {
        View view = mViews.get(viewId);
        if (view == null)
        {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }
    public ViewHolder setText(int viewId, String text)
    {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }
    public ViewHolder setImage(int viewId, String text)
    {
        ImageView iv = getView(viewId);
        ImageLoaderManager.getInstance(iv.getContext()).displayImage(iv,text);
        return this;
    }
    public ViewHolder setVisibility(int viewId, int viewState)
    {
        View view = getView(viewId);
        view.setVisibility(viewState);
        return this;
    }

    public ViewHolder setImageResource(int viewId, int resId)
    {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }
    public ViewHolder setRoundByXfermode(int viewId,int resId){
        RoundByXfermode view=getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public ViewHolder setOnClickListener(int viewId,
                                         View.OnClickListener listener)
    {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }


    public ViewHolder setProgress(int viewId,int progress){
        ProgressBar view=getView(viewId);
        view.setProgress(progress);
        return this;
    }
    public String getText(int viewId){
        TextView view=getView(viewId);
        return view.getText().toString().trim();
    }
}