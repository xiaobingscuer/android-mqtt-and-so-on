package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Device;

/**
 * Created by lixiaobing on 2016/4/28.
 */
public class SensorDatas {
    private int sensorsId;
    private float value;

    public SensorDatas() {
        super();
        this.sensorsId = 16712;
        this.value = 12.0f;
    }

    public SensorDatas(int sensorsId, float value) {
        this.sensorsId = sensorsId;
        this.value = value;
    }

    public int getSensorsId() {
        return sensorsId;
    }

    public void setSensorsId(int sensorsId) {
        this.sensorsId = sensorsId;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

}
