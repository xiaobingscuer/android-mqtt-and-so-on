package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.UserCenter;

/**
 * Created by lixiaobing on 2016/5/19.
 */
public class UserCenterItem {

    private int leftImageId;
    private String info;
    private int rightImageId;

    public UserCenterItem(int leftImageId, String info, int rightImageId) {
        this.leftImageId = leftImageId;
        this.info = info;
        this.rightImageId = rightImageId;
    }

    public int getLeftImageId() {
        return leftImageId;
    }

    public void setLeftImageId(int leftImageId) {
        this.leftImageId = leftImageId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getRightImage() {
        return rightImageId;
    }

    public void setRightImage(int rightImageId) {
        this.rightImageId = rightImageId;
    }

}
