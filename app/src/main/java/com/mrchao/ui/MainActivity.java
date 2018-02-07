package com.mrchao.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mrchao.ui.base.BaseActivity;

import java.util.ArrayList;


public class MainActivity extends BaseActivity {
    private ArrayList<String> listItem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    private void initData() {
        listItem.add("平行空间引导页效果");
    }
}
