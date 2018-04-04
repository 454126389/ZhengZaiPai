package app.conqueror.com.zhengzaipai.mainfragment.watch.device;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.util.SpUtil;

/**
 * Created by Administrator on 2017/10/23/023.
 */

public class SosDialogFragment extends DialogFragment
{
    String name;
    String id;

    public interface DialogFragmentDataImp{//定义一个与Activity通信的接口，使用该DialogFragment的Activity须实现该接口
        void showMessage(String message);
    }

    //1、定义接口
    public interface OnButtonClick {
        public void onClick(StringBuffer stringBuffer,int type);
    }
    private SosDialogFragment.OnButtonClick onButtonClick;//2、定义接口成员变量

    //定义接口变量的get方法
    public SosDialogFragment.OnButtonClick getOnButtonClick() {
        return onButtonClick;
    }
    //定义接口变量的set方法
    public void setOnButtonClick(SosDialogFragment.OnButtonClick onButtonClick) {
        this.onButtonClick = onButtonClick;
    }

    public static SosDialogFragment newInstance(){
        //创建一个带有参数的Fragment实例
        SosDialogFragment fragment = new SosDialogFragment();
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
                R.layout.device_setting_sos, null);
        EditText setting_sos_num_01, setting_sos_num_02, setting_sos_num_03;
        setting_sos_num_01 = (EditText) customView.findViewById(R.id.setting_sos_num_01);
        setting_sos_num_02 = (EditText) customView.findViewById(R.id.setting_sos_num_02);
        setting_sos_num_03 = (EditText) customView.findViewById(R.id.setting_sos_num_03);
        Button btn_sos_sure = (Button) customView.findViewById(R.id.btn_sos_sure);
        List<String> sosList = SpUtil.getWatchUserList().get(SpUtil.getChoise()).sosList;
        if(sosList!=null)
        {
            for (int i = 0; i < sosList.size(); i++) {
                if (i == 0)
                    setting_sos_num_01.setText(sosList.get(0).toString());
                if (i == 1)
                    setting_sos_num_02.setText(sosList.get(1).toString());
                if (i == 2)
                    setting_sos_num_03.setText(sosList.get(2).toString());
            }
        }

        btn_sos_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                StringBuffer stringBuffer = new StringBuffer();
                if (setting_sos_num_01.getText().length() > 0)
                    stringBuffer.append(setting_sos_num_01.getText().toString());
                if (setting_sos_num_02.getText().length() > 0) {
                    if (stringBuffer.length() > 0)
                        stringBuffer.append(",");
                    stringBuffer.append(setting_sos_num_02.getText().toString());
                }
                if (setting_sos_num_03.getText().length() > 0)
                    if (stringBuffer.length() > 0)
                        stringBuffer.append(",");
                stringBuffer.append(setting_sos_num_03.getText().toString());
//                getOnButtonClick().onClick(position, SpUtil.getWatchUserList().get(SpUtil.getChoise()).id, stringBuffer.toString());
                getOnButtonClick().onClick(stringBuffer,0);


                dismiss();


//                if(type==0)
//                {
//                    Intent intent = new Intent(Intent.ACTION_DIAL);
//                    intent.setData(Uri.parse("tel:" +phone));
//                    SpUtil.setPhone(id,et_dialog_phone.getText().toString());
//                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
//                        startActivity(intent);
//                    }else
//                    {
//                        App.showText(getString(R.string.tx_call_fail));
//                    }
//                }else
//                {
//                    getOnButtonClick().onClick();
//
//                }
            }
        });
//        mBtnCancel.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });
        return new AlertDialog.Builder(getActivity()).setView(customView)
                .create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        getOnButtonClick().onClick(new StringBuffer(),99);
    }

}