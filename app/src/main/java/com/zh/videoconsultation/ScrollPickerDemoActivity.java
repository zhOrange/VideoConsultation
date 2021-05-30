package com.zh.videoconsultation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import top.defaults.view.PickerView;

public class ScrollPickerDemoActivity extends AppCompatActivity {

    private  PickerView pickerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_picker_demo);

        PickerView.Adapter adapter = new PickerView.Adapter() {

            @Override
            public int getItemCount() {
                return 42;
            }

            @Override
            public PickerView.PickerItem getItem(int index) {
                return null;
            }

            @Override
            public String getText(int index) {
                return "Item " + index;
            }
        };
        pickerView = findViewById(R.id.pickerView);
        pickerView.setAdapter(adapter);
    }


}