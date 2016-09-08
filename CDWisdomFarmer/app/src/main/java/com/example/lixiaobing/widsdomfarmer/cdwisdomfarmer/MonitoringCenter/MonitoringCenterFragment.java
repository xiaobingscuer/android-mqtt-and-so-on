package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.MonitoringCenter;

import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.DeveloperAPI.DeviceOfUser;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.DeveloperAPI.DeviceSensorHashMap;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.DeveloperAPI.SensorDatasAanthorJson;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.DeveloperAPI.SensorsOfDevice;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.DeveloperAPI.UserDevicePostJson;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Device.Device;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Device.DeviceItem;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Device.DeviceRecyclerViewAdapter;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.MonitoringCenter.mointoring_center_item.Item;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.MonitoringCenter.monitoring_center_adapters.ItemClickListener;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.MonitoringCenter.monitoring_center_adapters.Section;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.MonitoringCenter.monitoring_center_adapters.SectionedExpandableLayoutHelper;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.R;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Service.SubscribService;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.UrlConnection.MyUrlConnect;
import com.google.gson.Gson;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixiaobing on 2016/6/6.
 */
public class MonitoringCenterFragment extends Fragment {
    private static RecyclerView recyclerView;
    public static SectionedExpandableLayoutHelper sectionedExpandableLayoutHelper;
    private static Context context;
    private ItemClickListener itemClickListener;
    private final int gridSpanCount=1;

    private  DeviceOfUser deviceOfUser;
    private  SensorsOfDevice sensorsOfDevice;
    private   static ArrayList<SensorsOfDevice> sensrosOfDeviceList;
    private  final String deviceOfUserUrl="http://api.tlink.io/tlink_interface/api/device/queryUserDevices.htm";
    private  final String sensorsOfDeviceUrl="http://api.tlink.io/tlink_interface/api/device/queryDevicesInfo.htm";
    private  final String userPostJson="{\"userId\":\"11023\",\"apiKey\":\"0dd2135096404e0a8f22c0748bef4b27\"}";
    private  String devicePostJson="{\"userId\":\"11023\",\"deviceId\":\"3244\"}";
    private  MyUrlConnect myUrlConnect;
    private  boolean isOk=false;
    private  boolean  isok=false;
    private static Gson gson;
    private static SensorDatasAanthorJson sensorDatasAanthorJson=new SensorDatasAanthorJson();
    private static ArrayList<Item> itemList;
    private MqttClient client;

    private final  static int SUBSCRIB_TYPE=(int)'S';
    private final  static int HTTPAPI_TYPE=(int)'H';
    private  static SwipeRefreshLayout mRefreshLayout;
    String mqSaveDataJson=null;

    public static android.os.Handler handler=new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 3:
                    Log.d("------>","订阅的消息来到handler");

                    String mqttJson=msg.obj.toString();
                    gson=new Gson();
                    sensorDatasAanthorJson=new SensorDatasAanthorJson();
                    sensorDatasAanthorJson=gson.fromJson(mqttJson, SensorDatasAanthorJson.class);
                    //getPostData(msg.arg1);

