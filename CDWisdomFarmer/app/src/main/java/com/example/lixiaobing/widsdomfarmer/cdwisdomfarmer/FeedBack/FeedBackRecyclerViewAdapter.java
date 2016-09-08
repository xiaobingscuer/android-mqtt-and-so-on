package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.FeedBack;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Device.DeviceItem;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.R;

import java.util.ArrayList;

/**
 * Created by lixiaobing on 2016/4/25.
 */
public class FeedBackRecyclerViewAdapter extends RecyclerView.Adapter<FeedBackRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<FeedBackItem> feedBackItems;
    private static RecyclerViewItemOnclickListener myClickListener=null;
    @Override
    public void onClick(View v) {

        if(myClickListener !=null){
            myClickListener.onItemClick(v,(String)v.getTag());
        }
    }

    public static RecyclerViewItemOnclickListener getMyClickListener() {
        return myClickListener;
    }

    public static void setMyClickListener(RecyclerViewItemOnclickListener myClickListener) {
        FeedBackRecyclerViewAdapter.myClickListener = myClickListener;
    }

    interface RecyclerViewItemOnclickListener {

        void onItemClick(View v, String data);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_back_fragment_item,parent,false);
        ViewHolder vh = new ViewHolder(v);
        v.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.dataTextView.setText(feedBackItems.get(position).getFeedBackName());
    }

    @Override
    public int getItemCount() {
        return feedBackItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dataTextView;
        public ViewHolder(View v) {
            super(v);
            dataTextView = (TextView) v.findViewById(R.id.feed_back_item_content_text);
        }
    }

    public FeedBackRecyclerViewAdapter(ArrayList<FeedBackItem> feedBackItems) {
        this.feedBackItems = feedBackItems;
    }
}