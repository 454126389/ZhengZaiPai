// (c)2016 Flipboard Inc, All Rights Reserved.

package app.conqueror.com.zhengzaipai.mainfragment.watch.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WatchUser {
    public String id;
    public Boolean sex;
    public String birthDate;
    public String height;
    public String constellation;
    public String school;
    public String controller;
    public String smsPhone;
    public String alarmSwitch;

    public @SerializedName("interest")
    List<String> interestList;

    public @SerializedName("signature")
    List<String> signatureList;

    public String flower;

    public String pattern;

    public @SerializedName("wlak")
    Wlak wlak;

    public String sleep;


    public @SerializedName("sos")
    List<String> sosList;

    public @SerializedName("clock")
    List<String> clockList;

    public @SerializedName("book")
    List<String> bookList;
//    public String book;

    public @SerializedName("disable")
    List<String> disableList;

    public String sim;


    public @SerializedName("Wifi")
    List<Wifi> Wifi;

    public @SerializedName("rails")
    List<String> railsList;

    public @SerializedName("friends")
    List<Friend> friends;

}