                    HashMap<String,ArrayList<Item>> mqSaveMap1=new HashMap<>();
                    for(int i=0;i<sensrosOfDeviceList.size();i++){
                        boolean isThis=false;
                        itemList=new ArrayList<>();
                        for(int k=0;k<sensrosOfDeviceList.get(i).getDevice().getSensorsList().size();k++){
                            Item item=new Item();
                            String snesorId=sensrosOfDeviceList.get(i).getDevice().getSensorsList().get(k).getId();
                            item.setId(snesorId);
                            item.setSensorName(sensrosOfDeviceList.get(i).getDevice().getSensorsList().get(k).getSensorName());
                            item.setIsLine(sensrosOfDeviceList.get(i).getDevice().getSensorsList().get(k).getIsLine());
                            item.setLastUpdateDate(sensrosOfDeviceList.get(i).getDevice().getSensorsList().get(k).getLastUpdateDate());
                            item.setUnit(sensrosOfDeviceList.get(i).getDevice().getSensorsList().get(k).getUnit());
                            item.setSensorTypeId(sensrosOfDeviceList.get(i).getDevice().getSensorsList().get(k).getSensorTypeId());
                            item.setDeviceNo(sensrosOfDeviceList.get(i).getDevice().getDeviceNo());
                            item.setLat(Float.parseFloat(sensrosOfDeviceList.get(i).getDevice().getLat()));
                            item.setLng(Float.parseFloat(sensrosOfDeviceList.get(i).getDevice().getLng()));

                            for(int j=0;j<sensorDatasAanthorJson.getSensorDatas().size();j++){
                                Log.d("------>","订阅的消息来到handler  到这里可执行");
                                int id=sensorDatasAanthorJson.getSensorDatas().get(j).getSensorsId();
                                System.out.println(id);
                                Log.d("传感器Id------>",snesorId);
                                System.out.print(Integer.parseInt(snesorId));
                                if(id==Integer.parseInt(snesorId)){
                                    isThis=true;
                                    Log.d("------>","订阅的消息来到handler  判断Id是否相等可执行");
                                    if(sensrosOfDeviceList.get(i).getDevice().getSensorsList().get(k).getSensorTypeId()==1){
                                        Log.d("------>","订阅的消息来到handler  判断传感器的类型是否等于1");
                                        item.setData(sensorDatasAanthorJson.getSensorDatas().get(j).getValue());
                                    }else if(sensrosOfDeviceList.get(i).getDevice().getSensorsList().get(k).getSensorTypeId()==2){
                                        Log.d("------>","订阅的消息来到handler  判断传感器的类型是否等于2");
                                        item.setIsSwitch(sensorDatasAanthorJson.getSensorDatas().get(j).getSwitcher());
                                    }else if(sensrosOfDeviceList.get(i).getDevice().getSensorsList().get(k).getSensorTypeId()==3){
                                        item.setLat(sensorDatasAanthorJson.getSensorDatas().get(j).getLat());
                                        item.setLng(sensorDatasAanthorJson.getSensorDatas().get(j).getLng());
                                    }else if(sensrosOfDeviceList.get(i).getDevice().getSensorsList().get(k).getSensorTypeId()==4){
                                        item.setImagHex(sensorDatasAanthorJson.getSensorDatas().get(j).getImgHex());
                                    }
                                }
                            }
                            itemList.add(item);

                        }
                        if(isThis){
                            Log.d("------>","订阅的消息来到handler  准备更新 可执行");
                            sectionedExpandableLayoutHelper.removeSection(sensrosOfDeviceList.get(i).getDevice().getDeviceName());
                            sectionedExpandableLayoutHelper.addSection(sensrosOfDeviceList.get(i).getDevice().getDeviceName(),itemList);
                            sectionedExpandableLayoutHelper.notifyDataSetChanged();
                        }

                        mqSaveMap1.put(sensrosOfDeviceList.get(i).getDevice().getDeviceName(),itemList);

                    }
                    gson=new Gson();
                    DeviceSensorHashMap deviceSensorHashMap1=new DeviceSensorHashMap(mqSaveMap1);
                    String saveJson1=gson.toJson(deviceSensorHashMap1);

