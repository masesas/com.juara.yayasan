package com.juara.yayasan.Utils;

import android.app.Activity;
import android.content.Context;

import com.juara.yayasan.R;

public class ConstantsUtils {
    public static final String HARUS_ISI = " Harus di Isi ";
    public static final float HEIGHT_NORMAL_TOOLBAR (Context activity) {
        return activity.getResources().getDimension(R.dimen.toolbar_jul_normal_height);
    }

    public static final float HEIGHT_TOOLBAR_SCROLLING(Context activity){
        return activity.getResources().getDimension(R.dimen.toolbar_jul_scroll_height);
    }
}
