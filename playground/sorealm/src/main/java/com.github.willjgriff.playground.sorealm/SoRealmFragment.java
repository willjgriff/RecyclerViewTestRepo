package com.github.willjgriff.playground.sorealm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.willjgriff.playground.R;
import com.github.willjgriff.playground.network.model.stackoverflow.StackOverflowQuestion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will on 11/07/2016.
 */

public class SoRealmFragment extends Fragment implements SoRealmContract.View {

	private SoRealmContract.Presenter mPresenter;
	private ArrayAdapter<StackOverflowQuestion> mAdapter;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_stack_overflow_questions, container, false);
		mPresenter = new SoRealmPresenter(this);

		setupSoList(view);

		return view;
	}

	private void setupSoList(View view) {
		ListView soQuestionsList = (ListView) view.findViewById(R.id.fragment_stack_overflow_questions_list);
		mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, new ArrayList<>());
		soQuestionsList.setAdapter(mAdapter);
	}

	@Override
	public void onResume() {
		super.onResume();
		mPresenter.start();
	}

	@Override
	public void onPause() {
		super.onPause();
		mPresenter.stop();
	}

	@Override
	public void addAll(List<StackOverflowQuestion> soQuestions) {
		mAdapter.addAll(soQuestions);
	}
}
