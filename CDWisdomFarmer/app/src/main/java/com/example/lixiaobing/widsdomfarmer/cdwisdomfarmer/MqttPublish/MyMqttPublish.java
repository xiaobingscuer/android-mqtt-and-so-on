package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.MqttPublish;

import android.util.Log;

import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.DeveloperAPI.SensorDatasJson;
import com.google.gson.Gson;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/25.
 */
public class MyMqttPublish {
    String clientId="publisherlxb";
    String topic="E604RQS8UIK15Y89";
    String server="tcp://www.tlink.io:1883";
    int qos=0;
    String publishString="";
    MqttMessage mqttMessage = new MqttMessage();

    public MyMqttPublish(String topics,int sensorsId,int isSwitch) throws InterruptedException {
        super();
        topic=topics;
        try
        {
            MqttClientPersistence persistence=new MemoryPersistence();
            MqttClient client=new MqttClient(server,clientId,persistence);
            MqttConnectOptions connectOptions=new MqttConnectOptions();
            connectOptions.setConnectionTimeout(10000);
            connectOptions.setCleanSession(true);
            connectOptions.setKeepAliveInterval(5000);
            client.connect(connectOptions);

            ArrayList<Object> sensorListOfDevice=new ArrayList<>();

            SensorDatasJson.SwitchTypeSensor switchTypeSensor=new  SensorDatasJson.SwitchTypeSensor(sensorsId,isSwitch);
            sensorListOfDevice.add(switchTypeSensor);

            SensorDatasJson dataJson=new SensorDatasJson(sensorListOfDevice);
            Gson gson=new Gson();
            publishString=gson.toJson(dataJson,SensorDatasJson.class);

            mqttMessage.setPayload(publishString.getBytes());
            mqttMessage.setQos(qos);
            client.publish(topic,mqttMessage);
            System.out.println("发布消息:"+mqttMessage.toString());

        }catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
