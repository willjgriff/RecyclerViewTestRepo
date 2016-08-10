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
import com.github.willjgriff.playground.network.dagger2.retrofitapis.ProdStackOverflowDagger;
import com.github.willjgriff.playground.network.dagger2.retrofitapis.StackOverflowDagger;
import com.github.willjgriff.playground.network.model.stackoverflow.StackOverflowQuestion;
import com.github.willjgriff.playground.network.model.stackoverflow.StackOverflowQuestions;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Will on 13/03/2016.
 */
public class StackOverflowQuestionsFragment extends Fragment {

    private ArrayAdapter<StackOverflowQuestion> mAdapter;
    private ProgressBar mProgressBar;
    @Inject
    StackOverflowDagger mStackOverflow;
    private Call<StackOverflowQuestions> mAndroidQuestionsCall;

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
        mAndroidQuestionsCall = mStackOverflow.androidQuestionsCall();
        mAndroidQuestionsCall.enqueue(new Callback<StackOverflowQuestions>() {
            @Override
            public void onResponse(Call<StackOverflowQuestions> call, Response<StackOverflowQuestions> response) {
                mAdapter.addAll(response.body().getQuestions());
                dismissProgressBar();
            }

            @Override
            public void onFailure(Call<StackOverflowQuestions> call, Throwable t) {
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

    @Override
    public void onDestroy() {
        if (mAndroidQuestionsCall != null) {
            mAndroidQuestionsCall.cancel();
            mAndroidQuestionsCall = null;
        }
        super.onDestroy();
    }
}
