package com.bruce.pc.blogfeeds;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.bruce.pc.R;
import com.bruce.pc.ViewId;

public class FeedCardView extends ConstraintLayout {
    private ImageView imageView;
    private TextView titleView;

    public FeedCardView(Context context) {
        this(context, null);
    }

    public FeedCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FeedCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        int padding = getResources().getDimensionPixelSize(R.dimen.padding);
        setBackground(ViewFactory.getBackgroundDrawableForFeedItem());
        imageView = new ImageView(context);
        titleView = new TextView(context);
        titleView.setLines(2);
        titleView.setTextAppearance(R.style.TitleTextAppearance);
        titleView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        imageView.setId(ViewId.imageViewId);
        titleView.setId(ViewId.titleViewId);
        ConstraintLayout.LayoutParams layoutParamsForImage = new ConstraintLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0);
        ConstraintLayout.LayoutParams layoutParamsForText = new ConstraintLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        ConstraintSet constraintSet = new ConstraintSet();
        addView(imageView, layoutParamsForImage);
        addView(titleView, layoutParamsForText);
        constraintSet.clone(this);
        constraintSet.connect(ViewId.titleViewId, ConstraintSet.TOP, ViewId.imageViewId, ConstraintSet.BOTTOM);
        constraintSet.setDimensionRatio(ViewId.imageViewId, "16:9");
        constraintSet.applyTo(this);
        titleView.setPadding(padding, 0, padding, 0);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTitleView() {
        return titleView;
    }
}
