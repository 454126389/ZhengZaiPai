package app.conqueror.com.zhengzaipai.mainfragment.watch.entity;

/**
 * Created by Administrator on 2017/9/5/005.
 */

public class Weather {
    public String city;
    public String temp;
    public String weather;
    public String range;

    public Weather(String city, String temp, String weather, String range) {
        this.city = city;
        this.temp = temp;
        this.weather = weather;
        this.range = range;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }
}
