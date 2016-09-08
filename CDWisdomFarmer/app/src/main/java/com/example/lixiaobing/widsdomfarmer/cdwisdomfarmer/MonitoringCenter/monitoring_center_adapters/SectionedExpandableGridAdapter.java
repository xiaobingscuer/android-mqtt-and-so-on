package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.MonitoringCenter.monitoring_center_adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;


import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.MonitoringCenter.mointoring_center_item.Item;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.MqttPublish.MyMqttPublish;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.R;

import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.R.color.*;

/**
 * Created by lenovo on 2/23/2016.
 */
public class SectionedExpandableGridAdapter extends RecyclerView.Adapter<SectionedExpandableGridAdapter.ViewHolder> {

    //data array
    private ArrayList<Object> mDataArrayList;

    //context
    private final Context mContext;

    //listeners
    private final ItemClickListener mItemClickListener;
    private final SectionStateChangeListener mSectionStateChangeListener;

    //view type
    private static final int VIEW_TYPE_SECTION = R.layout.monitoring_center_recyclerview_section_item;
    private static final int VIEW_TYPE_ITEM = R.layout.monitoring_center_recyclerview_item; //TODO : change this

    private DecimalFormat decimalFormat=new DecimalFormat("#0.00");

    public SectionedExpandableGridAdapter(Context context, ArrayList<Object> dataArrayList,
                                          final GridLayoutManager gridLayoutManager, ItemClickListener itemClickListener,
                                          SectionStateChangeListener sectionStateChangeListener) {
        mContext = context;
        mItemClickListener = itemClickListener;
        mSectionStateChangeListener = sectionStateChangeListener;
        mDataArrayList = dataArrayList;

//        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return isSection(position)?gridLayoutManager.getSpanCount():1;
//            }
//        });

    }

    private boolean isSection(int position) {
        return mDataArrayList.get(position) instanceof Section;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(viewType, parent, false), viewType);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (holder.viewType) {
            case VIEW_TYPE_ITEM:
                final Item item = (Item) mDataArrayList.get(position);
                holder.itemId.setText(item.getId());
                holder.itemName.setText(item.getSensorName());
                //holder.itemTiem.setText(item.getLastUpdateDate());
                holder.itemTiem.setText(""+item.getCurrentTimeIs());
                if(item.getIsLine()==1){
                    holder.itemLink.setText("已连接");
                    holder.itemLink.setTextColor(Color.rgb(80,176,80));
                }else {
                    holder.itemLink.setText("未连接");
                    holder.itemLink.setTextColor(Color.rgb(255,00,00));
                }
                int sensorType=item.getSensorTypeId();
                if(sensorType==1){
                    holder.itemData.setText(decimalFormat.format(item.getData())+"  "+item.getUnit());
                    holder.itemData.setVisibility(View.VISIBLE);
                    holder.itemSwitch.setVisibility(View.GONE);
                    holder.itemIamge.setVisibility(View.GONE);
                }else if(sensorType==2){
                    if(item.getIsSwitch()==0){
                        holder.itemSwitch.setChecked(true);
                    }else {
                        holder.itemSwitch.setChecked(false);
                    }
                    holder.itemSwitch.setVisibility(View.VISIBLE);
                    holder.itemData.setVisibility(View.GONE);
                    holder.itemIamge.setVisibility(View.GONE);
                }else if(sensorType==3){

                    holder.itemData.setText("纬度："+decimalFormat.format(item.getLat())+"\n"+"经度："+decimalFormat.format(item.getLng()));
                    holder.itemData.setVisibility(View.VISIBLE);
                    holder.itemSwitch.setVisibility(View.GONE);
                    holder.itemIamge.setVisibility(View.GONE);
                }else{
                    holder.itemIamge.setBackgroundColor(Color.rgb(100,50,70));
                    holder.itemIamge.setVisibility(View.VISIBLE);
                    holder.itemData.setVisibility(View.GONE);
                    holder.itemSwitch.setVisibility(View.GONE);
                }

                holder.itemSwitch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String topic=item.getDeviceNo();
                        int sensorId=Integer.parseInt(item.getId());
                        try {
                            if(item.getIsSwitch()==1){
                                //item.setIsSwitch(0);
                                new MyMqttPublish(topic,sensorId,0);
                            }else {
                                //item.setIsSwitch(1);
                                new MyMqttPublish(topic,sensorId,1);
                            }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });


                holder.goImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mItemClickListener.itemClicked(item);
                    }
                });

                break;
            case VIEW_TYPE_SECTION :
                final Section section = (Section) mDataArrayList.get(position);
                holder.sectionTextView.setText(section.getName());
                holder.sectionTextView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        mSectionStateChangeListener.onSectionStateChanged(section, !section.isExpanded);
                    }
                });

                holder.sectionToggleButton.setChecked(section.isExpanded);
                holder.sectionToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        mSectionStateChangeListener.onSectionStateChanged(section, isChecked);
                    }
                });

                break;
        }
    }

    @Override
    public int getItemCount() {
        return mDataArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isSection(position))
            return VIEW_TYPE_SECTION;
        else return VIEW_TYPE_ITEM;
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        //common
        View view;
        int viewType;

        //for section
        TextView sectionTextView;
        ToggleButton sectionToggleButton;

        //for item
        TextView itemId;
        TextView itemName;
        TextView itemLink;
        TextView itemTiem;
        TextView itemData;
        Switch itemSwitch;
        ImageView itemIamge;
        ImageView goImage;

        public ViewHolder(View view, int viewType) {
            super(view);
            this.viewType = viewType;
            this.view = view;
            if (viewType == VIEW_TYPE_ITEM) {
                itemData = (TextView) view.findViewById(R.id.monitoring_center_recyclerview_item_dataText);
                itemId = (TextView) view.findViewById(R.id.monitoring_center_recyclerview_item_sensor_id);
                itemName = (TextView) view.findViewById(R.id.monitoring_center_content_sensor_name);
                itemLink = (TextView) view.findViewById(R.id.monitoring_center_content_link_state);
                itemTiem = (TextView) view.findViewById(R.id.monitoring_center_content_update_time);
                itemSwitch=(Switch)view.findViewById(R.id.monitoring_center_recyclerview_item_switch);
                itemIamge=(ImageView)view.findViewById(R.id.monitoring_center_recyclerview_item_image);
                goImage=(ImageView)view.findViewById(R.id.monitoring_center_recyclerview_item_goImage);

            } else {
                sectionTextView = (TextView) view.findViewById(R.id.text_section);
                sectionToggleButton = (ToggleButton) view.findViewById(R.id.toggle_button_section);
            }
        }
    }
}
