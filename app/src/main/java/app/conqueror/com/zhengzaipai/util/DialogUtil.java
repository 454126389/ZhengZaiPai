package app.conqueror.com.zhengzaipai.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Html;

import app.conqueror.com.zhengzaipai.R;

/**
 * Created by Administrator on 2017/7/19.
 */

public class DialogUtil {
    private static ProgressDialog mProgressDialog;
    public static void initDialog(Context context) {
        mProgressDialog = new ProgressDialog(context);
// 设置mProgressDialog风格
        mProgressDialog.setProgress(ProgressDialog.STYLE_SPINNER);//圆形
        mProgressDialog.setProgress(ProgressDialog.STYLE_HORIZONTAL);//水平
// 设置mProgressDialog标题
        mProgressDialog.setTitle("提示");
// 设置mProgressDialog提示
        mProgressDialog.setMessage("正在发起请求");
// 设置mProgressDialog进度条的图标
        mProgressDialog.setIcon(R.mipmap.ic_launcher);
// 设置mProgressDialog的进度条是否不明确
//不滚动时，当前值在最小和最大值之间移动，一般在进行一些无法确定操作时间的任务时作为提示，明确时就是根据你的进度可以设置现在的进度值
        mProgressDialog.setIndeterminate(false);
//mProgressDialog.setProgress(m_count++);
// 是否可以按回退键取消
        mProgressDialog.setCancelable(false);
// 设置mProgressDialog的一个Button
//        mProgressDialog.setButton("关闭", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
    }
    public static void showDialog() {
        if(mProgressDialog!=null)
            mProgressDialog.show();
    }
    public static void hideDialog() {
        if(mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }


    public static AlertDialog.Builder getConfirmDialog(Context context, String message, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = getDialog(context);
        builder.setMessage(Html.fromHtml(message));
        builder.setPositiveButton("确定", onClickListener);
        builder.setNegativeButton("取消", null);
        return builder;
    }

    public static AlertDialog.Builder getDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        return builder;
    }

}
