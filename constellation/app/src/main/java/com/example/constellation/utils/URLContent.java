package com.example.constellation.utils;
import java.net.URLEncoder;

public class URLContent {
    /**
     * 配对的key
     */
    private static String PD_KEY = "1cff5b4b59a71c502cbcfc8b57438082";
    /**
     * 配对url
     */
    private static String PD_URL = "http://apis.juhe.cn/xzpd/query?men=PD_MEN_XZ&women=PD_WOMEN_XZ&key=PD_KEY";

    private static String LUCK_URL = "http://web.juhe.cn:8080/constellation/getAll?consName=XZNAME&type=year&key=LUCK_KEY";

    /**
     *  运势的 Key
     */
    private static String LUCK_KEY = "73195297e0707eebbd9fea623e47c388";

    /**
     * 星座配对接口
     * @param men  男星座名称
     * @param woman  女星座名称
     * @return  url路径
     */
    public static String getParnterURL(String men, String woman) {
        men = men.replace("座", "");
        woman = woman.replace("座", "");

        try {
//            字符转化
            men = URLEncoder.encode(men, "UTF-8");
            woman = URLEncoder.encode(woman, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = PD_URL.replace("PD_MEN_XZ", men).replace("PD_WOMEN_XZ", woman).replace("PD_KEY", PD_KEY);
        System.out.println("配对 --->>> 请求url："+url);
        return url;
    }

    /**
     * 运势url
     * @param name  星座名称
     * @return  路径 url
     */
    public static String getLuckURL(String name)  {
        try {
            name = URLEncoder.encode(name, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
       String url =  LUCK_URL.replace("XZNAME", name).replace("LUCK_KEY", LUCK_KEY);
        System.out.println("运势 --->>>> 请求url："+url);
        return url;
    }
}
