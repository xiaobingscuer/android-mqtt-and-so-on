package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.ConnectWithUs;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.R;

/**
 * Created by lixiaobing on 2016/6/7.
 */
public class ConnectWithUsFragment extends BackHandledFragment {
    private ProgressWebView mWebView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.connect_with_us_fragment, null);
        mWebView = (ProgressWebView) view.findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("http://www.cdwanjiang.com");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public String getTagText() {
        return null;
    }

    @Override
    public boolean onBackPressed() {
        if(mWebView.canGoBack()){
            mWebView.goBack();
            return true;
        }

        return false;
    }
}
