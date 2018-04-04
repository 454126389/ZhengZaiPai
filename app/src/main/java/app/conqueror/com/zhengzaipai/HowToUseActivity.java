package app.conqueror.com.zhengzaipai;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.conqueror.com.zhengzaipai.mainfragment.LockDevice.LockDeviceActivity;
import app.conqueror.com.zhengzaipai.mainfragment.MainTabActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.login.LoginSelectActivity;
import app.conqueror.com.zhengzaipai.view.adapter.HowToUsePagerAdapter;
import app.conqueror.com.zhengzaipai.view.listener.OnDotPageChangeListener;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HowToUseActivity extends AppCompatActivity {
    public static final int LAST_PAGE = 3;
    @Bind(R.id.skip_button)
    TextView skipButton;

    @Bind(R.id.done_button)
    TextView doneButton;
    @Bind(R.id.next_page_button)
    ImageView nextPageButton;
    private ViewPager viewPager;

    @Bind({R.id.circle_page1, R.id.circle_page2, R.id.circle_page3, R.id.circle_page4})
    List<ImageView> dots;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_use);
        ButterKnife.bind(this);
        initViewPager();
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.how_to_use_pager);
        HowToUsePagerAdapter pagerAdapter = new HowToUsePagerAdapter(getSupportFragmentManager());
        viewPager.addOnPageChangeListener(new OnDotPageChangeListener(dots, nextPageButton, skipButton, doneButton));
        viewPager.setAdapter(pagerAdapter);
    }

    @OnClick(R.id.next_page_button)
    public void nextPage() {
        int position = viewPager.getCurrentItem();
        viewPager.setCurrentItem(position + 1);
    }

    @OnClick(R.id.skip_button)
    public void skipPages() {
        viewPager.setCurrentItem(LAST_PAGE);
    }

    @OnClick(R.id.done_button)
    public void done() {
        Intent i = new Intent(HowToUseActivity.this, LoginSelectActivity.class);
        startActivity(i);
        finish();
    }
}
