package edu.osu.cse5914.ibmi.foodrecommendation.util;

import android.widget.TextView;

public class EditTextUtil {
    public static String ERROR_EMPTY = "Should not be empty";

    public static String getString(TextView tv) {
        return tv.getText().toString().trim();
    }

    public static boolean isEmpty(TextView tv) {
        return getString(tv).equals("");
    }

    public static void setWarning(TextView tv, String msg) {
        tv.setError(msg);
    }

    public static boolean warnIfEmpty(TextView tv) {
        if (isEmpty(tv)) {
            setWarning(tv, ERROR_EMPTY);
            return true;
        }

        return false;
    }

    public static boolean isEqual(TextView tv1, TextView tv2) {
        return getString(tv1).equals(getString(tv2));
    }
}
