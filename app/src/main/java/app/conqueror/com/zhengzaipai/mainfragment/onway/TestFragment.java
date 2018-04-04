package app.conqueror.com.zhengzaipai.mainfragment.onway;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.conqueror.com.zhengzaipai.R;


public  class TestFragment extends Fragment {

    private String s;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        View view=getView();
        TextView te=(TextView)view.findViewById(R.id.textView1);
        te.setText(s);
    }

    public void initView() {
    }
}
