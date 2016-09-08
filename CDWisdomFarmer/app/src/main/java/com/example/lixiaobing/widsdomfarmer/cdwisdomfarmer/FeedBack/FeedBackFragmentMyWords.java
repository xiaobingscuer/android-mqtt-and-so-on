package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.FeedBack;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.R;

/**
 * Created by lixiaobing on 2016/6/3.
 */
public class FeedBackFragmentMyWords extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feed_back_fragment_mywords, container, false);
        return view;
    }
}
