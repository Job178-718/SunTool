package com.sun.test1;

import static android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE;

import android.bluetooth.BluetoothGattDescriptor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;


import com.sun.utils.DataStoreUtils;

import java.util.ArrayList;
import java.util.List;


public class Test1Activity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<Item> itemList = new ArrayList<Item>();

    private int specifiedPosition = 3;

    private List<Integer> list = new ArrayList<Integer>();

    private MyAdapter myAdapter;

    int prePosition = -1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        itemList.add(new Item("1"));
        itemList.add(new Item("2"));
        itemList.add(new Item("3"));
        itemList.add(new Item("4"));
        itemList.add(new Item("5"));
        itemList.add(new Item("6"));
        itemList.add(new Item("7"));
        itemList.add(0,new Item("-1"));
        itemList.add(new Item("-1"));
        myAdapter = new MyAdapter(itemList,this);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setItemViewCacheSize(1);
        myAdapter.setMaxFlingVelocity(recyclerView,2000);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        // 创建一个布局完成监听器
        ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // 取消布局完成监听器
                recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                // 获取第一个可见的项的索引
                int firstVisiblePosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                // 滚动到第一个可见项的中间位置
                recyclerView.smoothScrollToPosition(firstVisiblePosition);
            }
        };



        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(globalLayoutListener);

        Log.d("targetSnapPosition", "onCreate: "+DataStoreUtils.Companion.getScreenWidth(this));
        // 在RecyclerView上添加滚动监听器
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    View view = snapHelper.findSnapView(recyclerView.getLayoutManager());
                    //int targetSnapPosition = snapHelper.findTargetSnapPosition(recyclerView.getLayoutManager(), 400,0);
                    //Log.d("targetSnapPosition", "targetSnapPosition:" +targetSnapPosition);
                    // 获取当前位于中间位置的 View
                    View snapView = snapHelper.findSnapView(recyclerView.getLayoutManager());

                    if (snapView != null) {
                        // 获取中间位置项的索引值
                        int centerPosition = recyclerView.getLayoutManager().getPosition(snapView);
                        Log.d("centerPosition", "onScrollStateChanged: "+centerPosition);
                        // 在这里处理中间位置的索引值
                        if(prePosition!=centerPosition){
                            myAdapter.setSelectPosition(centerPosition);
                            prePosition = centerPosition;
                        }

                    }
                }
            }
        });

    }
}
