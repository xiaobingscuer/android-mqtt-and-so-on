package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.UserCenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.R;

import java.util.List;

/**
 * Created by lixiaobing on 2016/5/19.
 */
public class UserCenterItemAdapter extends ArrayAdapter<UserCenterItem> {

   private int viewResourceId;

    public UserCenterItemAdapter(Context context, int resourceId, List<UserCenterItem> objects){

        super(context,resourceId,objects);
        viewResourceId = resourceId;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        UserCenterItem user = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(viewResourceId,null);
        ImageView leftImage = (ImageView)view.findViewById(R.id.user_center_item_leftImage);
        ImageView rightImage = (ImageView)view.findViewById(R.id.user_center_item_rightImage);
        TextView textView = (TextView)view.findViewById(R.id.user_center_item_text);
        leftImage.setImageResource(user.getLeftImageId());
        rightImage.setImageResource(user.getRightImage());
        textView.setText(user.getInfo());
        return view;
    }
}
