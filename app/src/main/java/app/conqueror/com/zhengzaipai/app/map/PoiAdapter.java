package app.conqueror.com.zhengzaipai.app.map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;
import java.util.Map;

import app.conqueror.com.zhengzaipai.R;

/**
 * Created by Administrator on 2017/7/24.
 */

public class PoiAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

//
//
//    private LayoutInflater mInflater;
//    private  List<PoiInfo> listItems;    //商品信息集合
//
//    public PoiAdapter(Context context,List<PoiInfo> listItems){
//        this.mInflater = LayoutInflater.from(context);
//        this.listItems = listItems;
//    }
//    @Override
//    public int getCount() {
//        // TODO Auto-generated method stub
//        return listItems.size();
//    }
//
//    @Override
//    public Object getItem(int arg0) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public long getItemId(int arg0) {
//        // TODO Auto-generated method stub
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        ViewHolder holder = null;
//        if (convertView == null) {
//
//            holder=new ViewHolder();
//
//            convertView = mInflater.inflate(R.layout.nav_list_item, null);
//            holder.title = (TextView)convertView.findViewById(R.id.title);
//            holder.info = (TextView)convertView.findViewById(R.id.info);
//            holder.viewBtn = (Button)convertView.findViewById(R.id.view_btn);
//            convertView.setTag(holder);
//
//        }else {
//
//            holder = (ViewHolder)convertView.getTag();
//        }
//
//
//        holder.title.setText((String)listItems.get(position).name);
//        holder.info.setText((String)listItems.get(position).address);
//
//        holder.viewBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
////                showInfo();
//            }
//        });
//
//
//        return convertView;
//    }

}