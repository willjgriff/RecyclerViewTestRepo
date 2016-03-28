package com.github.willjgriff.playground.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.will.Playground.R;
import com.github.willjgriff.playground.api.endpoints.StackOverflowApi;
import com.github.willjgriff.playground.api.model.stackoverflow.StackOverflowQuestion;
import com.github.willjgriff.playground.api.model.stackoverflow.StackOverflowQuestions;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Will on 13/03/2016.
 */
public class WebFragment extends Fragment {

    ArrayAdapter<StackOverflowQuestion> mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web, container, false);

        ListView soQuestionsList = (ListView) view.findViewById(R.id.fragment_web_questions_list);
        mAdapter = new ArrayAdapter<>(getActivity(),
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        new ArrayList<StackOverflowQuestion>());
        soQuestionsList.setAdapter(mAdapter);

        view.findViewById(R.id.fragment_web_get_questions).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadSoQuestions();
            }
        });

        return view;
    }

    private void loadSoQuestions() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.stackexchange.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StackOverflowApi stackOverflowAPI = retrofit.create(StackOverflowApi.class);

        Call<StackOverflowQuestions> call = stackOverflowAPI.loadQuestions("android");
        //asynchronous call (use call.execute() on a new thread for synchronous)
        call.enqueue(new Callback<StackOverflowQuestions>() {
            @Override
            public void onResponse(Response<StackOverflowQuestions> response, Retrofit retrofit) {
                mAdapter.clear();
                mAdapter.addAll(response.body().getQuestions());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), "Failed to connect to Stack Overflow", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
