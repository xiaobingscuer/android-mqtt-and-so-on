package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.UserCenter;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixiaobing on 2016/6/7.
 */
public class UserFragment extends Fragment {
    private List<UserCenterItem> userCenterItemList = new ArrayList<UserCenterItem>();
    private ListView userCenterItemListView;
    private UserCenterItemAdapter userCenterItemAdapter;
    private Context context;

    public UserFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserCenterItem user_center_item1 = new UserCenterItem(R.drawable.ic_menu_camera,"个人设置",R.drawable.ic_menu_send);
        UserCenterItem user_center_item2 = new UserCenterItem(R.drawable.ic_menu_camera,"所有联系人",R.drawable.ic_menu_send);
        UserCenterItem user_center_item3 = new UserCenterItem(R.drawable.ic_menu_camera,"添加联系人",R.drawable.ic_menu_send);
        UserCenterItem user_center_item4 = new UserCenterItem(R.drawable.ic_menu_camera,"修改密码",R.drawable.ic_menu_send);
        UserCenterItem user_center_item5 = new UserCenterItem(R.drawable.ic_menu_camera,"短信购买",R.drawable.ic_menu_send);
        UserCenterItem user_center_item6 = new UserCenterItem(R.drawable.ic_menu_camera,"我的存储",R.drawable.ic_menu_send);
        UserCenterItem user_center_item7 = new UserCenterItem(R.drawable.ic_menu_camera,"我的收藏",R.drawable.ic_menu_send);
        UserCenterItem user_center_item8 = new UserCenterItem(R.drawable.ic_menu_camera,"收支明细",R.drawable.ic_menu_send);

        userCenterItemList.add(user_center_item1);
        userCenterItemList.add(user_center_item2);
        userCenterItemList.add(user_center_item3);
        userCenterItemList.add(user_center_item4);
        userCenterItemList.add(user_center_item5);
        userCenterItemList.add(user_center_item6);
        userCenterItemList.add(user_center_item7);
        userCenterItemList.add(user_center_item8);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.content_user_center,container,false);
        userCenterItemListView= (ListView) view.findViewById(R.id.user_center_item_list);
        userCenterItemAdapter=new UserCenterItemAdapter(context,R.layout.user_center_item,userCenterItemList);
        userCenterItemListView.setAdapter(userCenterItemAdapter);
        userCenterItemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserCenterItem userCenterItem = userCenterItemList.get(position);
                Toast toast = Toast.makeText(context,userCenterItem.getInfo(),Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        return view;
    }
}
