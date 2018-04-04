// (c)2016 Flipboard Inc, All Rights Reserved.

package app.conqueror.com.zhengzaipai.mainfragment.watch.entity;

import com.google.gson.annotations.SerializedName;

public class AppResult {
    public String success;
    public @SerializedName("mes")
    AppUser appUser;

}
