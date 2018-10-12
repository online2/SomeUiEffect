package com.mrchao.ui.waterview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.mrchao.ui.R;

import java.util.List;

/**
 * Created by  Linfc on 2018/10/10.
 * desc：
 */
public class WaterViewAdapter extends AbsWaterViewAdapter<Water> {


    public WaterViewAdapter(Context context, List<Water> data) {
        super(R.layout.ac_water_item, context, data);
    }


    @Override
    protected void convert(View view, Water water, int position) {
        TextView tv_water = getView(view, R.id.tv_water);
        tv_water.setText(position+"");
        getView(view,R.id.tv_water).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWaterView().disMissViewAnim(view);
            }
        });
    }


}
