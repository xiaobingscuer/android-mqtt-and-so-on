package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Device;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.FeedBack.FeedBackRecyclerViewAdapter;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.MonitoringCenter.mointoring_center_item.Item;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.R;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.UrlConnection.MyUrlConnect;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixiaobing on 2016/6/6.
 */
public class DeviceFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DeviceRecyclerViewAdapter adapter;
    private ArrayList<DeviceItem> deviceItems;
    private Context context;
    private SwipeRefreshLayout mRefreshLayout;
    private MyUrlConnect myUrlConnect;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
//                    String json=myUrlConnect.getResposeResult();
//                    Log.d("----------->>>",json);
//                    Gson gson =new Gson();
//                    QuryUserDevice quryUserDevice=new QuryUserDevice();
//                    deviceItems.clear();
//                    quryUserDevice=gson.fromJson(json,QuryUserDevice.class);
//                    for(int i=0;i<quryUserDevice.getDevices().size();i++){
//                        deviceItems.add(new DeviceItem( quryUserDevice.getDevices().get(i).getDeviceName()));
//                    }
//                    Log.d("======>", quryUserDevice.getDevices().get(0).getDeviceName());
//                    adapter.notifyDataSetChanged();
//                    Snackbar.make(recyclerView,"已更新",Snackbar.LENGTH_SHORT).show();
                    break;
                case 0:
                    break;
            }
        }
    };

    public DeviceFragment(Context context) {
        this.context = context;
    }

    public DeviceFragment(Context context, ArrayList<DeviceItem> deviceItems) {
        this.context = context;
        this.deviceItems = deviceItems;
    }

    public DeviceFragment(RecyclerView.LayoutManager layoutManager, ArrayList<DeviceItem> deviceItems) {
        this.layoutManager = layoutManager;
        this.deviceItems = deviceItems;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deviceItems=new ArrayList<DeviceItem>();
        deviceItems.add(new DeviceItem("设备1"));

        myUrlConnect=new MyUrlConnect();
        String requestParam="{\"userId\": \"11023\",\"apiKey\":\"0dd2135096404e0a8f22c0748bef4b27\"}";
        myUrlConnect.setRequestMethod("POST");
        myUrlConnect.setUrl("http://api.tlink.io/tlink_interface/api/device/queryUserDevices.htm");
        myUrlConnect.setRequestParameter(requestParam);
        myUrlConnect.setConnectTimeOut(8000);
        myUrlConnect.setReadTimeOut(8000);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_device, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.device_recyclerview);
        layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new DeviceRecyclerViewAdapter(deviceItems);
        recyclerView.setAdapter(adapter);

        mRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.device_content_swipeRefreshLayout);
        mRefreshLayout.setColorSchemeResources(R.color.swipe_color_1,R.color.swipe_color_2,R.color.swipe_color_3);
        mRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        mRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.swipe_color_4);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new UpdateTask().execute();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private class UpdateTask extends AsyncTask<Void,Void,ArrayList<DeviceItem>> {
        @Override
        protected ArrayList<DeviceItem> doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            new Thread(new Runnable() {
                Message message = new Message();
                @Override
                public void run() {
                    Log.d("============>","fkfdfdfdfdf");
                    if(myUrlConnect.urlConnection()==true){
                        message.what=1;
                    }else message.what=0;
                    handler.sendMessage(message);
                }
            }).start();

            ArrayList<DeviceItem> newData = new ArrayList<DeviceItem>();
            newData.add(new DeviceItem("新设备"));
            return newData;
        }

        @Override
        protected void onPostExecute(ArrayList<DeviceItem> result)
        {
            //adapter.add(result);
            //adapter.notifyDataSetChanged();
            mRefreshLayout.setRefreshing(false);
        }
    }
}
