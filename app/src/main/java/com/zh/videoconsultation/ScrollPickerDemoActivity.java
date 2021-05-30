package com.zh.videoconsultation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zh.videoconsultation.lib.adapter.ScrollPickerAdapter;
import com.zh.videoconsultation.lib.view.ScrollPickerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ScrollPickerDemoActivity extends AppCompatActivity {
    private ScrollPickerView mScrollPickerViewWeek;
    private ScrollPickerView mScrollPickerViewDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_picker_demo);
        initView();
        initData();
    }

    private void initView() {
        mScrollPickerViewWeek = findViewById(R.id.scroll_picker_view_week);
        mScrollPickerViewDuration = findViewById(R.id.scroll_picker_view_duration);
        mScrollPickerViewWeek.setLayoutManager(new LinearLayoutManager(this));
        mScrollPickerViewDuration.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {
        List<String> weekList, durationList;
        String[] weeks = {"周一", "周二","周三","周四","周五","周六","周日"};
        String[] durations =   {"08:00 ~ 08.30", "08:30 ~ 09.00",
                                "09:00 ~ 09.30", "09:30 ~ 10.00",
                                "10:00 ~ 10.30", "10:30 ~ 11.00",
                                "11:00 ~ 11.30", "11:30 ~ 12.00",
                                "12:00 ~ 12.30", "12:30 ~ 13.00",
                                "13:00 ~ 13.30", "13:30 ~ 14.00",
                                "14:00 ~ 14.30", "14:30 ~ 15.00",
                                "05:00 ~ 15.30", "15:30 ~ 16.00",
                                "16:00 ~ 16.30", "16:30 ~ 17.00",
                                "17:00 ~ 17.30", "17:30 ~ 18.00"};
        weekList = Arrays.asList(weeks);
        durationList = Arrays.asList(durations);
//        for (int i = 0; i < 20; i++) {
//            String itemData = "item: " + i;
//            weekList.add(itemData);
//        }

        ScrollPickerAdapter.ScrollPickerAdapterBuilder<String> weekBuilder =
                new ScrollPickerAdapter.ScrollPickerAdapterBuilder<String>(this)
                        .setDataList(weekList)
                        .selectedItemOffset(1)
                        .visibleItemNumber(3)
                        .setDivideLineColor("#E5E5E5")
                        .setItemViewProvider(null)
                        .setOnClickListener(new ScrollPickerAdapter.OnClickListener() {
                            @Override
                            public void onSelectedItemClicked(View v) {
                                String text = (String) v.getTag();
                                if (text != null) {
                                    Toast.makeText(ScrollPickerDemoActivity.this, text, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
        ScrollPickerAdapter.ScrollPickerAdapterBuilder<String> durationBuilder =
                new ScrollPickerAdapter.ScrollPickerAdapterBuilder<String>(this)
                        .setDataList(durationList)
                        .selectedItemOffset(1)
                        .visibleItemNumber(3)
                        .setDivideLineColor("#E5E5E5")
                        .setItemViewProvider(null)
                        .setOnClickListener(new ScrollPickerAdapter.OnClickListener() {
                            @Override
                            public void onSelectedItemClicked(View v) {
                                String text = (String) v.getTag();
                                if (text != null) {
                                    Toast.makeText(ScrollPickerDemoActivity.this, text, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
        ScrollPickerAdapter mScrollPickerAdapterWeek = weekBuilder.build();
        ScrollPickerAdapter mScrollPickerAdapterDuration = durationBuilder.build();
        mScrollPickerViewWeek.setAdapter(mScrollPickerAdapterWeek);
        mScrollPickerViewDuration.setAdapter(mScrollPickerAdapterDuration);
    }
}