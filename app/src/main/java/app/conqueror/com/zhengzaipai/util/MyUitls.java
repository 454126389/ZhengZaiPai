package app.conqueror.com.zhengzaipai.util;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.conqueror.com.zhengzaipai.R;
import okhttp3.ResponseBody;


/**
 * Created by Administrator on 2017/6/8.
 */

public class MyUitls {
    public static Intent getImageFileIntent(String param ) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param ));
        intent.setDataAndType(uri, "image/*");
        return intent;
    }

    //android获取一个用于打开视频文件的intent
    public static Intent getVideoFileIntent(String param ) {
        Intent intent = new Intent("android.intent.action.VIEW");
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(new File(param ));
        intent.setDataAndType(uri, "video/*");
        return intent;
    }


    //android获取一个用于打开音频文件的intent
    public static Intent getAudioFileIntent(String param ) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(new File(param ));
        intent.setDataAndType(uri, "audio/*");
        return intent;
    }


    //下载文件
    public static boolean Download(ResponseBody body, String fileName) {

        try {
//            File targetFile = new File(fileName);
//            targetFile.createNewFile();//生成文件
//            File sd = Environment.getExternalStorageDirectory();
//            boolean can_write = sd.canWrite();
//            if(!can_write)
//                Log.d("DownloadImage", "can not write");

            Log.d("DownloadImage", "Reading and writing file");
            InputStream in = null;
            FileOutputStream out = null;
            try {
                in = body.byteStream();
                out = new FileOutputStream(fileName);
                int c;
                final int BUFFER_SIZE = 2048;
                final int EOF = -1;

                byte[] buf = new byte[BUFFER_SIZE];

                while (true) {
                    c = in.read(buf);
                    if (c == EOF)
                        break;

                    out.write(buf, 0, c);
                }
            }
            catch (IOException e) {
                Log.d("DownloadImage",e.toString());
                return false;
            }
            finally {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }

            return true;

        } catch (IOException e) {
            Log.d("DownloadImage",e.toString());
            return false;
        }
    }



    /**
     * 检查扩展名，得到图片格式的文件
     * @param fName  文件名
     * @return
     */
    @SuppressLint("DefaultLocale")
    public static boolean checkIsImageFile(String fName) {
        boolean isImageFile = false;
        // 获取扩展名
        String FileEnd = fName.substring(fName.lastIndexOf(".") + 1,
                fName.length()).toLowerCase();
        if (FileEnd.equals("jpg") || FileEnd.equals("png") || FileEnd.equals("gif")
                || FileEnd.equals("jpeg")|| FileEnd.equals("bmp") ) {
            isImageFile = true;
        } else {
            isImageFile = false;
        }
        return isImageFile;
    }

    /**
     * 检查扩展名，得到图片格式的文件
     * @param fName  文件名
     * @return
     */
    @SuppressLint("DefaultLocale")
    public static boolean checkIsVideoFile(String fName) {
        boolean isImageFile = false;
        // 获取扩展名
        String FileEnd = fName.substring(fName.lastIndexOf(".") + 1,
                fName.length()).toLowerCase();
        if (FileEnd.equals("MP4")||FileEnd.equals("mp4")  ) {
            isImageFile = true;
        } else {
            isImageFile = false;
        }
        return isImageFile;
    }

    /**
     * 检查扩展名，得到图片格式的文件
     * @param fName  文件名
     * @return
     */
    @SuppressLint("DefaultLocale")
    public static boolean checkIsSoundFile(String fName) {
        boolean isImageFile = false;
        // 获取扩展名
        String FileEnd = fName.substring(fName.lastIndexOf(".") + 1,
                fName.length()).toLowerCase();
        if (FileEnd.equals("amr")||FileEnd.equals("amr")  ) {
            isImageFile = true;
        } else {
            isImageFile = false;
        }
        return isImageFile;
    }


    public static ProgressDialog initDialog(Context context) {
        ProgressDialog mProgressDialog = new ProgressDialog(context);
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

        return mProgressDialog;
    }

    /*
      * 将时间转换为时间戳
      */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        ts=ts/1000;
        res = String.valueOf(ts);
        return res;
    }
    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
