package com.example.constellation.luckfrag;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.constellation.R;

import java.util.List;

/**
 * @Author WEN
 * @date 2020/6/30
 */
public class LuckAnalysisAdapter extends BaseAdapter {
    private Context context;
    private List<LuckItemBean> mData;

    public LuckAnalysisAdapter(Context context, List<LuckItemBean> mData) {
        System.out.println("LuckAnalysisAdapter  <<<<<<<  适配器   >>>>>>>");
        this.context = context;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (null == view) {
            view = LayoutInflater.from(context).inflate(R.layout.item_luckanalysis_lv, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
//        得到指定位置的数据
        LuckItemBean itemBean = mData.get(i);
        System.out.println("运势："+itemBean);
        holder.titleTv.setText(itemBean.getTitle());
        holder.contentTv.setText(itemBean.getContent());

//        改变TtextViewr 背景颜色
        GradientDrawable drawable = (GradientDrawable) holder.titleTv.getBackground();
        drawable.setColor(itemBean.getColorId());
        return view;
    }

    class ViewHolder{
        private TextView titleTv, contentTv;

        public ViewHolder(View view){
            titleTv = view.findViewById(R.id.item_luckanalysis_tv_title);
            contentTv = view.findViewById(R.id.item_luckanalysis_tv_content);
        }
    }
}
