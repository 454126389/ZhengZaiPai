package app.conqueror.com.zhengzaipai.mainfragment.watch.device.AddDevice.addlist.binddevice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.conqueror.com.zhengzaipai.R;

/**
 * Created by Administrator on 2017/9/18/018.
 */

public class GalleryAdapter extends
        RecyclerView.Adapter<GalleryAdapter.ViewHolder>
{

    /**
     * ItemClick的回调接口
     * @author zhy
     *
     */
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;


    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }




    private LayoutInflater mInflater;
    private List<Integer> mDatas;
    private List<String> nameList;

    public GalleryAdapter(Context context, List<Integer> datats,List<String> nameList)
    {
        mInflater = LayoutInflater.from(context);
        this.mDatas = datats;
        this.nameList = nameList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ViewHolder(View arg0)
        {
            super(arg0);
        }

        ImageView mImg;
        TextView mTxt;
    }

    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View view = mInflater.inflate(R.layout.device_item_role_choose,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.mImg = (ImageView) view
                .findViewById(R.id.iv_role_head);
        viewHolder.mTxt = (TextView) view
                .findViewById(R.id.tv_role_name);
        return viewHolder;
    }

//    /**
//     * 设置值
//     */
//    @Override
//    public void onBindViewHolder(final ViewHolder viewHolder, final int i)
//    {
//        viewHolder.mImg.setImageResource(mDatas.get(i));
//        viewHolder.mTxt.setText(nameList.get(i));
//    }



    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i)
    {
        viewHolder.mImg.setImageResource(mDatas.get(i));
        viewHolder.mTxt.setText(nameList.get(i));
        //如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null)
        {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View view) {
                    mOnItemClickLitener.onItemClick(viewHolder.itemView, i);
                }
            });

        }

    }

}
