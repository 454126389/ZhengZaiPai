package app.conqueror.com.zhengzaipai.mainfragment.watch.me;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiEntity;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MultipleItem implements MultiItemEntity {
    public static final int TEXT = 1;
    public static final int IMG = 2;
    public static final int IMG_TEXT = 3;
    public static final int IMG_TEXT_LINE = 4;
    public static final int DIVIDER = 5;

    public static final int ITEM_DEVICE_TITLE = 6;
    public static final int ITEM_DEVICE_ITEM = 7;
    public static final int ITEM_CLOCK= 8;
    public static final int ITEM_MAP= 9;
    public static final int ITEM_MENU_ITEM= 10;
    public static final int ITEM_PHONE_BOOK= 11;
    public static final int ITEM_DEVICE= 12;
    public static final int ITEM_CHAT_CONTACT= 13;
    public static final int ITEM_CHAT_LEFT= 14;
    public static final int ITEM_CHAT_RIGHT= 15;
    public static final int ITEM_DEVICE_SELECT= 16;
    public static final int ITEM_ROLE= 17;
    public static final int ITEM_ZONE= 18;
    public static final int  ITEM_DEVICE_AUTH= 19;
    public static final int  ITEM_NULL= 20;
    public static final int  ITEM_MESSAGE= 21;


    public static final int IMG_SPAN_SIZE = 1;
    public static final int IMG_TEXT_SPAN_SIZE_MIN = 2;
    public static final int TEXT_SPAN_SIZE = 3;
    public static final int IMG_TEXT_SPAN_SIZE = 4;

    private int itemType;
    private int spanSize;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int type;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time;

    public WatchPoiEntity getWatchPoiEntity() {
        return watchPoiEntity;
    }

    public void setWatchPoiEntity(WatchPoiEntity watchPoiEntity) {
        this.watchPoiEntity = watchPoiEntity;
    }

    private WatchPoiEntity watchPoiEntity;


    public MultipleItem(int itemType, int spanSize, String label,int icon,String data,Boolean noread) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.label = label;
        this.icon = icon;
        this.data = data;
        this.noread = noread;
    }

    public MultipleItem(int itemType, int spanSize, WatchPoiEntity watchPoiEntity,String name, String address) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.watchPoiEntity=watchPoiEntity;
        this.label=name;
        this.data=address;
    }


    public MultipleItem(int itemType, int spanSize,int type,String label,int icon,String data,Boolean noread,String time) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.type = type;
        this.label = label;
        this.icon = icon;
        this.data = data;
        this.noread = noread;
        this.time = time;
    }


    public MultipleItem(int itemType, int spanSize) {
        this.itemType = itemType;
        this.spanSize = spanSize;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    private String label;
    private String data;
    private int icon;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Boolean getNoread() {
        return noread;
    }

    public void setNoread(Boolean noread) {
        this.noread = noread;
    }

    private Boolean noread;



    @Override
    public int getItemType() {
        return itemType;
    }
}
