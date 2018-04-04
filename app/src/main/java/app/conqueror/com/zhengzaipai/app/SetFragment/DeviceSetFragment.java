package app.conqueror.com.zhengzaipai.app.SetFragment;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import app.conqueror.com.zhengzaipai.R;


/**
 * Created by tomchen on 2/27/16.
 */
public class DeviceSetFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.device_setting);




    }
}
