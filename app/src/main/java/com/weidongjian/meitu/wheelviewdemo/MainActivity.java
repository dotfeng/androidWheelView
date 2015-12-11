package com.weidongjian.meitu.wheelviewdemo;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.weidongjian.meitu.wheelviewdemo.view.OnItemSelectedListener;
import com.weidongjian.meitu.wheelviewdemo.view.LoopView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    protected TextView tv_data;

    protected LoopView ctm_lv_data;

    private PopupWindow popWindow;

    ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_data = (TextView) findViewById(R.id.tv_data);
        tv_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWheel(v);
            }
        });

        for (int i = 0; i < 35; i++) {
            data.add("" + i);
        }
    }

    private void showPopupWheel(View parent) {
        if (popWindow == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.layout_wheelview, null);
            popWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    true);
            initPop(view);
        }

        int current;
        current = data.indexOf(tv_data.getText().toString());
		ctm_lv_data.setInitPosition(current);

        popWindow.showAtLocation(parent, Gravity.CENTER_HORIZONTAL
                | Gravity.BOTTOM, 0, 0);
    }

    private void initPop(View view) {
        popWindow.setAnimationStyle(android.R.style.Animation_InputMethod);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tv_done = (TextView) view.findViewById(R.id.tv_done);
		ctm_lv_data = (LoopView) view.findViewById(R.id.ctm_lv_data);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dismiss();
            }
        });

        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDoneClick();
                popWindow.dismiss();
            }
        });

//		ctm_lv_data.setNotLoop();
        ctm_lv_data.setItemsVisible(7);
		ctm_lv_data.setItems(data);
		ctm_lv_data.setTextSize(30);

//        ctm_lv_data.setListener(new OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(int index) {
//                tv_data.setText(data.get(index));
//            }
//        });
    }

    private void onDoneClick() {
		String current = data.get(ctm_lv_data.getSelectedItem());
        tv_data.setText(current);
    }
}
