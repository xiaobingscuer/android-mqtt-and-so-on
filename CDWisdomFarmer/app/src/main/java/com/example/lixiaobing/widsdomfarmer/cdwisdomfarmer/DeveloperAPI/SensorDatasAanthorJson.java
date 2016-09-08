package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.DeveloperAPI;

import java.util.ArrayList;

public class SensorDatasAanthorJson {
	private ArrayList<SensorDataJsonM> sensorDatas;

	public SensorDatasAanthorJson() {
		sensorDatas=new ArrayList<SensorDataJsonM>();
	}

	public ArrayList<SensorDataJsonM> getSensorDatas() {
		return sensorDatas;
	}


public class SensorDataJsonM{
	private int sensorsId;
	private  float value;
	private float lat;
	private float lng;
	private int switcher;
	private String imgHex;
	
	public SensorDataJsonM() {
		super();
	}

	public int getSensorsId() {
		return sensorsId;
	}

	public float getValue() {
		return value;
	}

	public float getLat() {
		return lat;
	}

	public float getLng() {
		return lng;
	}

	public int getSwitcher() {
		return switcher;
	}

	public String getImgHex() {
		return imgHex;
	}
} 
	
}
