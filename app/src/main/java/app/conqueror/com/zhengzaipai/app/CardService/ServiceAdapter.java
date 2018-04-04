package app.conqueror.com.zhengzaipai.app.CardService;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


import java.util.List;

import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.app.map.ViewHolder;
import app.conqueror.com.zhengzaipai.entity.Plan;

/**
 * Created by Administrator on 2017/7/24.
 */

public class ServiceAdapter extends BaseAdapter {



    private LayoutInflater mInflater;
    private  List<Plan> listItems;    //商品信息集合

    public ServiceAdapter(Context context, List<Plan> listItems){
        this.mInflater = LayoutInflater.from(context);
        this.listItems = listItems;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listItems.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {

            holder=new ViewHolder();

            convertView = mInflater.inflate(R.layout.nav_list_item, null);
            holder.title = (TextView)convertView.findViewById(R.id.title);
            holder.info = (TextView)convertView.findViewById(R.id.info);
            holder.viewBtn = (Button)convertView.findViewById(R.id.view_btn);
            convertView.setTag(holder);

        }else {

            holder = (ViewHolder)convertView.getTag();
        }


        holder.title.setText((String)listItems.get(position).name);
//        holder.info.setText((String)listItems.get(position).address);

        holder.viewBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                showInfo();
            }
        });


        return convertView;
    }

}