package com.fatec.recycleapp.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.cardview.widget.CardView;

public class ImageUtil {
    public static void matchToWrap(CardView cardView) {
        ViewGroup.LayoutParams params = cardView.getLayoutParams();
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        cardView.setLayoutParams(params);
    }

    public static void setWeight(CardView cardView, float weight) {
        ((LinearLayout.LayoutParams) cardView.getLayoutParams()).weight = weight;
    }

    public static void paddingRightToLeft(CardView cardView) {
        cardView.setContentPadding(
                cardView.getContentPaddingRight(),
                cardView.getContentPaddingTop(),
                cardView.getContentPaddingRight(),
                cardView.getContentPaddingBottom()
        );
    }
}
