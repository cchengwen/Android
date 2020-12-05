package com.example.constellation.starfrag;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.constellation.R;
import com.example.constellation.bean.StarBean;

import java.util.ArrayList;
import java.util.List;


/**
 * 星座Fragment
 * 包括ViewPager和GridView
 */
public class StarFragment extends Fragment {

    private ViewPager starVp;
    private GridView starGv;
    private LinearLayout pointLayout;
    private List<StarBean.StarinfoBean> mDatas;
    private StarBaseAdapter starBaseAdapter;

    //    声明图片数组
    int[] imgIds = {R.mipmap.pic_guanggao, R.mipmap.pic_star, R.mipmap.jp1, R.mipmap.jp2, R.mipmap.jp3, R.mipmap.jp4, R.mipmap.jp5};
    //    声明ViewPager的数据源
    List<ImageView> ivList;
    //    声明管理指示器小圆点的集合
    List<ImageView> pointLists;
    private StarPageAdapter starPageAdapter;

    //   完成定时装置，实现自动滑动的效果
    Handler handler = new Handler() {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
//                获取当前ViewPager显示的页面
                int currentItem = starVp.getCurrentItem();
//                判断是否为最后一张，如果是最后一张回到第一张，否则显示最后一张
                if (currentItem == ivList.size() - 1) {
                    starVp.setCurrentItem(0);
                } else {
                    currentItem++;
                    starVp.setCurrentItem(currentItem);
                }
//                形成循环发送 -- 接收消息的效果，在接收消息的同时，也要进行消息发送
                handler.sendEmptyMessageDelayed(1, 5000);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("StarFragment <<<========================>>>>>  onCreateView");
        View view = inflater.inflate(R.layout.fragment_star, container, false);

//        初始化控件
        initView(view);
        Bundle bundle = getArguments();
        StarBean infoBean = (StarBean) bundle.getSerializable("info");
//        //  获取关于星座信息的集合数据
        mDatas = infoBean.getStarinfo();

//        创建适配器
        starBaseAdapter = new StarBaseAdapter(getContext(), mDatas);
////        GridView 设置元素的方法
        starGv.setAdapter(starBaseAdapter);

        initPager();
        setVPListener();
        setGVListener();

//        延迟五秒钟发送一条消息，通知可以切换viewpager的图片了
        handler.sendEmptyMessageAtTime(1, 5000);
        return view;
    }

    /**
     * 设置GriView的监听事件函数
     */
    private void setGVListener() {
        starGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                StarBean.StarinfoBean bean = mDatas.get(i);
                Intent intent = new Intent(getContext(), StarAnalysisActivity.class);
                intent.putExtra("star", bean);
                startActivity(intent);
            }
        });
    }

    /**
     * 设置ViewPager显示的页面
     */
    private void initPager() {
        ivList = new ArrayList<>();
        pointLists = new ArrayList<>();
        for (int i = 0; i < imgIds.length; i++) {
            ImageView iv = new ImageView(getContext());
            iv.setImageResource(imgIds[i]);
            //  放大操作
//            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);

//           设置图片view的宽高
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);

//            设置到图片的宽高
            iv.setLayoutParams(lp);

//            将图片view加载到集合中
            ivList.add(iv);

//            创建图片对应的指示器小圆点
            ImageView piv = new ImageView(getContext());
            piv.setImageResource(R.mipmap.point_normal);
            LinearLayout.LayoutParams plp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            plp.setMargins(20, 0, 0, 0);
            piv.setLayoutParams(plp);
//            将小圆点添加 到布局当中
            pointLayout.addView(piv);

//            为了便于管理,将小圆点添加 到统一管理的集合当中
            pointLists.add(piv);
        }
//        默认第一个小圆点是获取焦点的状态
        pointLists.get(0).setImageResource(R.mipmap.point_focus);
//        配置适配器
        starPageAdapter = new StarPageAdapter(getContext(), ivList);
        // 设置ViewPager
        starVp.setAdapter(starPageAdapter);
    }

    /**
     * 设置viewpager的监听函数
     */
    private void setVPListener() {
        starVp.addOnPageChangeListener(new SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < pointLists.size(); i++) {
//                    全部置为不选中
                    pointLists.get(i).setImageResource(R.mipmap.point_normal);
                }
//                根据位置选中
                pointLists.get(position).setImageResource(R.mipmap.point_focus);
            }
        });
    }

    /**
     * 初始化控件操作
     */
    private void initView(View view) {
        System.out.println("StarFragment <<<========================>>>>>  initView");
        starVp = view.findViewById(R.id.starfrag_vp);
        starGv = view.findViewById(R.id.starfrag_gv);
        pointLayout = view.findViewById(R.id.starfrag_layout);
    }

}
