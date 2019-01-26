package com.bruce.pc.blogfeeds;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bruce.pc.R;
import com.bruce.pc.ViewId;

public class HeadFeedCardView extends ConstraintLayout {
    private ImageView imageView;
    private TextView titleView;
    private TextView descView;

    public HeadFeedCardView(Context context) {
        this(context, null);
    }

    public HeadFeedCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeadFeedCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackground(ViewFactory.getBackgroundDrawableForFeedItem());
        imageView = new ImageView(context);
        titleView = new TextView(context);
        descView = new TextView(context);

        titleView.setLines(1);
        titleView.setTextAppearance(R.style.TitleTextAppearance);
        descView.setTextAppearance(R.style.DescriptionTextAppearance);
        titleView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        descView.setLines(2);
        titleView.setEllipsize(TextUtils.TruncateAt.END);
        descView.setEllipsize(TextUtils.TruncateAt.END);
        imageView.setId(ViewId.imageViewId);

        titleView.setId(ViewId.titleViewId);
        ConstraintLayout.LayoutParams layoutParamsForImage = new ConstraintLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0);
        ConstraintLayout.LayoutParams layoutParamsForText = new ConstraintLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        addView(imageView, layoutParamsForImage);
        addView(titleView, layoutParamsForText);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this);
        constraintSet.connect(ViewId.titleViewId, ConstraintSet.TOP, ViewId.imageViewId, ConstraintSet.BOTTOM);
        constraintSet.setDimensionRatio(ViewId.imageViewId, "16:9");
        constraintSet.applyTo(this);
        int padding = getResources().getDimensionPixelSize(R.dimen.padding);
        titleView.setPadding(padding, 0, padding, 0);
        setPadding(0, 0, padding*2, 0);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTitleView() {
        return titleView;
    }

    public TextView getDescView() {
        return descView;
    }
}
