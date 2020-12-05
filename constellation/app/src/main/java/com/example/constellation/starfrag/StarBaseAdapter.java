package com.example.constellation.starfrag;

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

public class StarBaseAdapter extends BaseAdapter {

    private Context context;
    private List<StarBean.StarinfoBean> mDatas;
    private Map<String, Bitmap> logoMap;

    public StarBaseAdapter(Context context, List<StarBean.StarinfoBean> mDatas) {
        System.out.println("StarBaseAdapter  <<<<<=========================>>>  StarBaseAdapter");
        this.context = context;
        this.mDatas = mDatas;
        this.logoMap = AssetsUtils.getLogoImgMap();
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
        System.out.println("StarBaseAdapter  <<<<<=========================>>>  getView");
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_star_gv, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
//        获取指定位置的数据
        StarBean.StarinfoBean bean = mDatas.get(i);
        holder.tv.setText(bean.getName());

//      获得图片名称，根据名称在内存当中进行查找
        String logoname = bean.getLogoname();
        Bitmap bitmap = logoMap.get(logoname);
        holder.iv.setImageBitmap(bitmap);
        return view;
    }

    //    对于item当中的控件进行声明和初始化的操作
    class ViewHolder {
        private CircleImageView iv;
        private TextView tv;

        public ViewHolder(View view) {
            iv = view.findViewById(R.id.item_star_iv);
            tv = view.findViewById(R.id.item_star_tv);
        }
    }
}
