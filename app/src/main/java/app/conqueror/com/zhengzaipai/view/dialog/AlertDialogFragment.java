package app.conqueror.com.zhengzaipai.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Administrator on 2017/10/16/016.
 */

public class AlertDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return new AlertDialog.Builder(getActivity()).setTitle("Title").setMessage("are you ok?")
                .setPositiveButton("Sure", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                }).setNegativeButton("cancel", null)
                .create();
    }
}