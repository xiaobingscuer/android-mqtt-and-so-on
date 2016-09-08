package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.DeveloperAPI;

import java.util.ArrayList;

public class SensorsOfDevice {
	private String flag;
	private String msg;
	private Device device;
	
	public SensorsOfDevice() {
		super();
		
	}

	public String getFlag() {
		return flag;
	}

	public String getMsg() {
		return msg;
	}

	public Device getDevice() {
		return device;
	}
	
	
	public static class Device{
		private String agreement;
		private CreateDate createDate;
		private String defaultTimescale;
		private String deviceName;
		private String deviceNo;
		private String faultDelay;
		private String id;
		private String iocUrl;
		private String isDelete;
		private String isShare;
		private String lat;
		private String lng;
		private String remark;
		private String sequence;
		private String userId;
		private ArrayList<SensorsList> sensorsList;
		
		public Device() {
			super();
		}

		public String getAgreement() {
			return agreement;
		}

		public CreateDate getCreateDate() {
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

		public String getId() {
			return id;
		}

		public String getIocUrl() {
			return iocUrl;
		}

		public String getIsDelete() {
			return isDelete;
		}

		public String getIsShare() {
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

		public String getSequence() {
			return sequence;
		}

		public String getUserId() {
			return userId;
		}

		public ArrayList<SensorsList> getSensorsList() {
			return sensorsList;
		} 
		
	}
	
	
	public static class SensorsList{
		private CreateDate createDate;
		private String decimalPlacse;
		private String defaultTimescale;
		private String deviceId;
		private String deviceName;
		private String id;
		private String iocUrl;
		private int isDelete;
		private int isLine;
		private int isOpen;
		private String lastUpdateDate;
		private String sensorName;
		private int sensorTypeId;
		private String switcher;
		private String tag;
		private String unit;
		
		public SensorsList() {
			super();
		}

		public CreateDate getCreateDate() {
			return createDate;
		}

		public String getDecimalPlacse() {
			return decimalPlacse;
		}

		public String getDefaultTimescale() {
			return defaultTimescale;
		}

		public String getDeviceId() {
			return deviceId;
		}

		public String getDeviceName() {
			return deviceName;
		}

		public String getId() {
			return id;
		}

		public String getIocUrl() {
			return iocUrl;
		}

		public int getIsDelete() {
			return isDelete;
		}

		public int getIsLine() {
			return isLine;
		}

		public int getIsOpen() {
			return isOpen;
		}

		public String getLastUpdateDate() {
			return lastUpdateDate;
		}

		public String getSensorName() {
			return sensorName;
		}

		public int getSensorTypeId() {
			return sensorTypeId;
		}

		public String getSwitcher() {
			return switcher;
		}

		public String getTag() {
			return tag;
		}

		public String getUnit() {
			return unit;
		}
		
	}
	
	
	public static class CreateDate{
		private int date;
		private int	day;
		private int	hours;
		private int	minutes;
		private int	month;
		private int	nanos;
		private int	seconds;
		private long time;
		private int	timezoneOffset;
		private int	year;
		
		public CreateDate() {
			super();
		}

		public int getDate() {
			return date;
		}

		public int getDay() {
			return day;
		}

		public int getHours() {
			return hours;
		}

		public int getMinutes() {
			return minutes;
		}

		public int getMonth() {
			return month;
		}

		public int getNanos() {
			return nanos;
		}

		public int getSeconds() {
			return seconds;
		}

		public long getTime() {
			return time;
		}

		public int getTimezoneOffset() {
			return timezoneOffset;
		}

		public int getYear() {
			return year;
		}
	
	}
	
}
