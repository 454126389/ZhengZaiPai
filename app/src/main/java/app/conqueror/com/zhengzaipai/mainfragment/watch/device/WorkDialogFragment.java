package app.conqueror.com.zhengzaipai.mainfragment.watch.device;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.List;

import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.util.SpUtil;

/**
 * Created by Administrator on 2017/10/23/023.
 */

public class WorkDialogFragment extends DialogFragment
{

    String pattern = null;
    String name;
    String id;

    public interface DialogFragmentDataImp{//定义一个与Activity通信的接口，使用该DialogFragment的Activity须实现该接口
        void showMessage(String message);
    }

    //1、定义接口
    public interface OnButtonClick {
        public void onClick(String id,int type);
    }
    private WorkDialogFragment.OnButtonClick onButtonClick;//2、定义接口成员变量

    //定义接口变量的get方法
    public WorkDialogFragment.OnButtonClick getOnButtonClick() {
        return onButtonClick;
    }
    //定义接口变量的set方法
    public void setOnButtonClick(WorkDialogFragment.OnButtonClick onButtonClick) {
        this.onButtonClick = onButtonClick;
    }

    public static WorkDialogFragment newInstance(){
        //创建一个带有参数的Fragment实例
        WorkDialogFragment fragment = new WorkDialogFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("name", name);
//        bundle.putString("id", id);
//        bundle.putInt("type", type);
//        fragment.setArguments(bundle);//把参数传递给该DialogFragment
        return fragment;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View customView = LayoutInflater.from(getActivity()).inflate(
                R.layout.view_setting_work_mode, null);
        RadioButton radio1, radio2, radio3;
        radio1 = (RadioButton) customView.findViewById(R.id.setting_work_mode_rb_01);
        radio2 = (RadioButton) customView.findViewById(R.id.setting_work_mode_rb_02);
        radio3 = (RadioButton) customView.findViewById(R.id.setting_work_mode_rb_03);
        Button btn_work_sure = (Button) customView.findViewById(R.id.btn_work_sure);
        if(SpUtil.getWatchUserList().get(SpUtil.getChoise()).pattern==null)
            SpUtil.changePattern(SpUtil.getChoise(),"60");
        else
            pattern=SpUtil.getWatchUserList().get(SpUtil.getChoise()).pattern;

        if (SpUtil.getWatchUserList().get(SpUtil.getChoise()).pattern.equals("10")) {
            radio1.setChecked(true);
            pattern = "10";

        } else if (SpUtil.getWatchUserList().get(SpUtil.getChoise()).pattern.equals("60")) {
            radio2.setChecked(true);
            pattern = "60";
        } else if (SpUtil.getWatchUserList().get(SpUtil.getChoise()).pattern.equals("1")) {
            radio3.setChecked(true);
            pattern = "1";
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.setting_work_mode_rb_01:
                        radio2.setChecked(false);
                        radio3.setChecked(false);
                        pattern = "10";
                        break;
                    case R.id.setting_work_mode_rb_02:
                        radio1.setChecked(false);
                        radio3.setChecked(false);
                        pattern = "60";
                        break;
                    case R.id.setting_work_mode_rb_03:
                        radio1.setChecked(false);
                        radio2.setChecked(false);
                        pattern = "1";
                        break;
                }
            }
        };
        radio1.setOnClickListener(onClickListener);
        radio2.setOnClickListener(onClickListener);
        radio3.setOnClickListener(onClickListener);


        btn_work_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getOnButtonClick().onClick(pattern,0);


                dismiss();

            }
        });

        return new AlertDialog.Builder(getActivity()).setView(customView)
                .create();
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        getOnButtonClick().onClick("",99);
    }
}