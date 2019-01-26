package com.bruce.pc.blogfeeds;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bruce.pc.R;

public class ViewFactory {
    private Context context;

    public ViewFactory(Context context) {
        this.context = context;
    }

    public RecyclerView newFeedsRecyclerView() {
        RecyclerView view = new RecyclerView(context);
        return view;
    }


    public static ViewGroup newFeedItemRow(Context context, int columns) {
        LinearLayout viewGroup = new LinearLayout(context);
        for (int i = 0; i < columns; i++) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.weight = 1;
            lp.leftMargin = 5;
            if (i == columns - 1) lp.rightMargin = 5;
            viewGroup.addView(new FeedCardView(context), lp);
        }
        int padding = context.getResources().getDimensionPixelSize(R.dimen.padding) * 3;
        viewGroup.setPadding(0, 0, 0, padding);
        return viewGroup;
    }

    public static Drawable getBackgroundDrawableForFeedItem() {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setStroke(3, Color.GRAY);
        return shape;
    }
}
