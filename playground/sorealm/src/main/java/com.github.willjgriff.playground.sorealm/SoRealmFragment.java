package com.github.willjgriff.playground.sorealm;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.github.willjgriff.playground.R;
import com.github.willjgriff.playground.navigation.NavigationToolbarListener;
import com.github.willjgriff.playground.network.model.stackoverflow.StackOverflowQuestion;
import com.github.willjgriff.playground.sorealm.data.StackOverflowDataManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will on 11/07/2016.
 */

public class SoRealmFragment extends Fragment implements SoRealmContract.View {

	private SoRealmContract.Presenter mPresenter;
	private ArrayAdapter<StackOverflowQuestion> mAdapter;
	private File mRealmFile;
	private NavigationToolbarListener mNavToolbarListener;
	private ProgressBar mProgressBar;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_stack_overflow_questions, container, false);

		// TODO: Inject the DataManager using Dagger
		mPresenter = new SoRealmPresenter(this, new StackOverflowDataManager());

		setupSoQuestionsList(view);

		mProgressBar = (ProgressBar) view.findViewById(R.id.fragment_stack_overflow_questions_progress_bar);

		mRealmFile = new File(getActivity().getFilesDir(), "default.realm");

		return view;
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mNavToolbarListener = (NavigationToolbarListener) context;
	}

	private void setupSoQuestionsList(View view) {
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
		mPresenter.stop();
		super.onPause();
	}

	@Override
	public void addAll(List<StackOverflowQuestion> soQuestions) {
		mAdapter.addAll(soQuestions);

		// Monitor the size of the Realm for testing purposes.
		Log.d("RealmSize", "Realm Size: " + mRealmFile.length());
	}

	@Override
	public void clearData() {
		mAdapter.clear();
	}

	@Override
	public void showLoadingView() {
		mProgressBar.setVisibility(View.VISIBLE);
	}

	@Override
	public void showNetworkLoading() {
		mNavToolbarListener.showNetworkLoadingView();
	}

	@Override
	public void hideLoadingViews() {
		if (mProgressBar.getVisibility() != View.GONE) {
			mProgressBar.setVisibility(View.GONE);
		}
		mNavToolbarListener.hideNetworkLoadingView();
	}

}
