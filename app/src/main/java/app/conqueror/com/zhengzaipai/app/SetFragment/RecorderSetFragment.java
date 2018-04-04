package app.conqueror.com.zhengzaipai.app.SetFragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import app.conqueror.com.zhengzaipai.R;


/**
 * Created by tomchen on 2/27/16.
 */
public class RecorderSetFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.recorder_setting);




    }
}
