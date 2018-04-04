package app.conqueror.com.zhengzaipai.mainfragment.watch.home.phone;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.DeviceFragment;
import app.conqueror.com.zhengzaipai.util.SpUtil;

/**
 * Created by Administrator on 2017/10/23/023.
 */

public class AlertDialogFragment extends DialogFragment
{
    String name;
    String id;

    public interface DialogFragmentDataImp{//定义一个与Activity通信的接口，使用该DialogFragment的Activity须实现该接口
        void showMessage(String message);
    }

    //1、定义接口
    public interface OnButtonClick {
        public void onClick();
    }
    private AlertDialogFragment.OnButtonClick onButtonClick;//2、定义接口成员变量

    //定义接口变量的get方法
    public AlertDialogFragment.OnButtonClick getOnButtonClick() {
        return onButtonClick;
    }
    //定义接口变量的set方法
    public void setOnButtonClick(AlertDialogFragment.OnButtonClick onButtonClick) {
        this.onButtonClick = onButtonClick;
    }

    public static AlertDialogFragment newInstance(String name,String id,int type){
        //创建一个带有参数的Fragment实例
        AlertDialogFragment fragment = new AlertDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("id", id);
        bundle.putInt("type", type);
        fragment.setArguments(bundle);//把参数传递给该DialogFragment
        return fragment;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View customView = LayoutInflater.from(getActivity()).inflate(
                R.layout.account_dialog_phone, null);
        TextView tv_name_dialog_phone = (TextView) customView.findViewById(R.id.tv_name_dialog_phone);
        TextView et_dialog_phone = (TextView) customView.findViewById(R.id.et_dialog_phone);
        Button tv_call = (Button) customView.findViewById(R.id.tv_call);


        String id=getArguments().getString("id");
        String phone=SpUtil.getAppUser().phone;
        int type=getArguments().getInt("type");

        tv_name_dialog_phone.setText(getArguments().getString("name"));//把传递过来的数据设置给TextView

        if(type==0)
            tv_call.setText(getString(R.string.tx_call));
        if(type==1)
            tv_call.setText(getString(R.string.tx_call_back));

        et_dialog_phone.setText(phone);//把传递过来的数据设置给TextView
        tv_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(type==0)
                {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" +phone));
                    SpUtil.setPhone(id,et_dialog_phone.getText().toString());
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(intent);
                    }else
                    {
                        App.showText(getString(R.string.tx_call_fail));
                    }
                }else
                {
                    getOnButtonClick().onClick();

                }
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

}