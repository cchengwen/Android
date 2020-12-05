package com.example.constellation.starfrag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.constellation.R;

import java.util.List;

public class AnalysisBaseAdapter extends BaseAdapter {

    private Context context;
    private List<StarAnalysisBean> mDatas;

    public AnalysisBaseAdapter(Context context, List<StarAnalysisBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder =null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_star_analysis, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        StarAnalysisBean bean = mDatas.get(i);
        holder.titleTv.setText(bean.getTitle());
        holder.contentTv.setText(bean.getContent());
        holder.contentTv.setBackgroundResource(bean.getColor());



        return view;
    }

    class ViewHolder{
        TextView titleTv, contentTv;

        public ViewHolder(View view){
            titleTv = view.findViewById(R.id.itemstar_tv_title);
            contentTv = view.findViewById(R.id.itemstar_tv_content);
        }
    }
}
