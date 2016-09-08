package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.DeveloperAPI;

import java.util.ArrayList;

public class SensorDatasJson {
	private ArrayList<Object> sensorDatas;
	
	public SensorDatasJson() {
		super();
		sensorDatas=new ArrayList<Object>();
		sensorDatas.add(new NumTypeSensor());
		sensorDatas.add(new SwitchTypeSensor());
	}
	
	public SensorDatasJson(ArrayList<Object> sensorDatas) {
		super();
		this.sensorDatas = sensorDatas;
	}


	public ArrayList<Object> getSensorDatas() {
		return sensorDatas;
	}


	public static class NumTypeSensor{
		private int sensorsId;
		private float value;
		
		public NumTypeSensor() {
			super();
			sensorsId=16712;
			value=15.0f;
		}

		public NumTypeSensor(int sensorsId, float value) {
			super();
			this.sensorsId = sensorsId;
			this.value = value;
		}

		public int getSensorsId() {
			return sensorsId;
		}

		public float getValue() {
			return value;
		}

		public void setSensorsId(int sensorsId) {
			this.sensorsId = sensorsId;
		}

		public void setValue(float value) {
			this.value = value;
		}
		
	}
	
	
	public static class PositionTypeSensor{
		private int sensorsId;
		private float lat;
		private float lng;
		
		public PositionTypeSensor() {
			super();
			sensorsId=1;
			lat=0.0f;
			lng=0.0f;
		}

		public PositionTypeSensor(int sensorsId, float lat, float lng) {
			super();
			this.sensorsId = sensorsId;
			this.lat = lat;
			this.lng = lng;
		}

		public int getSensorsId() {
			return sensorsId;
		}

		public float getLat() {
			return lat;
		}

		public float getLng() {
			return lng;
		}

		public void setSensorsId(int sensorsId) {
			this.sensorsId = sensorsId;
		}

		public void setLat(float lat) {
			this.lat = lat;
		}

		public void setLng(float lng) {
			this.lng = lng;
		}		
		
	}
	
	
	public static class SwitchTypeSensor{
		private int sensorsId;
		private int switcher;
		
		public SwitchTypeSensor() {
			super();
			sensorsId=21388;
			switcher=1;
		}

		public SwitchTypeSensor(int sensorsId, int switcher) {
			super();
			this.sensorsId = sensorsId;
			this.switcher = switcher;
		}

		public int getSensorsId() {
			return sensorsId;
		}

		public int getSwitcher() {
			return switcher;
		}

		public void setSensorsId(int sensorsId) {
			this.sensorsId = sensorsId;
		}

		public void setSwitcher(int switcher) {
			this.switcher = switcher;
		}
		
	}
	
	
	public static class ImageTypeSensor{
		private int sensorsId;
		private String imgHex;
		
		public ImageTypeSensor() {
			super();
			sensorsId=21393;
			imgHex="";
		}

		public ImageTypeSensor(int sensorsId, String imgHex) {
			super();
			this.sensorsId = sensorsId;
			this.imgHex = imgHex;
		}

		public int getSensorsId() {
			return sensorsId;
		}

		public String getImgHex() {
			return imgHex;
		}

		public void setSensorsId(int sensorsId) {
			this.sensorsId = sensorsId;
		}

		public void setImgHex(String imgHex) {
			this.imgHex = imgHex;
		}
		
	}
}


