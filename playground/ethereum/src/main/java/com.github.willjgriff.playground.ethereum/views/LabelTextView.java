package com.github.willjgriff.playground.ethereum.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.will.Playground.R;

/**
 * Created by Will on 26/04/2016.
 */
public class LabelTextView extends LinearLayout {

    private TextView mLabel;
    private TextView mDescription;
    private Paint mPaint;

    public LabelTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupAttrs(context, attrs);
        setupPaint(context);
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, 100, 100, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    private void setupPaint(Context context) {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(ContextCompat.getColor(context, R.color.accent_alpha26));
    }

    private void setupAttrs(Context context, AttributeSet attrs) {
        setOrientation(LinearLayout.HORIZONTAL);
        LayoutInflater.from(context).inflate(R.layout.view_label_text, this);
        mLabel = (TextView) findViewById(R.id.view_label_text_label);
        mDescription = (TextView) findViewById(R.id.view_label_text_description);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LabelTextView, 0, 0);
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
    }

    public void setLabel(String label) {
        mLabel.setText(label);
        invalidate();
        requestLayout();
    }

    public void setmDescription(String description) {
        mDescription.setText(description);
        invalidate();
        requestLayout();
    }
}
