package com.example.constellation.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.constellation.MainActivity;
import com.example.constellation.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导界面
 */
public class GuideActivity extends AppCompatActivity {
    
    private ViewPager guideVp;
//    存放三张图片的地址
    private int[] resIds = {R.mipmap.loading1, R.mipmap.loading2, R.mipmap.loading3};
    private List<ImageView> imageViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        
        guideVp = findViewById(R.id.guide_vp);
        imageViewList = new ArrayList<>();
        initPager();

//        为第三个图片设置监听事件
        setListener();
    }


    private void setListener() {
        int size = imageViewList.size();
        ImageView imageView = imageViewList.get(size - 1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /* 初始化数据并设置适配器 */
    private void initPager() {
        for (int i = 0; i < resIds.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(resIds[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(lp);
            imageViewList.add(imageView);
        }

//        创建适配器
        GuideAdapter adapter = new GuideAdapter(imageViewList);
//        设置适配器
        guideVp.setAdapter(adapter);
    }
}
