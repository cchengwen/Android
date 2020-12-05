package com.example.constellation.starfrag;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class StarPageAdapter extends PagerAdapter {

    private Context context;
    private List<ImageView> imgList;

    public StarPageAdapter(Context context, List<ImageView> imgList) {
        this.context = context;
        this.imgList = imgList;
    }

    @Override
    public int getCount() {
        return imgList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


    /**
     * 以下两个方法需手动重写
     * nstantiateItem(@NonNull ViewGroup container, int position),
     * destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object)
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        加载的时候去获取View对象
        ImageView imageView = imgList.get(position);
//        加载
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        获取需要销毁的View对象
        ImageView imageView = imgList.get(position);
        container.removeView(imageView);
    }
}
