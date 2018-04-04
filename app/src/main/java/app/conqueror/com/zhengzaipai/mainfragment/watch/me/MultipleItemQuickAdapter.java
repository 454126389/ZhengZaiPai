package app.conqueror.com.zhengzaipai.mainfragment.watch.me;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.util.AudioRecoderUtils;
import app.conqueror.com.zhengzaipai.util.DateUtils;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import app.conqueror.com.zhengzaipai.util.TimeUtils;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 * modify by AllenCoder
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {
    int[] imgList;

    public MultipleItemQuickAdapter(Context context, List data) {
        super(data);
        addItemType(MultipleItem.TEXT, R.layout.item_text_view);
        addItemType(MultipleItem.IMG, R.layout.item_image_view);
        addItemType(MultipleItem.IMG_TEXT, R.layout.item_img_text_view);
        addItemType(MultipleItem.IMG_TEXT_LINE, R.layout.item);
        addItemType(MultipleItem.DIVIDER, R.layout.item_divider);

        addItemType(MultipleItem.ITEM_DEVICE_TITLE, R.layout.item_device_title);
        addItemType(MultipleItem.ITEM_DEVICE_ITEM, R.layout.item_device_item);
        addItemType(MultipleItem.ITEM_CLOCK, R.layout.device_item_clock);
        addItemType(MultipleItem.ITEM_MAP, R.layout.item_map_device_position);
        addItemType(MultipleItem.ITEM_MENU_ITEM, R.layout.item_menu_item);
        addItemType(MultipleItem.ITEM_PHONE_BOOK, R.layout.item_phone_book_x);
        addItemType(MultipleItem.ITEM_DEVICE, R.layout.item_device_select);
        addItemType(MultipleItem.ITEM_CHAT_CONTACT, R.layout.item_chat_contact);
        addItemType(MultipleItem.ITEM_CHAT_LEFT, R.layout.device_item_chat_left);
        addItemType(MultipleItem.ITEM_CHAT_RIGHT, R.layout.device_item_chat_right);
        addItemType(MultipleItem.ITEM_DEVICE_SELECT, R.layout.login_item_bind_device);
        addItemType(MultipleItem.ITEM_ROLE, R.layout.device_item_role_choose);
        addItemType(MultipleItem.ITEM_ZONE, R.layout.device_item_safe_zone);
        addItemType(MultipleItem.ITEM_DEVICE_AUTH, R.layout.item_device_auth);
        addItemType(MultipleItem.ITEM_MESSAGE, R.layout.account_item_message);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {

        switch (helper.getItemViewType()) {
            case MultipleItem.IMG:
                helper.addOnClickListener(R.id.card_view);
                Picasso.with(mContext).load(item.getLabel()).resize(120, 60).into((ImageView) helper.getView(R.id.iv));
//                helper.setImageResource(R.id.iv, item.getIcon());


                break;
            case MultipleItem.TEXT:
//                helper.setText(R.id.tv, item.getContent());
                break;
            case MultipleItem.IMG_TEXT:
               /* switch (helper.getLayoutPosition() %
                        2) {
                    case 0:
                        helper.setImageResource(R.id.iv, R.mipmap.animation_img1);
                        break;
                    case 1:
                        helper.setImageResource(R.id.iv, R.mipmap.animation_img2);
                        break;

                }*/
                helper.setImageResource(R.id.iv, R.mipmap.animation_img1);
                break;
            case MultipleItem.IMG_TEXT_LINE:
                helper.addOnClickListener(R.id.root);
                helper.setImageResource(R.id.icon, item.getIcon());
                helper.setText(R.id.label, item.getLabel());
                helper.setText(R.id.data, item.getData());
                helper.setVisible(R.id.tv_have_message_no_read, item.getNoread());
                break;

            case MultipleItem.ITEM_DEVICE_TITLE:
                helper.setText(R.id.tv_title, item.getLabel());
                helper.setText(R.id.tv_content, item.getData());
                break;

            case MultipleItem.ITEM_DEVICE_ITEM:
                helper.addOnClickListener(R.id.root_device);
                helper.setImageResource(R.id.icon, item.getIcon());
                helper.setText(R.id.label, item.getLabel());
                break;
            case MultipleItem.ITEM_CLOCK:
                helper.addOnClickListener(R.id.item);
                helper.addOnClickListener(R.id.switch_compat);
                helper.setText(R.id.tv_data, item.getLabel());
                helper.setText(R.id.tv_date, item.getData());
                helper.setChecked(R.id.switch_compat, item.getNoread());

                break;
            case MultipleItem.ITEM_MAP:
                helper.setText(R.id.tv_username, "" + item.getLabel());
                helper.setText(R.id.tv_date, "" + DateUtils.times(item.getWatchPoiEntity().getTime()));
                String type = null;
                if (item.getWatchPoiEntity().getType() == 0)
                    type = "无法定位";
                else if (item.getWatchPoiEntity().getType() == 1)
                    type = "GPS定位";
                else if (item.getWatchPoiEntity().getType() == 2)
                    type = "基站定位";
                else if (item.getWatchPoiEntity().getType() == 3)
                    type = "WIFI定位";
                helper.setText(R.id.tv_position_datatype, "" + type);
                helper.setText(R.id.tv_battery, "" + item.getWatchPoiEntity().getElec() + "%");
                helper.setText(R.id.tv_address1, "" + item.getData());
                break;
            case MultipleItem.ITEM_MENU_ITEM:
                helper.addOnClickListener(R.id.root_device);
                helper.setImageResource(R.id.icon, item.getIcon());
                helper.setText(R.id.label, item.getLabel());
                break;
            case MultipleItem.ITEM_PHONE_BOOK:
                helper.addOnClickListener(R.id.slide_layout);
                helper.setText(R.id.name, item.getLabel());
                helper.setText(R.id.phone, item.getData());
                break;
            case MultipleItem.ITEM_DEVICE:
                helper.addOnClickListener(R.id.root);
                helper.setText(R.id.label, item.getLabel());
                break;
            case MultipleItem.ITEM_CHAT_CONTACT:
                helper.addOnClickListener(R.id.root_chat_item);
                helper.setImageResource(R.id.icon, item.getIcon());
                helper.setText(R.id.tv_name, item.getLabel());
                helper.setText(R.id.tv_content, item.getData());
                break;

            case MultipleItem.ITEM_CHAT_LEFT:
                //文字
                if (item.getType() == 0) {
                    //设备没法打字不处理

                }
                if (item.getType() == 1) {
                    //语音

                    helper.addOnClickListener(R.id.root);
//                    helper.setVisible(R.id.play,false);
                    helper.setVisible(R.id.img, false);
                    helper.setVisible(R.id.reload, !item.getNoread());

                    helper.setText(R.id.tv_name, item.getLabel());
                    int lenth=0;
                    try {
                        MediaPlayer player = new MediaPlayer();
                        player.setDataSource(item.getData());


                        lenth=player.getDuration()%3600%60;

//                        Calendar c = Calendar.getInstance();
//                        c.setTime(new Date(player.getDuration()));
//                        lenth=c.get(Calendar.SECOND);
//                        lenth=player.getDuration();
//                        lenth=lenth/1000000%60;
//                        lenth=AudioRecoderUtils.getAmrDuration(new File(item.getData()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    helper.setText(R.id.length, lenth+"''");
                    helper.setVisible(R.id.length, false);
//                    helper.setText(R.id.content, item.getData());
                    helper.setText(R.id.date, item.getTime());
                }
                break;
            case MultipleItem.ITEM_CHAT_RIGHT:
                //文字
                if (item.getType() == 0) {
                    helper.setVisible(R.id.length, false);
                    helper.setVisible(R.id.play, false);
                    helper.setVisible(R.id.img, false);

                    helper.setVisible(R.id.reload, !item.getNoread());

                    helper.setText(R.id.tv_name, item.getLabel());
                    helper.setText(R.id.content, item.getData());
                    helper.setText(R.id.date, item.getTime());

                }
                if (item.getType() == 1) {
                    helper.addOnClickListener(R.id.root);

                    helper.setVisible(R.id.reload, !item.getNoread());
                    helper.setText(R.id.tv_name, item.getLabel());


                    int lenth=0;
                    try {
                        lenth=AudioRecoderUtils.getAmrDuration(new File(item.getData()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    helper.setText(R.id.length, lenth+"''");

//                    helper.setText(R.id.content, item.getData());
                    helper.setText(R.id.date, item.getTime());
                    //语音
                }
                break;
            case MultipleItem.ITEM_DEVICE_SELECT:
                helper.addOnClickListener(R.id.root);
                break;
            case MultipleItem.ITEM_ROLE:
                helper.setImageResource(R.id.iv_role_head, item.getIcon());
                helper.setText(R.id.tv_role_name, item.getLabel());
                break;
            case MultipleItem.ITEM_ZONE:
                helper.setText(R.id.tv_name, item.getLabel());
                helper.setText(R.id.tv_range, item.getData());
                helper.setChecked(R.id.switch_compat, item.getNoread());

                helper.addOnClickListener(R.id.slide_hide_panel);
                helper.addOnClickListener(R.id.root);
                helper.addOnClickListener(R.id.switch_compat);
                break;
            case MultipleItem.ITEM_DEVICE_AUTH:
                helper.addOnClickListener(R.id.root);
                helper.setText(R.id.tv_title, item.getLabel());
                helper.setText(R.id.tv_content, item.getData());
                break;
            case MultipleItem.ITEM_MESSAGE:
//                helper.addOnClickListener(R.id.root);
                helper.setText(R.id.tv_title_message_center, item.getLabel());
                helper.setText(R.id.tv_content_message_center, item.getData());
                helper.setText(R.id.tv_content_message_center, item.getTime());
                break;
        }
    }

}
