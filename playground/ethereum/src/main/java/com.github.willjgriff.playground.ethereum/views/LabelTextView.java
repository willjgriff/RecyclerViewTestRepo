package com.github.willjgriff.playground.ethereum.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.willjgriff.playground.R;

import java.util.ArrayList;

/**
 * Created by Will on 26/04/2016.
 */
public class LabelTextView extends LinearLayout {

    private TextView mLabel;
    private TextView mDescription;
    private Paint mPaint;

    public LabelTextView(Context context) {
        super(context);
        setup(context, null);
    }

    public LabelTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(context, attrs);
    }

    public LabelTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LabelTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setup(context, attrs);
    }

    private void setup(Context context, AttributeSet attrs) {
        setupAttrs(context, attrs);
        setupPaint(context);
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCheckers(canvas, 6, 40);

        Drawable backgroundGradient = ContextCompat.getDrawable(getContext(), R.drawable.label_text_view_gradient);
        backgroundGradient.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        backgroundGradient.draw(canvas);
    }

    // I don't know if this is at all efficient. I expect it isn't.
    private void drawCheckers(Canvas canvas, int height, int width) {
        int checkerHeight = getMeasuredHeight() / height;
        int checkerWidth = getMeasuredWidth() / width;
        int checkerTop;
        int checkerLeft;
        int color;

        ArrayList<Integer> coloursList = new ArrayList<>();
        coloursList.add(R.color.accent);
        coloursList.add(R.color.primary);
        coloursList.add(R.color.yellow);
        coloursList.add(R.color.aqua);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j+=2) {
                color = coloursList.get((i + j) % coloursList.size());
                mPaint.setColor(ContextCompat.getColor(getContext(), color));
                checkerTop = checkerHeight * i;
                checkerLeft = i % 2 == 0 ? checkerWidth * j : (checkerWidth * j) + checkerWidth;
                canvas.drawRect(checkerLeft, checkerTop, checkerLeft + checkerWidth, checkerTop + checkerHeight, mPaint);
            }
        }
    }

    private void setupPaint(Context context) {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
    }

    private void setupAttrs(Context context, AttributeSet attrs) {
        setOrientation(LinearLayout.HORIZONTAL);
        LayoutInflater.from(context).inflate(R.layout.view_label_text, this);
        mLabel = (TextView) findViewById(R.id.view_label_text_label);
        mDescription = (TextView) findViewById(R.id.view_label_text_description);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LabelTextView);
        try {
            String labelText = typedArray.getString(R.styleable.LabelTextView_LabelTextLabel);
            mLabel.setText(labelText);
            String descriptionText = typedArray.getString(R.styleable.LabelTextView_LabelTextDescription);
            mDescription.setText(descriptionText);
        } finally {
            typedArray.recycle();
        }

        int padding = getResources().getDimensionPixelSize(R.dimen.xxsmall);
        setPadding(padding, padding, padding, padding);
        setBackground(ContextCompat.getDrawable(context, R.drawable.label_text_view_gradient));
    }

    public void setLabel(String label) {
        mLabel.setText(label);
    }

    public void setDescription(String description) {
        mDescription.setText(description);
        // Maybe do the below (check if in setText())
//        invalidate();
//        requestLayout();
    }
}
