package com.example.constellation.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.constellation.bean.StarBean;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *读取Assets文件夹当中数据的工具类
 * */
public class AssetsUtils {

    /* 读入内存，为了能够便于选中，选择Map形式进行读取，做一个强引用，因为项目不退出，此图片也不会退出 */
    private static Map<String, Bitmap> logoImgMap;
    private static Map<String, Bitmap> contentLogoImgMap;

    /* 读取assets文件夹当中的内容， 存入到字符串当中*/
    public static String getJsonFromAssets(Context context, String fileName) {
        System.out.println("1 <<<<=============>>>> " + fileName);
//        1、获取assets文件夹管理器
        AssetManager am = context.getResources().getAssets();
//        存放到内存流当中
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
//            2、打开文件,获取输入流
            InputStream is = am.open(fileName);
//            读取内容存放到内存流当中
            int hasRead = 0;
            byte[] buf = new byte[1024];
            while (true) {
                hasRead = is.read(buf);
                if (hasRead == -1) break;
//                写入到内存流当中
                baos.write(buf, 0, hasRead);
            }
//            读完以后，将数据转为String
            String msg = baos.toString();
            is.close();
            System.out.println("json数据 ======> " + msg);
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* 读取Assets文件夹下的图片，返回Bitmap对象 */
    public static Bitmap getBitmapFromAssets(Context context, String fileName) {
        Bitmap bitmap = null;
//        获取文件夹管理者
        AssetManager am = context.getResources().getAssets();
        try {
//            打开文件,获取输入流
            InputStream is = am.open(fileName);
//            通过位图管理器,将输入流转换成位图对象
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /*
     * @des  将 Assets文件夹当中的图片一起读取，放置到内存当中，便于管理
     *
     * */
    public static void saveBitmapFromAssets(Context context, StarBean starInfoBean) {
        logoImgMap = new HashMap<>();
        contentLogoImgMap = new HashMap<>();
        List<StarBean.StarinfoBean> starList = starInfoBean.getStarinfo();
        for (int i = 0; i < starList.size(); i++) {
            String logoname = starList.get(i).getLogoname();
            String fileName = "xzlogo/" + logoname + ".png";
            System.out.println(i + " = " + fileName);

            Bitmap logoBm = getBitmapFromAssets(context, fileName);
            logoImgMap.put(logoname, logoBm);

            String contentName = "xzcontentlogo/" + logoname + ".png";
            System.out.println(i + "=" + contentName);

            Bitmap bitmap = getBitmapFromAssets(context, contentName);
            contentLogoImgMap.put(logoname, bitmap);
        }
    }

    public static Map<String, Bitmap> getLogoImgMap() {
        return logoImgMap;
    }

    public static Map<String, Bitmap> getContentLogoImgMap() {
        return contentLogoImgMap;
    }
}
