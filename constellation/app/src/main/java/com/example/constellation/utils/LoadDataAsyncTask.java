package com.example.constellation.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * @Author WEN
 * @date 2020/6/21
 * <p>
 * AsyncTask 的参数：
 * 参数1： String类型的接口，即网址
 * 参数2：进度
 * 参数3：表示返回来的数据
 */
public class LoadDataAsyncTask extends AsyncTask<String, Void, String> {
    /**
     * 表示与AsyncTask运行的Acvity的上下文对象
     */
    private Context context;
    private OnGetNetDataListener listener;
    private ProgressDialog dialog;
    private boolean isShowDailog = false;

    //    初始化
    private void initDialog() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("提示信息");
        dialog.setMessage("正在加载中......");
    }

    //    通过构造方法进行传递
    public LoadDataAsyncTask(Context context, OnGetNetDataListener listener, boolean isShowDailog) {
        this.context = context;
        this.listener = listener;
        this.isShowDailog = isShowDailog;
        initDialog();
    }

    /**
     * 回调接口，获取网络数据的接口
     */
    public interface OnGetNetDataListener {
        void OnSuccess(String json);
    }

    //    运行在主线程当中，通常在此方法中进行控件的初始化
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (isShowDailog) {
            dialog.show();
        }
    }

    //    运行在子线程当中，可以在此处进行耗时操作等逻辑（比如：获取网络请求）
    @Override
    protected String doInBackground(String... params) {
        String json = HttpUtils.getJSONFromNet(params[0]);
        return json;
    }

    //    运行在主线程当中，可以在此处得到doInBackground方法返回的数据，在此处通常进行控件的更新
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

//        如果展示加载，在此取消加载提示
        if (isShowDailog) {
            dialog.dismiss();
        }
        System.out.println("返回的数据："+s);
//       回调方法，返回数据
        listener.OnSuccess(s);
    }
}
