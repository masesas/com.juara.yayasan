package com.juara.yayasan;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.FileProvider;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.juara.yayasan.Utils.DialogUtils;
import com.valdesekamdem.library.mdtoast.MDToast;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class BaseActivity extends AppCompatActivity {
    public void viewFocus(final View view) {
        view.post(() -> {
            view.setFocusable(true);
            view.requestFocusFromTouch();
            view.requestFocus();
            //view.performClick();
        });
    }

    public void errorFocusET(View view, final String errorMessage) {
        view.post(() -> {
            viewFocus(view);
            ((EditText) view).setError(errorMessage);
        });
    }

    public void showInfo(String text) {
        MDToast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT, MDToast.TYPE_INFO).show();
    }

    public void showInfo(String text, int timeToast) {
        MDToast.makeText(getApplicationContext(), text, timeToast, MDToast.TYPE_INFO).show();
    }

    public void showSuccess(String text) {
        MDToast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();
    }

    public void showSuccess(String text, int timeToast) {
        MDToast.makeText(getApplicationContext(), text, timeToast, MDToast.TYPE_SUCCESS).show();
    }

    public void showError(String text) {
        MDToast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();
    }

    public void showError(String text, int timeToast) {
        MDToast.makeText(getApplicationContext(), text, timeToast, MDToast.TYPE_ERROR).show();
    }

    public void showWarning(String text, int timeToast) {
        MDToast.makeText(getApplicationContext(), text, timeToast, MDToast.TYPE_WARNING).show();
    }

    public void showWarning(String text) {
        MDToast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT, MDToast.TYPE_WARNING).show();
    }

    public String currentDateTime() {
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return simpleDateFormat.format(calendar.getTime());
    }

    public void showDatePicker(final EditText dateTime) {
        final Calendar cldr = Calendar.getInstance();
        final int day = cldr.get(Calendar.DAY_OF_MONTH);
        final int month = cldr.get(Calendar.MONTH);
        final int year = cldr.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance((view, year1, monthOfYear, dayOfMonth) -> {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String newDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1;
            Date date = null;
            try {
                date = sdf.parse(newDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String formattedTime = sdf.format(date);
            dateTime.setText(formattedTime);
        }, year, month, day);
        //datePickerDialog.setMinDate();
        datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
    }

    public <T extends View> T to(View v, Class<? super T> s) {
        return (T) (v);
    }

    public <T extends View> T find(int id) {
        return (T) findViewById(id);
    }

    public <T extends View> T find(int id, Class<? super T> s) {
        return (T) findViewById(id);
    }

    public <T extends View> T findView(View v, int id, Class<? super T> s) {
        return (T) v.findViewById(id);
    }

    public <T extends View> T findView(View v, int id) {
        return v.findViewById(id);
    }

    public String getEditText(EditText editText) {
        return editText.getText().toString();
    }

    public String textInputEditTextValue(int viewID) {
        return getEditText(find(viewID, TextInputEditText.class));
    }

    public EditText findEditText(int viewID) {
        return find(viewID, EditText.class);
    }

    public TextView findTextView(int viewID) {
        return find(viewID, TextView.class);
    }

    protected void setToolbarJul(String title) {
        find(R.id.img_back).setOnClickListener(v -> super.onBackPressed());
        find(R.id.tv_toolbar_title, TextView.class).setText(title);
    }


    protected void setCollapseToolbar(String title) {
        setToolbarJul(title);
        find(R.id.img_back_collapse).setVisibility(View.GONE);
        find(R.id.img_back_collapse).setOnClickListener(v -> super.onBackPressed());
        find(R.id.app_bar, AppBarLayout.class).addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    find(R.id.toolbar_layout, CollapsingToolbarLayout.class).setTitle(title);
                    find(R.id.img_back_collapse).setVisibility(View.VISIBLE);

                    CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) find(R.id.vg_input, NestedScrollView.class)
                            .getLayoutParams();
                    layoutParams.setMargins(0, 0, 0, 0);
                    find(R.id.vg_input, NestedScrollView.class).setLayoutParams(layoutParams);
                } else if (isShow) {
                    isShow = false;
                    find(R.id.toolbar_layout, CollapsingToolbarLayout.class).setTitle(" ");
                    find(R.id.img_back_collapse).setVisibility(View.GONE);

                    CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) find(R.id.vg_input, NestedScrollView.class)
                            .getLayoutParams();
                    layoutParams.setMargins(0, -100, 0, 0);
                    find(R.id.vg_input, NestedScrollView.class).setLayoutParams(layoutParams);
                }
            }
        });
    }

    protected void openGeneratedPDF(File mFile) {
        try {
            if (mFile.exists()) {
                Uri path = FileProvider.getUriForFile(this, getPackageName() + ".provider", mFile);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(path, "application/pdf");

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, "File Tidak Ada", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("error_laporan", e.getMessage());
        }
    }

    protected void showInfoDialog(String tittle, String message, DialogInterface.OnClickListener onClickListenerOK, DialogInterface.OnClickListener onClickListenerNO) {
        if (onClickListenerOK == null) {
            onClickListenerOK = (dialog, which) -> dialog.dismiss();
        }
        DialogUtils.showDialog(this, tittle, message, "Ya", "Tidak", onClickListenerOK, onClickListenerNO);
    }
}
