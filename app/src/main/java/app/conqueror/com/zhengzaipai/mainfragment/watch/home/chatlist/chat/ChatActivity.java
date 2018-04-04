package app.conqueror.com.zhengzaipai.mainfragment.watch.home.chatlist.chat;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ChatMsg;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItem;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItemQuickAdapter;
import app.conqueror.com.zhengzaipai.util.Actions;
import app.conqueror.com.zhengzaipai.util.AudioRecoderUtils;
import app.conqueror.com.zhengzaipai.util.NetWorkUtil;
import app.conqueror.com.zhengzaipai.util.PopupWindowFactory;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import app.conqueror.com.zhengzaipai.util.TimeUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ChatActivity extends BaseActivity<ChatPresenter, ChatModel> implements ChatContract.View, View.OnClickListener, CompoundButton.OnCheckedChangeListener {


    int choise;
    List<MultipleItem> msglist = new ArrayList<>();
    MultipleItemQuickAdapter multipleItemAdapter;

    List<ChatMsg> chatMsgList;


    static final int VOICE_REQUEST_CODE = 66;
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
    @Bind(R.id.icon)
    ImageView icon;
    @Bind(R.id.tv_tip)
    TextView tvTip;
    @Bind(R.id.tv_tip1)
    TextView tvTip1;
    @Bind(R.id.rl_empty_chat)
    RelativeLayout rlEmptyChat;
    @Bind(R.id.googleProgress)
    ProgressBar googleProgress;
    @Bind(R.id.swipe_target)
    RecyclerView swipeTarget;
    @Bind(R.id.refresh_layout)
    SwipeToLoadLayout refreshLayout;
    @Bind(R.id.chat_cb_type)
    CheckBox chatCbType;
    @Bind(R.id.chat_et_content)
    EditText chatEtContent;
    @Bind(R.id.chat_btn_send)
    TextView chatBtnSend;
    @Bind(R.id.chat_btn_pick_picture)
    ImageButton chatBtnPickPicture;
    @Bind(R.id.chat_type_text)
    LinearLayout chatTypeText;
    @Bind(R.id.chat_btn_record)
    Button chatBtnRecord;
    @Bind(R.id.chat_type_voice)
    LinearLayout chatTypeVoice;
    @Bind(R.id.rl)
    LinearLayout rl;
    private Context context;
    private ImageView mImageView;
    private TextView mTextView;
    private AudioRecoderUtils mAudioRecoderUtils;
    private PopupWindowFactory mPop;


//    private IntentFilter intentFilter;
//    private NetworkChangeReceiver networkChangeReceiver;

    @Override
    public int getLayoutId() {
        return R.layout.device_act_chat;
    }

    @Override
    public void initView() {

        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        chatBtnSend.setOnClickListener(this);
        chatCbType.setOnCheckedChangeListener(this);


        Intent intent = getIntent();
        choise= intent.getIntExtra("position",0);

        chatMsgList = SpUtil.getChatMsgList(SpUtil.getAppUser().deviceList.get(choise).id);

        if (chatMsgList == null)
            chatMsgList = new ArrayList<>();
        if (chatMsgList.size() == 0) {
            String sAgeFormat = getResources().getString(R.string.chat_empty_tip1);
            String sFinalAge = String.format(sAgeFormat, SpUtil.getAppUser().deviceList.get(choise).nickName);
            tvTip1.setText(sFinalAge);
        }

        swipeTarget.setLayoutManager(new GridLayoutManager(this, 1));

        for (int i = 0; i < chatMsgList.size(); i++) {
            addMsgList(chatMsgList.get(i), false);
        }

        multipleItemAdapter = new MultipleItemQuickAdapter(this, msglist);

        multipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return msglist.get(position).getSpanSize();
            }
        });
        swipeTarget.setAdapter(multipleItemAdapter);

        multipleItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                ImageView imageView=view.findViewById(R.id.play);
//                imageView.setImageResource(R.drawable.anim_play_left);
                if (chatMsgList.get(position).from == 0)
                    mAudioRecoderUtils.startNetPlaying(chatMsgList.get(position).msg,imageView);
                else if (chatMsgList.get(position).from == 1)
                    mAudioRecoderUtils.startPlaying(chatMsgList.get(position).msg,imageView);
            }
        });


        context = this;
        //PopupWindow的布局文件
        final View view = View.inflate(this, R.layout.layout_microphone, null);
        mPop = new PopupWindowFactory(this, view);
        //PopupWindow布局文件里面的控件
        mImageView = (ImageView) view.findViewById(R.id.iv_recording_icon);
        mTextView = (TextView) view.findViewById(R.id.tv_recording_time);
        mAudioRecoderUtils = new AudioRecoderUtils();
        //录音回调
        mAudioRecoderUtils.setOnAudioStatusUpdateListener(new AudioRecoderUtils.OnAudioStatusUpdateListener() {

            //录音中....db为声音分贝，time为录音时长
            @Override
            public void onUpdate(double db, long time) {
                mImageView.getDrawable().setLevel((int) (3000 + 6000 * db / 100));
                mTextView.setText(TimeUtils.long2String(time));
            }

            //录音结束，filePath为保存路径
            @Override
            public void onStop(String filePath) {
//                Toast.makeText(ChatActivity.this, "录音保存在：" + filePath, Toast.LENGTH_SHORT).show();
                mTextView.setText(TimeUtils.long2String(0));


//                mPresenter.tk(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,requestBody);

//                File file = new File(filePath);
////                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
//                File file = new File(path);             // 需要上传的文件
//                RequestBody requestFile =               // 根据文件格式封装文件
//                        RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), file);

                File file = new File(filePath);//访问手机端的文件资源，保证手机端sdcdrd中必须有这个文件
                RequestBody requestFile =
                        RequestBody.create(MediaType.parse("multipart/form-data"), file);

                MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                String descriptionString = "This is a description";
                RequestBody description =
                        RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);
                mPresenter.tk(description, body, SpUtil.getWatchUserList().get(choise).id, filePath);

//                mPresenter.viptk(description, body, SpUtil.getWatchUserList().get(choise).id,SpUtil.getAppUser().phone, filePath);

            }
        });


        swipeTarget.scrollToPosition(msglist.size() - 1);


        IntentFilter filter = new IntentFilter(Actions.msg);
        registerReceiver(broadcastReceiver, filter);


        //6.0以上需要权限申请
        requestPermissions();


        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
