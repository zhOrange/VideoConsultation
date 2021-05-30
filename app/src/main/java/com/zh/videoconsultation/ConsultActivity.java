package com.zh.videoconsultation;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ConsultActivity extends AppCompatActivity {

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
                Intent intent = new Intent(ConsultActivity.this, ScrollPickerDemoActivity.class);
                startActivity(intent);
                Toast.makeText(ConsultActivity.this, "成功预约 " + consultName + "顾问！", Toast.LENGTH_SHORT).show();

            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ConsultActivity.this, "开始视频咨询", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ConsultActivity.this, ChatActivity.class);
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
}