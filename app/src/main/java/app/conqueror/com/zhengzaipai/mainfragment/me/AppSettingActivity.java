package app.conqueror.com.zhengzaipai.mainfragment.me;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;

//import com.leon.lib.settingview.LSettingItem;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.util.DataCleanManager;
import app.conqueror.com.zhengzaipai.util.DialogUtil;
import butterknife.Bind;

public class AppSettingActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initPresenter() {

    }


//    private final int CLEAN_SUC=1001;
//    private final int CLEAN_FAIL=1002;
//
//    @Bind(R.id.set_languaga)
//    LSettingItem setLanguaga;
//    @Bind(R.id.set_clear_save)
//    LSettingItem setClearSave;
//    @Bind(R.id.set_about)
//    LSettingItem setAbout;
//    @Bind(R.id.set_help)
//    LSettingItem setHelp;
//
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_setting_app;
//    }
//
//    @Override
//    public void initView() {
//
//
//
//        final String items[]={getString(R.string.languaga_cn),getString(R.string.languaga_cntw),getString(R.string.languaga_english)};
//        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器
////        builder.setTitle("xu"); //设置标题
////        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
//        builder.setSingleChoiceItems(items,0,new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //dialog.dismiss();
////                Toast.makeText(AppSettingActivity.this, items[which], Toast.LENGTH_SHORT).show();
//            }
//        });
//        builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
////                Toast.makeText(AppSettingActivity.this, "确定", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//        setLanguaga.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
//            @Override
//            public void click(boolean isChecked) {
////                Toast.makeText(getApplicationContext(), "我的消息", Toast.LENGTH_SHORT).show();
//                builder.create().show();
//            }
//        });
//
//        setClearSave.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
//            @Override
//            public void click(boolean isChecked) {
//                DialogUtil.getConfirmDialog(AppSettingActivity.this, "是否清空缓存?", new DialogInterface.OnClickListener
//                        () {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        DataCleanManager.clearAllCache(AppSettingActivity.this);
//                        setClearSave.setRightText("0KB");
//                    }
//                }).show();
//            }
//        });
//
//        setAbout.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
//            @Override
//            public void click(boolean isChecked) {
//                mContext.startActivity(new Intent(mContext, HelpWebActivity.class));
//            }
//        });
//
//    }
//
//
//
//    private Handler handler = new Handler() {
//        public void handleMessage(android.os.Message msg) {
//            switch (msg.what) {
//                case CLEAN_FAIL:
////                    ToastUtils.show(SxApplication.getInstance(),"清除失败");
//                    App.showText("清除失败");
//                    break;
//                case CLEAN_SUC:
//                    App.showText("清除成功");
//                    break;
//            }
//        };
//    };
//
//
//    @Override
//    public void initPresenter() {
//
//    }
//
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        try {
//            setClearSave.setRightText(DataCleanManager.caculateCacheSize(AppSettingActivity.this));
//        } catch (Exception e) {
//            setClearSave.setRightText("计算失败");
//            e.printStackTrace();
//        }
//    }
}
