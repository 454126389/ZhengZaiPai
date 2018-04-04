package app.conqueror.com.zhengzaipai.app.SetFragment;

import android.content.Intent;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.util.SpUtil;


/**
 * Created by tomchen on 2/27/16.
 */
public class PoiSetFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);


        final CheckBoxPreference saveFlow = (CheckBoxPreference) getPreferenceManager()
                .findPreference("save_flow");

        saveFlow.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean checked = Boolean.valueOf(newValue.toString());
                if (checked==true){
                    Toast.makeText(getActivity(),"开启省流量模式", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(),"关闭省流量模式", Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });

        final CheckBoxPreference allowPush = (CheckBoxPreference) getPreferenceManager()
                .findPreference("allow_push");

        allowPush.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean checked = Boolean.valueOf(newValue.toString());
                if (checked==true){
                    Toast.makeText(getActivity(),"开启消息推送", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(),"关闭消息推送", Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });

    }
}
