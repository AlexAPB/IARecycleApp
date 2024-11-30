package com.fatec.recycleapp.util;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class ButtonUtil {
    public static void wrapToMatch(Button button, boolean width, boolean height) {
        button.setLayoutParams(new ViewGroup.LayoutParams(
                width ? LinearLayout.LayoutParams.MATCH_PARENT : LinearLayout.LayoutParams.WRAP_CONTENT,
                height ? LinearLayout.LayoutParams.MATCH_PARENT : LinearLayout.LayoutParams.WRAP_CONTENT
                )
        );
    }

    public static void background(Button button, int color) {
        button.setBackgroundColor(color);
    }

    public static void corner(Button button, int radius, int color) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(color);

        int dp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                radius,
                button.getContext().getResources().getDisplayMetrics()
        );
        drawable.setCornerRadius(dp);

        button.setBackground(drawable);
    }

    public static void minHeight(Button button, int height) {
        int dp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                height,
                button.getContext().getResources().getDisplayMetrics()
        );

        button.setMinimumHeight(dp);
    }
}
