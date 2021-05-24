package com.zh.videoconsultation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Consult[] consults = {new Consult("李雷", R.drawable.lilei), new Consult("韩梅梅", R.drawable.hanmeimei),
            new Consult("静香", R.drawable.jingxiang), new Consult("大雄", R.drawable.daxiong),
            new Consult("胖虎", R.drawable.panghu), new Consult("小夫", R.drawable.xiaofu)};

    private List<Consult> consultList = new ArrayList<>();
    private ConsultAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.aquamarine);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshConsults();
            }
        });

        initConsults();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        adapter = new ConsultAdapter(consultList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void initConsults(){
        for(int i = 0; i < 60; i++){
            Random random = new Random();
            int index = random.nextInt(consults.length);
            consultList.add(consults[index]);
        }
    }
    private void refreshConsults(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        consultList.clear();
                        initConsults();
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
}