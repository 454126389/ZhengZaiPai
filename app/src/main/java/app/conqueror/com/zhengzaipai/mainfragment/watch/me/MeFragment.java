package app.conqueror.com.zhengzaipai.mainfragment.watch.me;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActMessage.ActMessageActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.login.LoginActivity;
import app.conqueror.com.zhengzaipai.util.SpUtil;

public class MeFragment extends Fragment{
    Context myContext;
    RecyclerView recycler_view_1;
    ImageButton btn_alarm_info;
    private static final String CYM_CHAD = "CymChad";

    private int[] imgList={R.mipmap.ic_default_headimg,R.mipmap.ic_me_ranking};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_me, container, false);
        recycler_view_1 = (RecyclerView) view.findViewById(R.id.recycler_view_1);
        btn_alarm_info = (ImageButton) view.findViewById(R.id.btn_alarm_info);


        btn_alarm_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ActMessageActivity.class));
            }
        });

        List<MultipleItem> list = new ArrayList<>();
//        list.add(new MultipleItem(MultipleItem.IMG_TEXT_LINE, MultipleItem.IMG_SPAN_SIZE, "宝宝",R.mipmap.ic_default_headimg,"全部设备积分",true));
//        list.add(new MultipleItem(MultipleItem.IMG_TEXT_LINE, MultipleItem.IMG_SPAN_SIZE, getString(R.string.score_rank),R.mipmap.ic_default_headimg,"",false));
//        list.add(new MultipleItem(MultipleItem.DIVIDER, MultipleItem.IMG_SPAN_SIZE, null,0,null,false));
//        list.add(new MultipleItem(MultipleItem.IMG_TEXT_LINE, MultipleItem.IMG_SPAN_SIZE, getString(R.string.my_medal),R.mipmap.ic_me_medal,"",false));
//        list.add(new MultipleItem(MultipleItem.DIVIDER, MultipleItem.IMG_SPAN_SIZE, null,0,null,false));
        list.add(new MultipleItem(MultipleItem.IMG_TEXT_LINE, MultipleItem.IMG_SPAN_SIZE, getString(R.string.account_manager),R.mipmap.ic_me_account,"",false));
        list.add(new MultipleItem(MultipleItem.IMG_TEXT_LINE, MultipleItem.IMG_SPAN_SIZE, getString(R.string.about),R.mipmap.ic_me_advice,"",false));
        list.add(new MultipleItem(MultipleItem.IMG_TEXT_LINE, MultipleItem.IMG_SPAN_SIZE, "退出",R.mipmap.ic_me_advice,"",false));

        final MultipleItemQuickAdapter multipleItemAdapter = new MultipleItemQuickAdapter(myContext, list);
        final GridLayoutManager manager = new GridLayoutManager(myContext, 1);

        recycler_view_1.setLayoutManager(manager);
        multipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return list.get(position).getSpanSize();
            }
        });
        recycler_view_1.setAdapter(multipleItemAdapter);

        multipleItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        getActivity().startActivity(new Intent(getActivity(), FamilyActivity.class));
                        break;
                    case 1:
                        getActivity().startActivity(new Intent(getActivity(), AboutActivity.class));
                        break;
                    case 2:
                        SpUtil.setIsAutoLogin(false);
                        getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finish();

                        break;
                }
            }
        });
//        SectionAdapter extra_sectionAdapter = new SectionAdapter(R.layout.item_section_content, R.layout.def_section_head, extra_mData,extra_imgList);

        return view;
    }
    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }
}
