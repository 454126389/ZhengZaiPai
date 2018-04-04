// (c)2016 Flipboard Inc, All Rights Reserved.

package app.conqueror.com.zhengzaipai.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServiceResult {

    public String index;
    public String title;

    public @SerializedName("baseset")
    List<Plan> baseset;
    public @SerializedName("changeset")
    List<Plan> changeset;
    public @SerializedName("content_flowall")
    List<Plan> content_flowall;
    public @SerializedName("content_iccid")
    CardMsg content_iccid;
}
