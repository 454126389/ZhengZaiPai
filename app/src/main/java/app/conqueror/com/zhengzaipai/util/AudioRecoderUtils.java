package app.conqueror.com.zhengzaipai.util;

import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import app.conqueror.com.zhengzaipai.R;

import static com.tencent.wxop.stat.common.StatConstants.LOG_TAG;

/**
 * Created by Administrator on 2017/6/16.
 */

public class AudioRecoderUtils {
    //文件路径
    private String filePath;
    //文件夹路径
    private String FolderPath;

    private MediaRecorder mMediaRecorder;
    private final String TAG = "fan";
    public static final int MAX_LENGTH = 1000 * 60 * 10;// 最大录音时长1000*60*10;

    private OnAudioStatusUpdateListener audioStatusUpdateListener;



    private MediaPlayer mPlayer = null;

    /**
     * 文件存储默认sdcard/record
     */
    public AudioRecoderUtils(){

        //默认保存路径为/sdcard/record/下
        this(Environment.getExternalStorageDirectory()+"/cache/");
    }

    public AudioRecoderUtils(String filePath) {

        File path = new File(filePath);
        if(!path.exists())
            path.mkdirs();

        this.FolderPath = filePath;
    }

    private long startTime;
    private long endTime;



    /**
     * 开始录音 使用amr格式
     *      录音文件
     * @return
     */
    public void startRecord() {
        // 开始录音
        /* ①Initial：实例化MediaRecorder对象 */
        if (mMediaRecorder == null)
            mMediaRecorder = new MediaRecorder();
        try {
            /* ②setAudioSource/setVedioSource */
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);// 设置麦克风
            /* ②设置音频文件的编码：AAC/AMR_NB/AMR_MB/Default 声音的（波形）的采样 */
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
            /*
             * ②设置输出文件的格式：THREE_GPP/MPEG-4/RAW_AMR/Default THREE_GPP(3gp格式
             * ，H263视频/ARM音频编码)、MPEG-4、RAW_AMR(只支持音频且音频编码要求为AMR_NB)
             */
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);


            filePath = FolderPath + TimeUtils.getCurrentTime() + ".amr" ;
            /* ③准备 */
            mMediaRecorder.setOutputFile(filePath);
            mMediaRecorder.setMaxDuration(MAX_LENGTH);
            mMediaRecorder.prepare();
            /* ④开始 */
            mMediaRecorder.start();
            // AudioRecord audioRecord.
            /* 获取开始时间* */
            startTime = System.currentTimeMillis();
            updateMicStatus();
            Log.e("fan", "startTime" + startTime);
        } catch (IllegalStateException e) {
            Log.i(TAG, "call startAmr(File mRecAudioFile) failed!" + e.getMessage());
        } catch (IOException e) {
            Log.i(TAG, "call startAmr(File mRecAudioFile) failed!" + e.getMessage());
        }
    }

    /**
     * 停止录音
     */
    public long stopRecord() {
        if (mMediaRecorder == null)
            return 0L;
        endTime = System.currentTimeMillis();

        //有一些网友反应在5.0以上在调用stop的时候会报错，翻阅了一下谷歌文档发现上面确实写的有可能会报错的情况，捕获异常清理一下就行了，感谢大家反馈！
        try {
            mMediaRecorder.stop();
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;

            audioStatusUpdateListener.onStop(filePath);
            filePath = "";

        }catch (RuntimeException e){

           if(mMediaRecorder!=null){
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;
           }
            File file = new File(filePath);
            if (file.exists())
                file.delete();

            filePath = "";

        }
        return endTime - startTime;
    }

    /**
     * 取消录音
     */
    public void cancelRecord(){

        try {

            mMediaRecorder.stop();
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;

        }catch (RuntimeException e){
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;
        }
        File file = new File(filePath);
        if (file.exists())
            file.delete();

        filePath = "";

    }

    private final Handler mHandler = new Handler();
    private Runnable mUpdateMicStatusTimer = new Runnable() {
        public void run() {
            updateMicStatus();
        }
    };


    private int BASE = 1;
    private int SPACE = 100;// 间隔取样时间

    public void setOnAudioStatusUpdateListener(OnAudioStatusUpdateListener audioStatusUpdateListener) {
        this.audioStatusUpdateListener = audioStatusUpdateListener;
    }

    /**
     * 更新麦克状态
     */
    private void updateMicStatus() {

        if (mMediaRecorder != null) {
            double ratio = (double)mMediaRecorder.getMaxAmplitude() / BASE;
            double db = 0;// 分贝
            if (ratio > 1) {
                db = 20 * Math.log10(ratio);
                if(null != audioStatusUpdateListener) {
                    audioStatusUpdateListener.onUpdate(db, System.currentTimeMillis()-startTime);
                }
            }
            mHandler.postDelayed(mUpdateMicStatusTimer, SPACE);
        }
    }

    public interface OnAudioStatusUpdateListener {
        /**
         * 录音中...
         * @param db 当前声音分贝
         * @param time 录音时长
         */
        public void onUpdate(double db, long time);

        /**
         * 停止录音
         * @param filePath 保存路径
         */
        public void onStop(String filePath);
    }


    public void startPlaying(String mFileName,ImageView imageView ) {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            imageView.setImageResource(R.drawable.anim_play_right);
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Log.d("tag", "播放完毕");
                    //根据需要添加自己的代码。。。
                    imageView.setImageResource(R.mipmap.esi);
                }
            });
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    // 初始化播放器
    public void startNetPlaying(String url,  ImageView imageView ) {

        try {
            mPlayer = new MediaPlayer();
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置媒体流类型
            mPlayer.setDataSource(url); // 设置数据源
            imageView.setImageResource(R.drawable.anim_play_left);
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Log.d("tag", "播放完毕");
                    //根据需要添加自己的代码。。。
                    imageView.setImageResource(R.mipmap.esi);
//                    imageView.setVisibility(View.INVISIBLE);
//                    animationDrawable.stop();
                }
            });
            mPlayer.prepare(); // prepare自动播放
            mPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
    }


    /**
     * 得到amr的时长
     *
     * @param file
     * @return amr文件时间长度
     * @throws IOException
     */
    public static int getAmrDuration(File file) throws IOException {
        long duration = -1;
        int[] packedSize = { 12, 13, 15, 17, 19, 20, 26, 31, 5, 0, 0, 0, 0, 0,
                0, 0 };
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            long length = file.length();// 文件的长度
            int pos = 6;// 设置初始位置
            int frameCount = 0;// 初始帧数
            int packedPos = -1;

            byte[] datas = new byte[1];// 初始数据值
            while (pos <= length) {
                randomAccessFile.seek(pos);
                if (randomAccessFile.read(datas, 0, 1) != 1) {
                    duration = length > 0 ? ((length - 6) / 650) : 0;
                    break;
                }
                packedPos = (datas[0] >> 3) & 0x0F;
                pos += packedSize[packedPos] + 1;
                frameCount++;
            }

            duration += frameCount * 20;// 帧数*20
        } finally {
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
        }
        return (int)((duration/1000)+1);
    }

}