//                for (int i = 0; i < 20; i++) {
//                    list.add(0,"刷新获得的数据");
//                }
//                myAdapter.notifyDataSetChanged();
                //设置下拉刷新结束
                refreshLayout.setRefreshing(false);
            }
        });


    }

    /**
     * 开启扫描之前判断权限是否打开
     */
    private void requestPermissions() {
        //判断是否开启摄像头权限
        if ((ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)
                ) {
            StartListener();

            //判断是否开启语音权限
        } else {

        }        //请求获取摄像头权限
        ActivityCompat.requestPermissions((Activity) context,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, VOICE_REQUEST_CODE);

    }

    /**
     * 请求权限回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == VOICE_REQUEST_CODE) {
            if ((grantResults[0] == PackageManager.PERMISSION_GRANTED) && (grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                StartListener();
            } else {
                Toast.makeText(context, getString(R.string.tx_permission_fail), Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void StartListener() {
        //Button的touch监听
        chatBtnRecord.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        mPop.showAtLocation(rl, Gravity.CENTER, 0, 0);

                        chatBtnRecord.setText(getString(R.string.tx_save_start));
                        mAudioRecoderUtils.startRecord();


                        break;

                    case MotionEvent.ACTION_UP:

                        mAudioRecoderUtils.stopRecord();        //结束录音（保存录音文件）
//                        mAudioRecoderUtils.cancelRecord();    //取消录音（不保存录音文件）
                        mPop.dismiss();
                        chatBtnRecord.setText(getString(R.string.tx_speak_start));

                        break;
                }
                return true;
            }
        });
    }


    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel, this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chat_btn_send:
                if (chatEtContent.getText().length() == 0)
                    App.showText(getString(R.string.tx_no_null));
                else {
                    mPresenter.vipmessage(SpUtil.getWatchUserList().get(choise).id,SpUtil.getAppUser().phone, chatEtContent.getText().toString());
                    chatEtContent.setText("");
                }
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void initDialog() {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void addMsgList(ChatMsg chatMsg, Boolean isAdd) {
        if (chatMsg.from == 0)
            msglist.add(new MultipleItem(MultipleItem.ITEM_CHAT_LEFT, MultipleItem.IMG_SPAN_SIZE, chatMsg.type, chatMsg.name, R.mipmap.ic_default_headimg, chatMsg.msg, chatMsg.isSuc, chatMsg.time));
        else if (chatMsg.from == 1)
            msglist.add(new MultipleItem(MultipleItem.ITEM_CHAT_RIGHT, MultipleItem.IMG_SPAN_SIZE, chatMsg.type, chatMsg.name, R.mipmap.ic_default_headimg, chatMsg.msg, chatMsg.isSuc, chatMsg.time));

        if (rlEmptyChat.VISIBLE == View.VISIBLE)
            rlEmptyChat.setVisibility(View.GONE);

        if (isAdd) {

            chatMsgList.add(chatMsg);

            multipleItemAdapter.notifyDataSetChanged();
            SpUtil.setChatMsgList(SpUtil.getAppUser().deviceList.get(choise).id, chatMsgList);


        }
        swipeTarget.scrollToPosition(msglist.size() - 1);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            chatTypeText.setVisibility(View.VISIBLE);
            chatTypeVoice.setVisibility(View.GONE);
        } else {

            chatTypeText.setVisibility(View.GONE);
            chatTypeVoice.setVisibility(View.VISIBLE);
        }

    }




    class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {


            Intent msgIntent = new Intent(NetWorkUtil.MESSAGE_RECEIVED_CHAT);

//            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//
//            if (networkInfo != null && networkInfo.isAvailable()) {
//                Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(context, "network is unavailbale", Toast.LENGTH_SHORT).show();
//            }
        }
    }


    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
//            textView.setText(intent.getExtras().getString("data"));
//            App.showText(intent.getExtras().getString("data"));

            chatMsgList = SpUtil.getChatMsgList(SpUtil.getAppUser().deviceList.get(choise).id);
            addMsgList(chatMsgList.get(chatMsgList.size() - 1), false);

        }
    };

//    protected void onDestroy() {
//        unregisterReceiver(broadcastReceiver);
//    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
}
