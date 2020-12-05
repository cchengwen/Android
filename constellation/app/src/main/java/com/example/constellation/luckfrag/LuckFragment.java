package com.example.constellation.luckfrag;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.constellation.R;
import com.example.constellation.bean.StarBean;

import java.io.Serializable;
import java.util.List;

/**
 * 运势界面
 */
public class LuckFragment extends Fragment {

    private GridView luckGv;
    private List<StarBean.StarinfoBean> mData;
    private LuckBaseAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_luck, container, false);

        luckGv = view.findViewById(R.id.luckfrag_gv);

//        获取数据源
        Bundle bundle = getArguments();
        StarBean info = (StarBean) bundle.getSerializable("info");
        mData = info.getStarinfo();

//        创建适配器
        adapter = new LuckBaseAdapter(getContext(), mData);

//        设置适配器
        luckGv.setAdapter(adapter);
        
//        设置GridView 每一项的监听事件
        setListener();

        return view;
    }

    /* 创建监听事件 */
    private void setListener() {
        luckGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//               拿到此位置i对应的对象
                StarBean.StarinfoBean bean = mData.get(i);
                String name = bean.getName();
                Intent intent = new Intent(getContext(), LuckAnalysisActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }

}
