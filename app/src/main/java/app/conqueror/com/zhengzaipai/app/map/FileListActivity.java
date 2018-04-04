package app.conqueror.com.zhengzaipai.app.map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.R;

import static app.conqueror.com.zhengzaipai.util.MyUitls.checkIsImageFile;
import static app.conqueror.com.zhengzaipai.util.MyUitls.checkIsSoundFile;
import static app.conqueror.com.zhengzaipai.util.MyUitls.checkIsVideoFile;
import static app.conqueror.com.zhengzaipai.util.MyUitls.getAudioFileIntent;
import static app.conqueror.com.zhengzaipai.util.MyUitls.getImageFileIntent;
import static app.conqueror.com.zhengzaipai.util.MyUitls.getVideoFileIntent;


public class FileListActivity extends Activity {

    private ArrayAdapter<String> adapter;
    private ListView mShowPathLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filelist);


        Bundle bundle = this.getIntent().getExtras();
        //接收name值
        String type = bundle.getString("type");

        mShowPathLv = (ListView) findViewById(R.id.lv_show_path);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1);

        List<String> filelist =getPathFromSD(type);


        adapter.addAll(filelist);
        mShowPathLv.setAdapter(adapter);
        mShowPathLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(type.equals("image")){
                    Intent it = getImageFileIntent(filelist.get(position));
                    startActivity( it );
//                    shareMsg("activityTitle","msgTitle","msgText",filelist.get(position));
//                    showImg(BitmapFactory.decodeFile(filelist.get(position)));
//                    startActivity(new Intent(FileListActivity.this, SendToWXActivity.class));
//                    finish();

                }else if(type.equals("video"))
                {
                    Intent it = getVideoFileIntent(filelist.get(position));
                    startActivity( it );

                }else if(type.equals("sound"))
                {
                    Intent it = getAudioFileIntent(filelist.get(position));
                    startActivity( it );

                }
            }
        });
    }

    /**
     * 从sd卡获取图片资源
     * @return
     */
    private List<String> getPathFromSD(String type) {
        // 图片列表
        List<String> PathList = new ArrayList<String>();
        // 得到sd卡内image文件夹的路径   File.separator(/)
        String filePath = Environment.getExternalStorageDirectory().toString() + File.separator
                + "cache";
        // 得到该路径文件夹下所有的文件
        File fileAll = new File(filePath);
        File[] files = fileAll.listFiles();
        // 将所有的文件存入ArrayList中,并过滤所有图片格式的文件
        for (int i = 0; i < files.length; i++) {
            File file = files[i];

            if(type.equals("image"))
            {
                if (checkIsImageFile(file.getPath())) {
                    PathList.add(file.getPath());
                }
            }
            else if(type.equals("video"))
            {
                if (checkIsVideoFile(file.getPath())) {
                    PathList.add(file.getPath());
                }
            } else if(type.equals("sound"))
            {
                if (checkIsSoundFile(file.getPath())) {
                    PathList.add(file.getPath());
                }
            }

        }
        // 返回得到的图片列表
        return PathList;
    }



    /**
     * 分享功能
     *
     * @param
     *            <a href="https://www.baidu.com/s?wd=%E4%B8%8A%E4%B8%8B%E6%96%87&tn=44039180_cpr&fenlei=mv6quAkxTZn0IZRqIHckPjm4nH00T1Y3rynduyubPHTLnHK-n1010ZwV5Hcvrjm3rH6sPfKWUMw85HfYnjn4nH6sgvPsT6KdThsqpZwYTjCEQLGCpyw9Uz4Bmy-bIi4WUvYETgN-TLwGUv3EnHm4nHndnjbknj0znH6vrHRsr0" target="_blank" class="baidu-highlight">上下文</a>
     * @param activityTitle
     *            Activity的名字
     * @param msgTitle
     *            消息标题
     * @param msgText
     *            消息内容
     * @param imgPath
     *            图片路径，不分享图片则传null
     */
    public void shareMsg(String activityTitle, String msgTitle, String msgText,
                         String imgPath) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (imgPath == null || imgPath.equals("")) {
            intent.setType("text/plain"); // 纯文本
        } else {
            File f = new File(imgPath);
            if (f != null && f.exists() && f.isFile()) {
                intent.setType("image/jpg");
                Uri u = Uri.fromFile(f);
                intent.putExtra(Intent.EXTRA_STREAM, u);
            }
        }
        intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
        intent.putExtra(Intent.EXTRA_TEXT, msgText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, activityTitle));
    }



}