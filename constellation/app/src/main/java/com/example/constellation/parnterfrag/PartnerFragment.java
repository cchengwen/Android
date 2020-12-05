package com.example.constellation.parnterfrag;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.constellation.R;
import com.example.constellation.bean.StarBean;
import com.example.constellation.utils.AssetsUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class PartnerFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private ImageView manIv, womanIv;
    private Spinner manSp, womanSp;
    private Button prizeBtn, analysisBtn;
    private List<StarBean.StarinfoBean> infoList;
    //    存放了星座名称的集合
    List<String> nameList;
    private Map<String, Bitmap> logoImgMap;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_partner, container, false);
//        控件初始化
        initView(view);

//        获取 activity 传递的数据, 此方法可以获得传过来的对象
        Bundle bundle = getArguments();
        StarBean starBean = (StarBean) bundle.getSerializable("info");
        infoList = starBean.getStarinfo();

        nameList = new ArrayList<>();
//        获取适配器所需要的数据源
        for (StarBean.StarinfoBean t : infoList) {
            nameList.add(t.getName());
        }

//        得到数据源后，创建适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.item_parnter_sp, R.id.item_parnter_tv, nameList);

//        设置适配器
        manSp.setAdapter(adapter);
        womanSp.setAdapter(adapter);

//        获取第一个图片资源显示在 imageView上
        String logoname = infoList.get(0).getLogoname();

        /**
         * 因为图上资源已经保存在内存中，所以直接从中取出来
         */
        logoImgMap = AssetsUtils.getContentLogoImgMap();
        Bitmap bitmap = logoImgMap.get(logoname);
        manIv.setImageBitmap(bitmap);
        womanIv.setImageBitmap(bitmap);


        return view;
    }

    /**
     * 初始化控件
     */
    private void initView(View view) {
        manIv = view.findViewById(R.id.parnterfrag_iv_man);
        womanIv = view.findViewById(R.id.parnterfrag_iv_woman);
        manSp = view.findViewById(R.id.parnterfrag_sp_man);
        womanSp = view.findViewById(R.id.parnterfrag_sp_woman);
        prizeBtn = view.findViewById(R.id.parnterfrag_btn_prize);
        analysisBtn = view.findViewById(R.id.parnterfrag_btn_analysis);

//        设置按钮的监听器
        prizeBtn.setOnClickListener(this);
        analysisBtn.setOnClickListener(this);
        manSp.setOnItemSelectedListener(this);
        womanSp.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.parnterfrag_btn_prize:

                break;
            case R.id.parnterfrag_btn_analysis:
//                获取spinner选中的位置
                int manSelPos = manSp.getSelectedItemPosition();
                int womanSelPos = womanSp.getSelectedItemPosition();
//                跳转，传值到星座配对详情界面
                Intent intent = new Intent(getContext(), ParnterAnalysisActivity.class);
                intent.putExtra("man_name", infoList.get(manSelPos).getName());
                intent.putExtra("man_logoname", infoList.get(manSelPos).getLogoname());
                intent.putExtra("woman_name", infoList.get(womanSelPos).getName());
                intent.putExtra("woman_logoname", infoList.get(womanSelPos).getLogoname());
                startActivity(intent);
                break;
        }
    }

    /**
     * @param adapterView 此为下拉点击事件的对象集合
     * @param view
     * @param i           表示被选中的下拉位置
     * @param l
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.parnterfrag_sp_man:
                String logoname = infoList.get(i).getLogoname();
                Bitmap bitmap = logoImgMap.get(logoname);
                manIv.setImageBitmap(bitmap);
                break;
            case R.id.parnterfrag_sp_woman:
                logoname = infoList.get(i).getLogoname();
                bitmap = logoImgMap.get(logoname);
                womanIv.setImageBitmap(bitmap);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
