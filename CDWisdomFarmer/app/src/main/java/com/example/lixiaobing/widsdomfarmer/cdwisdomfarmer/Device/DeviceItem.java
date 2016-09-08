package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Device;

/**
 * Created by lixiaobing on 2016/6/3.
 */
public class DeviceItem {
    private String deviceName;
    private int  deviceId;
    private String creatTime;

    public DeviceItem(String creatTime, int deviceId, String deviceName) {
        this.creatTime = creatTime;
        this.deviceId = deviceId;
        this.deviceName = deviceName;
    }

    public DeviceItem(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceName() {
        return deviceName;
    }
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public int getDeviceId() {
        return deviceId;
    }
}
