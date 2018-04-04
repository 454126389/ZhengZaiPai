package app.conqueror.com.zhengzaipai.mainfragment.onway;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.view.pop.PopupMenuUtil;
import app.conqueror.com.zhengzaipai.view.viewpagerindicator.TabPageIndicator;



//在路上
public class OnWayActivity  extends BaseActivity
{
    private static final String[] CONTENT = new String[] { "推荐", "热点", "视频" };
    private List<Fragment> list=new ArrayList<Fragment>();

    private long exitTime = 0;


    @Override
    public int getLayoutId() {
        return R.layout.activity_onway;
    }

    @Override
    public void initView() {




        FragmentPagerAdapter adapter = new GoogleMusicAdapter(getSupportFragmentManager());

        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(pager);
    }

    @Override
    public void initPresenter() {

    }

    class GoogleMusicAdapter extends FragmentPagerAdapter {
        public GoogleMusicAdapter(FragmentManager fm) {
            super(fm);
            list.add(new GCFragment());
            list.add(new TestFragment());
            list.add(new TestFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length].toUpperCase();
        }

        @Override
        public int getCount() {
            return CONTENT.length;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            // 当popupWindow 正在展示的时候 按下返回键 关闭popupWindow 否则关闭activity
            if (PopupMenuUtil.getInstance()._isShowing()) {
                PopupMenuUtil.getInstance()._rlClickAction();
            }
            // 当popupWindow 正在展示的时候 按下返回键 关闭popupWindow 否则关闭activity
            else if ((System.currentTimeMillis() - exitTime) > 2000) {
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
