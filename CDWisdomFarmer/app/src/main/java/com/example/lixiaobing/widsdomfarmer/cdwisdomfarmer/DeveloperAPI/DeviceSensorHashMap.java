package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.DeveloperAPI;

import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.MonitoringCenter.mointoring_center_item.Item;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lixiaobing on 2016/6/23.
 */
public class DeviceSensorHashMap {
    private HashMap<String,ArrayList<Item>> devSenHasMap;

    public DeviceSensorHashMap() {
        devSenHasMap=new HashMap<>();
    }

    public DeviceSensorHashMap(HashMap<String, ArrayList<Item>> devSenHasMap) {
        this.devSenHasMap = devSenHasMap;
    }

    public HashMap<String, ArrayList<Item>> getDevSenHasMap() {
        return devSenHasMap;
    }
}
