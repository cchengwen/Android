package com.example.constellation.luckfrag;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.constellation.R;
import com.example.constellation.bean.StarBean;
import com.example.constellation.utils.AssetsUtils;

import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @Author WEN
 * @date 2020/6/23
 */
public class LuckBaseAdapter extends BaseAdapter {

    private final Map<String, Bitmap> contentLogoImgMap;
    private Context context;
    List<StarBean.StarinfoBean> mData;

    public LuckBaseAdapter(Context context, List<StarBean.StarinfoBean> mData) {
        this.context = context;
        this.mData = mData;
        contentLogoImgMap = AssetsUtils.getContentLogoImgMap();
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
            view = LayoutInflater.from(context).inflate(R.layout.item_luck_gv, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
           holder = (ViewHolder) view.getTag();
        }

//        获取指定位置的数据
        StarBean.StarinfoBean bean = mData.get(i);
        System.out.println("adapter--->>> bean --->>>>"+bean);
        holder.starTv.setText(bean.getName());
//        通过名称，获取存储在内存当中的图片
        Bitmap bitmap = contentLogoImgMap.get(bean.getLogoname());
        System.out.println("图片 --->>"+bitmap);
        holder.starIv.setImageBitmap(bitmap);
        return view;
    }

//    定义内部类进行初始化
    class ViewHolder{
        private CircleImageView starIv;
        private TextView starTv;

        public ViewHolder(View view){
            starIv = view.findViewById(R.id.item_luck_iv);
            starTv = view.findViewById(R.id.item_luck_tv);
        }

}
}
