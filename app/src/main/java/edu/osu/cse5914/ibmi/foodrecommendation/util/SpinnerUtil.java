package edu.osu.cse5914.ibmi.foodrecommendation.util;

import android.widget.Spinner;

public class SpinnerUtil {
    public static int getOption(Spinner spinner) {
        return spinner.getSelectedItemPosition();
    }
}
