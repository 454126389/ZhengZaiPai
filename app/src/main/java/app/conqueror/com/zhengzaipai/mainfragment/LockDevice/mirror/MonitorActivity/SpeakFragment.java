package app.conqueror.com.zhengzaipai.mainfragment.LockDevice.mirror.MonitorActivity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.conqueror.com.zhengzaipai.R;


public  class SpeakFragment extends Fragment {



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View messageLayout = inflater.inflate(R.layout.fragment_speak, container, false);
        return messageLayout;
    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        return inflater.inflate(R.layout.fragment_speak, container, false);
//    }
//
//    @Override
//    public void onActivityCreated( Bundle savedInstanceState) {
//        // TODO Auto-generated method stub
//        super.onActivityCreated(savedInstanceState);
//        View view=getView();
//        TextView te=(TextView)view.findViewById(R.id.sendmsg_et);
//        te.setText("aaaaaaaaa");
//    }


}
