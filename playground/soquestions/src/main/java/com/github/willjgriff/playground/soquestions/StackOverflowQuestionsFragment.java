package com.github.willjgriff.playground.soquestions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.github.willjgriff.playground.PlaygroundApplication;
import com.github.willjgriff.playground.R;
import com.github.willjgriff.playground.dagger2.StackOverflowDagger;
import com.github.willjgriff.playground.network.model.stackoverflow.StackOverflowQuestion;
import com.github.willjgriff.playground.network.model.stackoverflow.StackOverflowQuestions;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Will on 13/03/2016.
 */
public class StackOverflowQuestionsFragment extends Fragment {

    private ArrayAdapter<Object> mAdapter;
    private ProgressBar mProgressBar;
    @Inject StackOverflowDagger mStackOverflow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stack_overflow_questions, container, false);
        PlaygroundApplication.app().getSoComponent().inject(this);

        ListView soQuestionsList = (ListView) view.findViewById(R.id.fragment_stack_overflow_questions_list);
        mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, new ArrayList<>());
        soQuestionsList.setAdapter(mAdapter);

        soQuestionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String soQuestionLink = ((StackOverflowQuestion) parent.getAdapter().getItem(position)).getLink();
                WebViewActivity.startInstance(getContext(), soQuestionLink);
            }
        });

        mProgressBar = (ProgressBar) view.findViewById(R.id.fragment_stack_overflow_questions_progress_bar);
        showProgressBar();
        loadStackOverflowQuestions();

        return view;
    }

    private void loadStackOverflowQuestions() {
        //asynchronous call (use call.execute() on a new thread for synchronous)
        mStackOverflow.androidQuestionsCall().enqueue(new Callback<StackOverflowQuestions>() {
            @Override
            public void onResponse(Response<StackOverflowQuestions> response, Retrofit retrofit) {
                mAdapter.addAll(response.body().getQuestions());
                dismissProgressBar();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Tag", "Failed to connect to Stack Overflow");
            }
        });
    }

    private void showProgressBar() {
        mAdapter.clear();
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void dismissProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }


}
