package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.MonitoringCenter.monitoring_center_adapters;

/**
 * Created by bpncool on 2/23/2016.
 */
public class Section {

    private final String name;

    public boolean isExpanded;

    public Section(String name) {
        this.name = name;
        isExpanded = true;
    }

    public String getName() {
        return name;
    }
}
