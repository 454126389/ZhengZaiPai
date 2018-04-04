package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActRemoteCamera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import app.conqueror.com.zhengzaipai.R;
import butterknife.Bind;
import butterknife.ButterKnife;


public class ImageScaleActivity extends Activity {
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
    @Bind(R.id.lib_tv_right)
    TextView libTvRight;
    @Bind(R.id.lib_bottom_line)
    View libBottomLine;
    @Bind(R.id.iv_image)
    ImageView ivImage;
    private Bitmap bitmap;
    private String filepath;
    private static final boolean defaultValue = false;
    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);
//        image = (ImageView) findViewById(R.id.iv_image);


        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        filepath = intent.getStringExtra("filePath");
        Picasso.with(this).load(filepath).into(ivImage);

    }


}