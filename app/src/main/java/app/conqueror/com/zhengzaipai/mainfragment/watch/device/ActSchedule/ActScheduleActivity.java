package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActSchedule;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ActScheduleActivity extends BaseActivity {


    @Bind(R.id.title_bar_status)
    View titleBarStatus;
    @Bind(R.id.lib_btn_back)
    ImageButton libBtnBack;
    @Bind(R.id.lib_tv_title)
    TextView libTvTitle;
    @Bind(R.id.title_right_btn)
    Button titleRightBtn;
    @Bind(R.id.title_right_iv)
    ImageView titleRightIv;

    @Bind(R.id.weekNames)
    LinearLayout weekNames;
    @Bind(R.id.sections)
    LinearLayout sections;
    @Bind(R.id.mFreshLayout)
    SwipeRefreshLayout mFreshLayout;


    @Bind({R.id.weekPanel_1, R.id.weekPanel_2, R.id.weekPanel_3, R.id.weekPanel_4,
            R.id.weekPanel_5, R.id.weekPanel_6, R.id.weekPanel_7})
    List<LinearLayout> mWeekViews;

    //
    private int itemHeight;
    private int maxSection = 12;

    @Override
    public int getLayoutId() {
        return R.layout.device_act_schedule2;
    }

    @Override
    public void initView() {

        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        itemHeight = getResources().getDimensionPixelSize(R.dimen.sectionHeight);
        initWeekNameView();
        initSectionView();
        initWeekCourseView();

    }

    @Override
    public void initPresenter() {

    }


    /**
     * 初始化课程表
     */
    private void initWeekCourseView() {
        for (int i = 0; i < mWeekViews.size(); i++) {
            initWeekPanel(mWeekViews.get(i), CourseDao.getCourseData()[i]);
        }
    }




    /**
     * 顶部周一到周日的布局
     **/
    private void initWeekNameView() {
        for (int i = 0; i < mWeekViews.size() + 1; i++) {
            TextView tvWeekName = new TextView(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
            if (i != 0) {
                lp.weight = 1;
                tvWeekName.setText("周" + intToZH(i));
                if (i == getWeekDay()) {
                    tvWeekName.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    tvWeekName.setTextColor(Color.parseColor("#4A4A4A"));
                }
            } else {
                lp.weight = 0.8f;
                tvWeekName.setText(getMonth() + "月");
            }
            tvWeekName.setGravity(Gravity.CENTER_HORIZONTAL);
            tvWeekName.setLayoutParams(lp);
            weekNames.addView(tvWeekName);
        }
    }

    /**
     * 左边节次布局，设定每天最多12节课
     */
    private void initSectionView() {
        for (int i = 1; i <= maxSection; i++) {
            TextView tvSection = new TextView(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, getResources().getDimensionPixelSize(R.dimen.sectionHeight));
            lp.gravity = Gravity.CENTER;
            tvSection.setGravity(Gravity.CENTER);
            tvSection.setText(String.valueOf(i));
            tvSection.setLayoutParams(lp);
            sections.addView(tvSection);
        }
    }

    /**
     * 当前星期
     */
    public int getWeekDay() {
        int w = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
        if (w <= 0) {
            w = 7;
        }
        return w;
    }

    /**
     * 当前月份
     */
    public int getMonth() {
        int w = Calendar.getInstance().get(Calendar.MONTH) + 1;
        return w;
    }

    /**
     * 每次刷新前清除每个LinearLayout上的课程view
     */
    private void clearChildView() {
        for (int i = 0; i < mWeekViews.size(); i++) {
            if (mWeekViews.get(i) != null)
                if (mWeekViews.get(i).getChildCount() > 0)
                    mWeekViews.get(i).removeAllViews();
        }
    }


    public void initWeekPanel(LinearLayout ll, List<CourseModel> data) {

        if (ll == null || data == null || data.size() < 1)
            return;
        CourseModel firstCourse = data.get(0);
        for (int i = 0; i < data.size(); i++) {
            final CourseModel courseModel = data.get(i);

            if (courseModel.getSection() == 0 || courseModel.getSectionSpan() == 0)
                return;
            FrameLayout frameLayout = new FrameLayout(this);

            CornerTextView tv = new CornerTextView(this,
                    ColorUtils.getCourseBgColor(courseModel.getCourseFlag()),
                    dip2px(this, 3));
            LinearLayout.LayoutParams frameLp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    itemHeight * courseModel.getSectionSpan());
            LinearLayout.LayoutParams tvLp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);

            if (i == 0) {
                frameLp.setMargins(0, (courseModel.getSection() - 1) * itemHeight, 0, 0);
            } else {
                frameLp.setMargins(0, (courseModel.getSection() - (firstCourse.getSection() + firstCourse.getSectionSpan())) * itemHeight, 0, 0);
            }
            tv.setLayoutParams(tvLp);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(12);
            tv.setTextColor(Color.parseColor("#FFFFFF"));
            tv.setText(courseModel.getCourseName() + "\n @" + courseModel.getClassRoom());

            frameLayout.setLayoutParams(frameLp);
            frameLayout.addView(tv);
            frameLayout.setPadding(2, 2, 2, 2);
            ll.addView(frameLayout);
            firstCourse = courseModel;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showToast(courseModel.getCourseName());
                }
            });
        }
    }

    /**
     * Toast
     */
    private void showToast(String msg) {
        if (TextUtils.isEmpty(msg))
            return;
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 数字转换中文
     */
    public static String intToZH(int i) {
        String[] zh = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String[] unit = {"", "十", "百", "千", "万", "十", "百", "千", "亿", "十"};

        String str = "";
        StringBuffer sb = new StringBuffer(String.valueOf(i));
        sb = sb.reverse();
        int r = 0;
        int l = 0;
        for (int j = 0; j < sb.length(); j++) {
            r = Integer.valueOf(sb.substring(j, j + 1));
            if (j != 0)
                l = Integer.valueOf(sb.substring(j - 1, j));
            if (j == 0) {
                if (r != 0 || sb.length() == 1)
                    str = zh[r];
                continue;
            }
            if (j == 1 || j == 2 || j == 3 || j == 5 || j == 6 || j == 7 || j == 9) {
                if (r != 0)
                    str = zh[r] + unit[j] + str;
                else if (l != 0)
                    str = zh[r] + str;
                continue;
            }
            if (j == 4 || j == 8) {
                str = unit[j] + str;
                if ((l != 0 && r == 0) || r != 0)
                    str = zh[r] + str;
                continue;
            }
        }
        if (str.equals("七"))
            str = "日";
        return str;
    }



}
