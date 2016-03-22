package com.playground.will.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.will.Playground.R;

/**
 * Created by Will on 07/03/2016.
 */
public class PageFragment extends Fragment {

    public static final String EXTRA_PAGE_NUMBER = "playground.tabs.src.main.java.PageFragment;EXTRA_PAGE_NUMBER";

    private int mPageNumber;

    public static PageFragment newInstance(int pageNumber) {
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_PAGE_NUMBER, pageNumber);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(EXTRA_PAGE_NUMBER);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_layout_page, container, false);
        TextView pageText = (TextView) view.findViewById(R.id.fragment_tab_layout_page_text);
        pageText.setText(this.getString(R.string.fragment_tab_layout_page_number, mPageNumber));
        return view;
    }
}
