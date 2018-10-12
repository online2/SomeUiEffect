package com.mrchao.ui.waterview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mrchao.ui.R;

import java.util.ArrayList;
import java.util.List;

public class WaterViewActivity extends AppCompatActivity {

    private WaterView mWaterView;

    private List<Water> mWaters = new ArrayList<>();
    private WaterViewAdapter mAdapter;

    private void initData() {
        mWaters.clear();
        for (int i = 0; i < 15; i++) {
            mWaters.add(new Water((int) (i + Math.random() * 4), "item" + i));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_water);
        initData();
        mWaterView = (WaterView) findViewById(R.id.wv_water);
        mAdapter = new WaterViewAdapter(this, mWaters);
        mWaterView.setAdapter(mAdapter);
    }

    public void onRest(View view) {
        initData();
        mWaterView.notifyDataSetChanged();
//        Intent intent = new Intent(this, ParallaxHeaderActivity.class);
//        startActivity(intent);
    }
}
