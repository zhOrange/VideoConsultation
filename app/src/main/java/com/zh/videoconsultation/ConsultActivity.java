package com.zh.videoconsultation;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zh.videoconsultation.utils.DensityUtil;


import androidx.recyclerview.widget.LinearLayoutManager;

import com.zh.videoconsultation.lib.adapter.ScrollPickerAdapter;
import com.zh.videoconsultation.lib.view.ScrollPickerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsultActivity extends AppCompatActivity {
    private ScrollPickerView mScrollPickerViewWeek;
    private ScrollPickerView mScrollPickerViewDuration;

    private Button btnOrder, btnCall;
    private FloatingActionButton fbtnMark;
    private boolean flagMark = true;
    public static final String CONSULT_NAME = "consult_name";
    public static final String CONSULT_IMAGE_ID = "consult_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);



        Intent intent = getIntent();
        String consultName = intent.getStringExtra(CONSULT_NAME);
        int consultID = intent.getIntExtra(CONSULT_IMAGE_ID, 0);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar_consult);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapse_toolbar);
        ImageView consultImage = (ImageView)findViewById(R.id.consult_image_view);
        TextView consultContentText = (TextView)findViewById(R.id.consult_content_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(consultName);
        Glide.with(this).load(consultID).into(consultImage);
        String consultContent = getnerateConsultContent(consultName);
        consultContentText.setText(consultContent);

        btnOrder = findViewById(R.id.btn_order);
        btnCall = findViewById(R.id.btn_call);
        fbtnMark = findViewById(R.id.fbtn_mark);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnOrder.setEnabled(false);
                btnCall.setEnabled(true);
//                Intent intent = new Intent(ConsultActivity.this, ScrollPickerDemoActivity.class);
//                startActivity(intent);

                show1();
                Toast.makeText(ConsultActivity.this, "成功预约 " + consultName + "顾问！", Toast.LENGTH_SHORT).show();

            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ConsultActivity.this, "开始视频咨询", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ConsultActivity.this, ChatActivity.class);
//                show2();
                startActivity(intent);
            }
        });
        fbtnMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = "";
                if(flagMark){
                    text = "成功关注 " + consultName;
                }else{
                    text = "取消关注 " + consultName;
                }
                flagMark = !flagMark;
                Toast.makeText(ConsultActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private String getnerateConsultContent(String name){
        StringBuilder builder = new StringBuilder();
//        for(int i = 0; i < 300;i++){
//            builder.append(name);
//        }
        builder.append("顾问：" + name + "\n\n");
        builder.append("简介：白金投资顾问，具有10年以上证券从业经验，擅长时长情绪、估值、政治周期，判断市场走势\n\n");
        builder.append("可预约时间：每周  09:00~17:00\n");
        return builder.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void show1() {

        Dialog bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_choose_bottom, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);

        bottomDialog.show();
        initView();
        initData();
    }

    private void show2() {
        Dialog bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_content_circle, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) contentView.getLayoutParams();
        params.width = getResources().getDisplayMetrics().widthPixels - DensityUtil.dp2px(ConsultActivity.this, 16f);
        params.bottomMargin = DensityUtil.dp2px(ConsultActivity.this, 8f);
        contentView.setLayoutParams(params);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();
    }

    private void initView() {
        mScrollPickerViewWeek = findViewById(R.id.scroll_picker_view_week_1);
//        mScrollPickerViewWeek = findViewById(R.id.scroll_picker_view_week_1);
        mScrollPickerViewDuration = findViewById(R.id.scroll_picker_view_duration_1);
        if(mScrollPickerViewWeek != null){
            mScrollPickerViewWeek.setLayoutManager(new LinearLayoutManager(this));
        }
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
                                    Toast.makeText(ConsultActivity.this, text, Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(ConsultActivity.this, text, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
        ScrollPickerAdapter mScrollPickerAdapterWeek = weekBuilder.build();
        ScrollPickerAdapter mScrollPickerAdapterDuration = durationBuilder.build();
        mScrollPickerViewWeek.setAdapter(mScrollPickerAdapterWeek);
        mScrollPickerViewDuration.setAdapter(mScrollPickerAdapterDuration);
    }
}