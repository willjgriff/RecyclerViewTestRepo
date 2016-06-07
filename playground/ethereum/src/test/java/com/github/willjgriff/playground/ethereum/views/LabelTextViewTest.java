package com.github.willjgriff.playground.ethereum.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.github.willjgriff.playground.BuildConfig;
import com.github.willjgriff.playground.R;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

/**
 * Created by Will on 06/06/2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class LabelTextViewTest {

    LabelTextView mViewSubject;
    @Mock
    Context mMockContext;
    @Mock
    TypedArray mMockTypedArray;
    @Mock
    AttributeSet mMockAttributeSet;

    @Before
    public void setupLabelTextView() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLabelTextViewLabelAttribute_setsTheLabel() {
        String expectedLabel = "el Label";
        setupMockContext(expectedLabel);

        mViewSubject = new LabelTextView(mMockContext, mMockAttributeSet);

        TextView actualView = (TextView) mViewSubject.findViewById(R.id.view_label_text_label);
        Assert.assertEquals(expectedLabel, actualView.getText());
    }

    private void setupMockContext(String title) {
        mMockContext = RuntimeEnvironment.application;
        mMockContext = Mockito.spy(mMockContext);
        Mockito.doReturn(title).when(mMockTypedArray).getString(R.styleable.LabelTextView_LabelTextLabel);
        Mockito.doReturn(mMockTypedArray).when(mMockContext).obtainStyledAttributes(mMockAttributeSet, R.styleable.LabelTextView);
    }
}