                    BufferedWriter bufferWriter1=null;
                    try {
                        FileOutputStream outputStream= context.openFileOutput("mqdata2", Context.MODE_PRIVATE);
                        bufferWriter1 = new BufferedWriter(new OutputStreamWriter(outputStream));
                        bufferWriter1.write(saveJson1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        if (bufferWriter1 != null) {
                            try {
                                bufferWriter1.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    System.out.println("保存数据已完成:");

                    break;
                case 2:
                    Log.d("------>","来到handler");
                    mRefreshLayout.setRefreshing(false);
                    if(msg.arg1==SUBSCRIB_TYPE){
                        for(int i=0;i<sensrosOfDeviceList.size();i++){
                            boolean isThis=false;
                            itemList=new ArrayList<>();
                            for(int k=0;k<sensrosOfDeviceList.get(i).getDevice().getSensorsList().size();k++){
                                Item item=new Item();
                                String snesorId=sensrosOfDeviceList.get(i).getDevice().getSensorsList().get(k).getId();
                                item.setId(snesorId);
                                item.setSensorName(sensrosOfDeviceList.get(i).getDevice().getSensorsList().get(k).getSensorName());
                                item.setIsLine(sensrosOfDeviceList.get(i).getDevice().getSensorsList().get(k).getIsLine());
                                item.setLastUpdateDate(sensrosOfDeviceList.get(i).getDevice().getSensorsList().get(k).getLastUpdateDate());
                                item.setUnit(sensrosOfDeviceList.get(i).getDevice().getSensorsList().get(k).getUnit());
                                item.setSensorTypeId(sensrosOfDeviceList.get(i).getDevice().getSensorsList().get(k).getSensorTypeId());
                                item.setDeviceNo(sensrosOfDeviceList.get(i).getDevice().getDeviceNo());
                                item.setLat(Float.parseFloat(sensrosOfDeviceList.get(i).getDevice().getLat()));
                                item.setLng(Float.parseFloat(sensrosOfDeviceList.get(i).getDevice().getLng()));

                                for(int j=0;j<sensorDatasAanthorJson.getSensorDatas().size();j++){
                                    Log.d("------>","订阅的消息来到handler  到这里可执行");
                                    int id=sensorDatasAanthorJson.getSensorDatas().get(j).getSensorsId();
                                    System.out.println(id);
                                    Log.d("传感器Id------>",snesorId);
                                    System.out.print(Integer.parseInt(snesorId));
                                    if(id==Integer.parseInt(snesorId)){
                                        isThis=true;
                                        Log.d("------>","订阅的消息来到handler  判断Id是否相等可执行");
                                        if(sensrosOfDeviceList.get(i).getDevice().getSensorsList().get(k).getSensorTypeId()==1){
                                            Log.d("------>","订阅的消息来到handler  判断传感器的类型是否等于1");
                                            item.setData(sensorDatasAanthorJson.getSensorDatas().get(j).getValue());
                                        }else if(sensrosOfDeviceList.get(i).getDevice().getSensorsList().get(k).getSensorTypeId()==2){
                                            Log.d("------>","订阅的消息来到handler  判断传感器的类型是否等于1");
                                            item.setIsSwitch(sensorDatasAanthorJson.getSensorDatas().get(j).getSwitcher());
                                        }else if(sensrosOfDeviceList.get(i).getDevice().getSensorsList().get(k).getSensorTypeId()==3){
                                            item.setLat(sensorDatasAanthorJson.getSensorDatas().get(j).getLat());
                                            item.setLng(sensorDatasAanthorJson.getSensorDatas().get(j).getLng());
                                        }else if(sensrosOfDeviceList.get(i).getDevice().getSensorsList().get(k).getSensorTypeId()==4){
                                            item.setImagHex(sensorDatasAanthorJson.getSensorDatas().get(j).getImgHex());
                                        }
                                    }
                                }
                                itemList.add(item);
                            }
                            if(isThis){
                                Log.d("------>","订阅的消息来到handler  准备更新 可执行");
                                sectionedExpandableLayoutHelper.removeSection(sensrosOfDeviceList.get(i).getDevice().getDeviceName());
                                sectionedExpandableLayoutHelper.addSection(sensrosOfDeviceList.get(i).getDevice().getDeviceName(),itemList);
                                sectionedExpandableLayoutHelper.notifyDataSetChanged();
                             }


                        }
                    }else {
                        HashMap<String,ArrayList<Item>> mqSaveMap=new HashMap<>();
                        for(int i=0;i<sensrosOfDeviceList.size();i++){
                            itemList=new ArrayList<>();
                            for(int k=0;k<sensrosOfDeviceList.get(i).getDevice().getSensorsList().size();k++){
                                Item item=new Item();
                                item.setId(sensrosOfDeviceList.get(i).getDevice().getSensorsList().get(k).getId());
                                item.setSensorName(sensrosOfDeviceList.get(i).getDevice().getSensorsList().get(k).getSensorName());
                                item.setIsLine(sensrosOfDeviceList.get(i).getDevice().getSensorsList().get(k).getIsLine());
                                item.setLastUpdateDate(sensrosOfDeviceList.get(i).getDevice().getSensorsList().get(k).getLastUpdateDate());
                                item.setSensorTypeId(sensrosOfDeviceList.get(i).getDevice().getSensorsList().get(k).getSensorTypeId());
                                item.setUnit(sensrosOfDeviceList.get(i).getDevice().getSensorsList().get(k).getUnit());
                                item.setLat(Float.parseFloat(sensrosOfDeviceList.get(i).getDevice().getLat()));
                                item.setLng(Float.parseFloat(sensrosOfDeviceList.get(i).getDevice().getLng()));
                                item.setDeviceNo(sensrosOfDeviceList.get(i).getDevice().getDeviceNo());
                                itemList.add(item);

                            }
//                            sectionedExpandableLayoutHelper.addSection(sensrosOfDeviceList.get(i).getDevice().getDeviceName(),itemList);
//                            sectionedExpandableLayoutHelper.notifyDataSetChanged();

                            mqSaveMap.put(sensrosOfDeviceList.get(i).getDevice().getDeviceName(),itemList);
                        }

                        gson=new Gson();
                        DeviceSensorHashMap deviceSensorHashMap=new DeviceSensorHashMap(mqSaveMap);
                        String saveJson=gson.toJson(deviceSensorHashMap);
                        System.out.print(saveJson);
                        BufferedWriter bufferWriter=null;
                        try {
                            FileOutputStream outputStream= context.openFileOutput("mqdata2", Context.MODE_PRIVATE);
                            bufferWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                            bufferWriter.write(saveJson);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }finally {
                            if (bufferWriter != null) {
                                try {
                                    bufferWriter.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        System.out.println("保存数据已完成:");
                    }

                    break;
                case 1:
                    mRefreshLayout.setRefreshing(false);
                    break;
                case 0:
                    break;
            }
        }
    };
    public MonitoringCenterFragment(Context context, ItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPostData(HTTPAPI_TYPE);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_monitoring_center, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.monitoring_center_recyclerview);
        sectionedExpandableLayoutHelper = new SectionedExpandableLayoutHelper(context,recyclerView,itemClickListener, gridSpanCount);

        try {
                FileInputStream inputstream=context.openFileInput("mqdata2");
                BufferedReader bufferReader=new BufferedReader(new InputStreamReader(inputstream));
                String line="";
                StringBuilder string=new StringBuilder();
                while (((line=bufferReader.readLine())!=null)){
                    string.append(line);
                }
                mqSaveDataJson=string.toString();

                gson=new Gson();
                DeviceSensorHashMap deviceSensorHashMap=new DeviceSensorHashMap();
                deviceSensorHashMap=gson.fromJson(mqSaveDataJson,DeviceSensorHashMap.class);
                for(Map.Entry<String,ArrayList<Item>> entry : deviceSensorHashMap.getDevSenHasMap().entrySet()){
                    sectionedExpandableLayoutHelper.addSection(entry.getKey(),entry.getValue());
                    sectionedExpandableLayoutHelper.notifyDataSetChanged();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        //getPostData(SUBSCRIB_TYPE);

        mRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.monitoring_center_content_swipeRefreshLayout);
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
                getPostData(SUBSCRIB_TYPE);

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

//        subscrible();
        Log.d("---------》","在这里开始订阅信息");
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
//        try {
//            client.close();
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private  void getPostData(final int  msgType) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean ok=excuteGetPost(msgType);
            }

            private boolean excuteGetPost(int msgType) {
                sensrosOfDeviceList=new ArrayList<SensorsOfDevice>();
                myUrlConnect=new MyUrlConnect(deviceOfUserUrl,"POST",userPostJson);
                isOk =myUrlConnect.urlConnection();
                if(isOk==true){
                    System.out.println(myUrlConnect.getResposeResult());
                    deviceOfUser=new DeviceOfUser();
                    gson=new Gson();
                    deviceOfUser=gson.fromJson(myUrlConnect.getResposeResult(), DeviceOfUser.class);
                    isok=true;

                    if(isok){
                        for(int i=0;i<deviceOfUser.getDevices().size();i++){
                            isOk=false;
                            isok=false;
                            gson=new Gson();
                            devicePostJson=gson.toJson(new UserDevicePostJson.UserIdDeviceId(""+11023, ""+deviceOfUser.getDevices().get(i).getId()));
                            System.out.println(devicePostJson);

                            myUrlConnect=new MyUrlConnect(sensorsOfDeviceUrl,"POST",devicePostJson);
                            isOk=myUrlConnect.urlConnection();
                            System.out.println(myUrlConnect.getResposeResult());
                            if(isOk==true){
                                sensorsOfDevice=new SensorsOfDevice();
                                gson=new Gson();
                                sensorsOfDevice=gson.fromJson(myUrlConnect.getResposeResult(), SensorsOfDevice.class);

                                sensrosOfDeviceList.add(sensorsOfDevice);
                                isok=true;
                            }
                        }
                        Message message =new Message();
                        message.what=2;
                        message.arg1=msgType;
                        handler.sendMessage(message);
                    }else {
                        Snackbar.make(recyclerView,"请检查网络状态",Snackbar.LENGTH_SHORT).show();
                        Message message =new Message();
                        message.what=1;
                        handler.sendMessage(message);
                    }
                }else {
                    Snackbar.make(recyclerView,"请检查网络状态",Snackbar.LENGTH_SHORT).show();
                    Message message =new Message();
                    message.what=1;
                    handler.sendMessage(message);
                }
                return isok;
            }
        }).start();
    }

    private  void subscrible() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {

//                mqttSubscrible();
//            }

//            private void mqttSubscrible() {

                String clientId="subscriblerlxb";
                String topic="E604RQS8UIK15Y89";
                String server="tcp://www.tlink.io:1883";
                int qos=0;

                try
                {
                    MqttClientPersistence persistence=new MemoryPersistence();
                    client=new MqttClient(server,clientId,persistence);
                    MqttConnectOptions connectOptions=new MqttConnectOptions();
                    connectOptions.setConnectionTimeout(10000);
                    connectOptions.setCleanSession(true);
                    connectOptions.setKeepAliveInterval(5000);
                    client.setCallback(new MqttCallback(){
                        @Override
                        public void connectionLost(Throwable throwable) {
                            System.out.println("链接丢失了，这里从新开始链接,请检查你的网络");
                            try {
                                client.connect();
                            } catch (MqttException e) {
                                e.printStackTrace();
                            }
                        }
                        @Override
                        public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                            System.out.println("订阅的消息到达:"+mqttMessage.toString());
                            Message message=new Message();
                            message.what=3;
                            message.arg1=SUBSCRIB_TYPE;
                            message.obj=mqttMessage.toString();
                            handler.sendMessage(message);

                        }
                        @Override
                        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                            System.out.println("发布的消息到达:");
                        }

                    });

                    client.connect(connectOptions);
                    client.subscribe(topic);
                    System.out.println("正在订阅信息");
                }catch (MqttException e) {
                    e.printStackTrace();
                }
//            }
//        }).start();

    }

}
