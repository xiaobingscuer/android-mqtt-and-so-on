package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Device.Device;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.MonitoringCenter.MonitoringCenterActivity;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.MonitoringCenter.MonitoringCenterFragment;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.R;
import com.google.gson.Gson;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by lixiaobing on 2016/5/9.
 */
public class SubscribService extends Service  {

    private final String Server="tcp://www.tlink.io:1883";
    private final String Topic = "E604RQS8UIK15Y89";
    private final String topic[]={"E604RQS8UIK15Y89","GXO0OZB2M78C5GL8","6PNC70VAMA2LGVF6"};
    private String ClientId;
    private int qos = 0;
    private MqttClient client;
    private MqttConnectOptions conOptions;
    private MemoryPersistence persistence;
    private final  static int SUBSCRIB_TYPE=(int)'S';

    private NotificationManager notificationManager;
    private Notification.Builder builder;
    private Notification notification;
    private Intent intent;
    PendingIntent pendingIntent;

    public SubscribService() {
        //ClientId=String.valueOf(new Random(System.currentTimeMillis()).nextInt());
        ClientId=""+System.currentTimeMillis();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        ///// notification
        notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setTicker("mqtt warrning notification");
        builder.setWhen(System.currentTimeMillis());
        builder.setDefaults(Notification.DEFAULT_ALL);
        intent = new Intent(this,MonitoringCenterActivity.class);
        pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        /////mqtt
        persistence = new MemoryPersistence();
        conOptions = new MqttConnectOptions();
        conOptions.setCleanSession(true);
        conOptions.setConnectionTimeout(10*1000);
        conOptions.setKeepAliveInterval(5*1000);
        Log.d("MSubscribService------>", "onCreate executed");
        try {
            client = new MqttClient(Server, ClientId,persistence);
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable throwable) {
                    // 连接丢失后，一般在这里面进行重连
                    System.out.println("链接丢失了，这里从新开始链接,请检查你的网络");
                    try {
                        client.connect();
                    } catch (MqttException e) {
                        e.printStackTrace();
                        Log.d(" service - callback-->","重新连接订阅失败");
                    }
                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                    System.out.println("订阅的消息到达:"+mqttMessage.toString());
                    Message message=new Message();
                    message.what=3;
                    message.arg1=SUBSCRIB_TYPE;
                    message.obj=mqttMessage.toString();
                    MonitoringCenterFragment.handler.sendMessage(message);

                    builder.setContentTitle("智慧农夫"); // 消息标题
                    builder.setContentText("订阅的消息更新"); //消息内容
                    builder.setContentIntent(pendingIntent);
                    notification = builder.build();
                    notificationManager.notify((int)(Math.random()*20),notification);
                }
                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                    System.out.println("发布的消息到达:");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if(!client.isConnected()) {
                        client.connect();
                        for(int i=0;i<topic.length;i++){
                            client.subscribe(topic[i], qos);
                        }
                      //  client.subscribe(Topic, qos);
                    }
                } catch (MqttException e) {
                    e.printStackTrace();

                }
                Log.d("MyService---------->", "onStartCommand executed");
                //stopSelf();
            }
        }).start();
        return START_STICKY;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        try {
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
        Log.d("service-------------->","onDestory");
    }

}
