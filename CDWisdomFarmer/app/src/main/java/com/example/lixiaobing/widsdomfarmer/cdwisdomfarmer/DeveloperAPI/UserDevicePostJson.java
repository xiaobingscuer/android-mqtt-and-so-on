package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.DeveloperAPI;

public class UserDevicePostJson {
	
	
	public static class UserIdApikey{
		private String  userId;
		private String  apiKey;
		
		public UserIdApikey(String userId, String apiKey) {
			super();
			this.userId = userId;
			this.apiKey = apiKey;
		}
		
		
	}
	

	public static class UserIdDeviceId{
		private String  userId;
		private String  deviceId;
		
		public UserIdDeviceId(String userId, String deviceId) {
			super();
			this.userId = userId;
			this.deviceId = deviceId;
		}
		
	}

}
