package com.team3.uniton.unitonapplication.util;

import android.content.res.Resources;

public class Utils {

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
