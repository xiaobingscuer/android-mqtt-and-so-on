package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.DeveloperAPI;

import java.util.ArrayList;

public class DeviceOfUser {
	private String flag;
	private ArrayList<Devices> devices;
	private String msg;
	
	public DeviceOfUser() {
		super();
		flag=00+"";
		msg="";
		devices=new ArrayList<Devices>();
		devices.add(new Devices());
	}

	public String getFlag() {
		return flag;
	}

	public ArrayList<Devices> getDevices() {
		return devices;
	}

	public String getMsg() {
		return msg;
	}
	
	public class Devices{
		private String agreement;
		private String createDate;
		private String defaultTimescale;
		private String deviceName;
		private String deviceNo;
		private String faultDelay;
		private int id;
		private int isDelete;
		private int isShare;
		private String lat;
		private String lng;
		private String remark;
		private int sequence;
		private int userId;
		
		public Devices(){
			super();
			this.agreement = "mqtt";
			this.createDate = "2016-06-17 00:00:00";
			this.defaultTimescale = "10";
			this.deviceName = "device of lxb";
			this.deviceNo = "0000";
			this.faultDelay = "10";
			this.id = 1111;
			this.isDelete = 0;
			this.isShare = 1;
			this.lat = 32.0+"";
			this.lng = 106.0+"";
			this.remark = "";
			this.sequence = 0;
			this.userId = 11324;			
		}

		public String getAgreement() {
			return agreement;
		}

		public String getCreateDate() {
			return createDate;
		}

		public String getDefaultTimescale() {
			return defaultTimescale;
		}

		public String getDeviceName() {
			return deviceName;
		}

		public String getDeviceNo() {
			return deviceNo;
		}

		public String getFaultDelay() {
			return faultDelay;
		}

		public int getId() {
			return id;
		}

		public int getIsDelete() {
			return isDelete;
		}

		public int getIsShare() {
			return isShare;
		}

		public String getLat() {
			return lat;
		}

		public String getLng() {
			return lng;
		}

		public String getRemark() {
			return remark;
		}

		public int getSequence() {
			return sequence;
		}

		public int getUserId() {
			return userId;
		}
		
	}
	
	
}
