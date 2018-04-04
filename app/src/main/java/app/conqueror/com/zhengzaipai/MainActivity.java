package app.conqueror.com.zhengzaipai;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import app.conqueror.com.zhengzaipai.mainfragment.LockDevice.LockDeviceActivity;
import app.conqueror.com.zhengzaipai.app.SetFragment.SettingActivity;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import butterknife.Bind;



public class MainActivity extends BaseActivity {

    @Bind(R.id.roll_view_pager)
    RollPagerView rollViewPager;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

//        int a=1/0;

        rollViewPager = (RollPagerView) findViewById(R.id.roll_view_pager);
        rollViewPager.setAnimationDurtion(500);   //设置切换时间
        rollViewPager.setAdapter(new TestLoopAdapter(rollViewPager)); //设置适配器
        rollViewPager.setHintView(new ColorPointHintView(this, Color.WHITE, Color.GRAY));// 设置圆点指示器颜色

        bar_right.setVisibility(View.VISIBLE);
        bar_right.setText("设置");
        bar_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recorderSetIntent = new Intent(MainActivity.this, SettingActivity.class);
                //用Bundle携带数据
                Bundle recorderSetBundle = new Bundle();
                //传递name参数为tinyphp
                recorderSetBundle.putString("settype", "appset");
                recorderSetIntent.putExtras(recorderSetBundle);
                startActivity(recorderSetIntent);
            }
        });
    }

    @Override
    public void initPresenter() {

    }
    private class TestLoopAdapter extends LoopPagerAdapter {
        private int[] imgs = {R.mipmap.a, R.mipmap.b, R.mipmap.c};  // 本地图片
        private int count = imgs.length;  // banner上图片的数量

        public TestLoopAdapter(RollPagerView viewPager) {
            super(viewPager);
        }

        @Override
        public View getView(ViewGroup container, int position) {
            final int picNo = position + 1;
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            view.setOnClickListener(new View.OnClickListener()      // 点击事件
            {
                @Override
                public void onClick(View v) {
                    App.showText("点击了第" + picNo + "张图片");
                }

            });

            return view;
        }

        @Override
        public int getRealCount() {
            return count;
        }

    }

    public void trun_activity(View view)
    {

        switch (view.getTag().toString())
        {
            case "lockdevice":
//                startActivity(new Intent(MainActivity.this, LockDeviceActivity.class));
                Intent deviceintent = new Intent();
                deviceintent.setClass(MainActivity.this,LockDeviceActivity.class);
                deviceintent.putExtra("type","lockdevice");
                startActivity(deviceintent);
                break;
            case "lockcard":
                Intent cardintent = new Intent();
                cardintent.setClass(MainActivity.this,LockDeviceActivity.class);
                cardintent.putExtra("type","lockcard");
                startActivity(cardintent);
//                startActivity(new Intent(MainActivity.this, LockCardActivity.class));
                break;
            case "shop":
//                Intent shopIntent =new Intent(MainActivity.this,WebActivity.class);
//                //用Bundle携带数据
//                Bundle shopBundle=new Bundle();
//                //传递name参数为tinyphp
//                shopBundle.putString("url", "https://conqueror.tmall.com/");
//                shopIntent.putExtras(shopBundle);
//                startActivity(shopIntent);
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,WebActivity.class);
                intent.putExtra("url","https://conqueror.m.tmall.com/?");
                startActivity(intent);
                break;
            case "home":
                Intent homeIntent =new Intent(MainActivity.this,WebActivity.class);
                //用Bundle携带数据
                Bundle homeBundle=new Bundle();
                //传递name参数为tinyphp
                homeBundle.putString("url", "http://new.conqueror.cn/");
                homeIntent.putExtras(homeBundle);
                startActivity(homeIntent);
                break;
            case "help":
                Intent helpIntent =new Intent(MainActivity.this,WebActivity.class);
                //用Bundle携带数据
                Bundle helpBundle=new Bundle();
                //传递name参数为tinyphp
                helpBundle.putString("url", "http://61.131.68.156:7123/TZ/toIndex.do");
                helpIntent.putExtras(helpBundle);
                startActivity(helpIntent);
                break;
            case "about":
//                Intent aboutIntent =new Intent(MainActivity.this,WebActivity.class);
//                //用Bundle携带数据
//                Bundle aboutBundle=new Bundle();
//                //传递name参数为tinyphp
//                aboutBundle.putString("url", "http://sim.conqueror.cn/CiLearn/public/images/about.png");
//                aboutIntent.putExtras(aboutBundle);
//                startActivity(aboutIntent);
                Intent settingIntent = new Intent(MainActivity.this, SettingActivity.class);
                //用Bundle携带数据
                Bundle settingBundle = new Bundle();
                //传递name参数为tinyphp
                settingBundle.putString("settype", "about");
                settingIntent.putExtras(settingBundle);
                startActivity(settingIntent);

                break;
        }
    }

    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
