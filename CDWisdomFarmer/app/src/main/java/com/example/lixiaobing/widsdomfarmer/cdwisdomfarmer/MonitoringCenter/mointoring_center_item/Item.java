package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.MonitoringCenter.mointoring_center_item;

import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Time.CurrentTime;

/**
 * Created by bpncool on 2/23/2016.
 */
public class Item {

    private String deviceId;
    private String deviceName;
    private String deviceNo;
    private String id;
    private String iocUrl;
    private int isLine;
    private String lastUpdateDate;
    private String currentTimeIs;
    private String sensorName;
    private int sensorTypeId;
    private String unit;

    private float data;
    private int isSwitch=1;
    private String imagHex;
    private float lat;
    private float lng;

    public Item() {
        CurrentTime currentTime=new CurrentTime();
        currentTimeIs=currentTime.toString();
    }

    public Item(String id, String sensorName, int isLine, String lastUpdateDate) {
        this.id = id;
        this.sensorName = sensorName;
        this.isLine = isLine;
        this.lastUpdateDate = lastUpdateDate;

    }

    public float getData() {
        return data;
    }

    public void setData(float data) {
        this.data = data;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagHex() {
        return imagHex;
    }

    public void setImagHex(String imagHex) {
        this.imagHex = imagHex;
    }

    public String getIocUrl() {
        return iocUrl;
    }

    public void setIocUrl(String iocUrl) {
        this.iocUrl = iocUrl;
    }

    public int getIsLine() {
        return isLine;
    }

    public void setIsLine(int isLine) {
        this.isLine = isLine;
    }

    public int getIsSwitch() {
        return isSwitch;
    }

    public void setIsSwitch(int isSwitch) {
        this.isSwitch = isSwitch;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public int getSensorTypeId() {
        return sensorTypeId;
    }

    public void setSensorTypeId(int sensorTypeId) {
        this.sensorTypeId = sensorTypeId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getCurrentTimeIs() {
        return currentTimeIs;
    }
}
