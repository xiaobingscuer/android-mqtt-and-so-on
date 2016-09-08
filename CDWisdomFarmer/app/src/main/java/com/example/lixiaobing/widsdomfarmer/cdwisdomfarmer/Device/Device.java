package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Device;

import java.util.List;

/**
 * Created by lixiaobing on 2016/4/28.
 */
public class Device {

    private List<SensorDatas> sensorDatas  ;

    public Device() {
        super();
    }


    public Device(List<SensorDatas> sensorDatas) {
        this.sensorDatas = sensorDatas;
    }

    public List<SensorDatas> getSensorDatas() {
        return sensorDatas;
    }

    public void setSensorDatas(List<SensorDatas> sensorDatas) {
        this.sensorDatas = sensorDatas;
    }

}
