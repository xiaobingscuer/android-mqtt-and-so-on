package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.FeedBack;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Device.DeviceItem;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Device.DeviceRecyclerViewAdapter;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.R;

import java.util.ArrayList;

/**
 * Created by lixiaobing on 2016/6/3.
 */
public class FeedBackRecyclerFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager linearLayoutManager;
    private FeedBackRecyclerViewAdapter adapter;
    private ArrayList<FeedBackItem> feedBackItems;
    private Context context;

    public FeedBackRecyclerFragment(Context context) {
        this.context = context;
    }

    public FeedBackRecyclerFragment(Context context, ArrayList<FeedBackItem> feedBackItems) {
        this.context = context;
        this.feedBackItems = feedBackItems;
    }

    public FeedBackRecyclerFragment(ArrayList<FeedBackItem> feedBackItems, RecyclerView.LayoutManager linearLayoutManager) {
        this.feedBackItems = feedBackItems;
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feedBackItems=new ArrayList<FeedBackItem>();
        feedBackItems.add(new FeedBackItem("反馈的信息"));
        feedBackItems.add(new FeedBackItem("反馈的信息"));
        feedBackItems.add(new FeedBackItem("反馈的信息"));
        feedBackItems.add(new FeedBackItem("反馈的信息"));
        feedBackItems.add(new FeedBackItem("反馈的信息"));
        feedBackItems.add(new FeedBackItem("反馈的信息"));
        feedBackItems.add(new FeedBackItem("反馈的信息"));
        feedBackItems.add(new FeedBackItem("反馈的信息"));
        feedBackItems.add(new FeedBackItem("反馈的信息"));
        feedBackItems.add(new FeedBackItem("反馈的信息"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feed_back_fragment_recyclerview, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.feed_back_recyclerview);
        linearLayoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new FeedBackRecyclerViewAdapter(feedBackItems);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
