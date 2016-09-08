package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Trigger;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.R;

import java.util.zip.Inflater;

/**
 * Created by lixiaobing on 2016/6/7.
 */
public class TriggerFragment extends Fragment {
    public TriggerFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.content_trigger,container,false);
        return view;
    }
}
