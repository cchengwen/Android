package com.example.constellation.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

    public static String getJSONFromNet(String path) {
        String json = "";
        try {
//            1、将路径转换成url对象
            URL url = new URL(path);
//            2、获取网络连接对象
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            3、开始连接
            connection.connect();
//            4、读取输入流当中的内容
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
//            5、开始读取流
            String str = null;
            StringBuffer sb =new StringBuffer();
            if ((str = br.readLine()) != null){
                sb.append(str);
//                sb.append("\r\n");
            }
            br.close();
            System.out.println("sb == "+sb.toString());
            json = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("请求数据返回："+json);
        return json;
    }
}
