package com.juara.yayasan.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

public class DialogUtils {

    public static void showDialog(Context context, String title, String message, String button1, String button2, DialogInterface.OnClickListener listener1, DialogInterface.OnClickListener listener2) {
        AlertDialog.Builder dlg = new AlertDialog.Builder(context);
        if (title != null && !title.equals("")) {
            dlg.setTitle(title);
        }
        if (message != null && !message.equals("")) {
            dlg.setMessage(message);
        }

        if (button1 != null && !button1.equals("") && button1 != null && !button1.equals("")) {
            dlg.setPositiveButton(button1, listener1);
            dlg.setNegativeButton(button2, listener2);
        } else if (button1 != null && !button1.equals("")) {
            dlg.setNeutralButton(button1, listener1);
        }
        dlg.setCancelable(false);
        dlg.create().show();
    }

    public static void showDialog(Context context, View layout, boolean cancelable) {
        AlertDialog.Builder dlg = new AlertDialog.Builder(context);
        dlg.setView(layout);
        dlg.setCancelable(cancelable);
        AlertDialog dialog = dlg.create();
        layout.setTag(dialog);
        dialog.show();
    }
}
