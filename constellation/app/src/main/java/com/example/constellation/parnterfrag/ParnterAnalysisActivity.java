package com.example.constellation.parnterfrag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.constellation.R;
import com.example.constellation.utils.AssetsUtils;
import com.example.constellation.utils.LoadDataAsyncTask;
import com.example.constellation.utils.URLContent;
import com.google.gson.Gson;

import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ParnterAnalysisActivity extends AppCompatActivity implements LoadDataAsyncTask.OnGetNetDataListener, View.OnClickListener {

    private TextView manTv, womanTv, pdTv, vsTv, pfTv, bzTv, jxTv, zyTv, titleTv;
    private CircleImageView manIv, womanIv;
    private ImageView backIv;
    private String man_name;
    private String man_logoname;
    private String woman_name;
    private String woman_logoname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parnter_analysis);
        // 初始化控件
        initView();
//        接收上一个页面传递的数值
        getLatData();

//        获取网络路径地址
        String parnterURL = URLContent.getParnterURL(man_name, woman_name);

//        加载网络数据
//        1、创造自定义的异步任务的对象
        LoadDataAsyncTask task = new LoadDataAsyncTask(this, this, true);

//        2、执行异常任务
        task.execute(parnterURL);
    }

//    请求数据成功后都会在这个方法中返回数据的，因此在此方法中进行数据处理
    @Override
    public void OnSuccess(String json) {
        if (!TextUtils.isEmpty(json)) {
            ParnterAnalysisBean analysisBean = new Gson().fromJson(json, ParnterAnalysisBean.class);
            ParnterAnalysisBean.ResultBean resultBean = analysisBean.getResult();
            System.out.println(resultBean);
            pfTv.setText("配对评分:"+resultBean.getZhishu()+" "+resultBean.getJieguo());
            bzTv.setText("星座比重:"+resultBean.getBizhong());
            jxTv.setText("解析:\n\n"+resultBean.getLianai());
            zyTv.setText("注意事项:\n\n"+resultBean.getZhuyi());
        }

    }

    private void getLatData() {
        Intent intent = getIntent();
        man_name = intent.getStringExtra("man_name");
        man_logoname = intent.getStringExtra("man_logoname");
        woman_name = intent.getStringExtra("woman_name");
        woman_logoname = intent.getStringExtra("woman_logoname");

//        设置能够显示 的控件的信息
        Map<String, Bitmap> contentLogoImgMap = AssetsUtils.getContentLogoImgMap();
//        设置男士的
        Bitmap manBitmap = contentLogoImgMap.get(man_logoname);
        manIv.setImageBitmap(manBitmap);
//        设置女士的
        Bitmap womanBitmap = contentLogoImgMap.get(woman_logoname);
        womanIv.setImageBitmap(womanBitmap);

        manTv.setText(man_name);
        womanTv.setText(woman_name);
        StringBuilder pdSb = new StringBuilder();
        pdSb.append("星座配对-").append(man_name)
                .append("和").append(woman_name)
                .append("配对");
        pdTv.setText(pdSb.toString());
        vsTv.setText(man_name+" vs "+woman_name);
    }

    private void initView() {
        manIv = findViewById(R.id.parnteranalysis_iv_man);
        womanIv = findViewById(R.id.parnteranalysis_iv_woman);
        backIv = findViewById(R.id.title_iv_back);
        manTv = findViewById(R.id.parnteranalysis_tv_man);
        womanTv = findViewById(R.id.parnteranalysis_tv_woman);
        pdTv = findViewById(R.id.parnteranalysis_tv_pd);
        vsTv = findViewById(R.id.parnteranalysis_tv_vs);
        pfTv = findViewById(R.id.parnteranalysis_tv_pf);
        bzTv = findViewById(R.id.parnteranalysis_tv_bz);
        jxTv = findViewById(R.id.parnteranalysis_tv_jx);
        zyTv = findViewById(R.id.parnteranalysis_tv_zy);
        titleTv = findViewById(R.id.title_tv);

        backIv.setOnClickListener(this);
        titleTv.setText("配对详情");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_iv_back:
                finish();
                break;
        }
    }
}
