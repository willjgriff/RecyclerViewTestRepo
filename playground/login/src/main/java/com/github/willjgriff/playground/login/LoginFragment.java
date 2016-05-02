package com.github.willjgriff.playground.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.will.Playground.R;
import com.github.willjgriff.playground.mvp.RxMvp.RxView.RxMvpFragment;

import rx.Observable;
import rx.android.widget.OnTextChangeEvent;
import rx.android.widget.WidgetObservable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by Will on 02/05/2016.
 */
public class LoginFragment extends RxMvpFragment<LoginPresenter> implements LoginView {

    EditText mUsername;
    EditText mPassword;
    Button mSignup;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        mUsername = (EditText) view.findViewById(R.id.fragment_signup_username);
        mPassword = (EditText) view.findViewById(R.id.fragment_signup_password1);
        mSignup = (Button) view.findViewById(R.id.fragment_signup_button);

        Observable<Boolean> usernameObservable = WidgetObservable.text(mUsername)
                .map(new Func1<OnTextChangeEvent, Boolean>() {
                    @Override
                    public Boolean call(OnTextChangeEvent onTextChangeEvent) {
                        return onTextChangeEvent.text().length() > 4;
                    }
                });

        // TODO: Would like to understand how to do multiple mappings without using a filter.
        Observable<Boolean> passwordObservable = WidgetObservable.text(mPassword)
                .map(new Func1<OnTextChangeEvent, Boolean>() {
                    @Override
                    public Boolean call(OnTextChangeEvent onTextChangeEvent) {
                        return onTextChangeEvent.text().length() > 4
                                && String.valueOf(onTextChangeEvent.text()).contains("a");
                    }
                });
        
        Observable.combineLatest(passwordObservable, usernameObservable, new Func2<Boolean, Boolean, Boolean>() {
            @Override
            public Boolean call(Boolean passwordValid, Boolean usernameValid) {
                return passwordValid && usernameValid;
            }
        }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean buttonClickable) {
                mSignup.setEnabled(buttonClickable);
            }
        });

        return view;
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenterImpl();
    }
}
