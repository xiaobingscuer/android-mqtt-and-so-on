package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Device;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by lixiaobing on 2016/4/25.
 */
public class DeviceRecyclerViewAdapter extends RecyclerView.Adapter<DeviceRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<DeviceItem> deviceItems;
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
        DeviceRecyclerViewAdapter.myClickListener = myClickListener;
    }

    interface RecyclerViewItemOnclickListener {

        void onItemClick(View v, String data);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.decice_item,parent,false);
        ViewHolder vh = new ViewHolder(v);
        v.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.dataTextView.setText(deviceItems.get(position).getDeviceName());
    }

    @Override
    public int getItemCount() {
        return deviceItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dataTextView;
        public ViewHolder(View v) {
            super(v);
            dataTextView = (TextView) v.findViewById(R.id.device_text_item);
        }
    }

    public void add(ArrayList<DeviceItem> Items){
        deviceItems.addAll(Items);
    }

    public DeviceRecyclerViewAdapter(ArrayList<DeviceItem> deviceItems){
        this.deviceItems=deviceItems;
    }

}