package com.github.willjgriff.playground;

import android.util.Log;
import android.widget.Button;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.android.view.OnClickEvent;
import rx.android.view.ViewObservable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by Will on 21/04/2016.
 */
public class RxJavaFun {

    public void play() {
        rxPlay();
        rxPlayLists();
        rxPlayThreads();
        rxPlayView();
    }

    private void rxPlay() {
        Observable<String> observable = Observable.just("QUE VAS");
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                log(s);
            }
        };
        Subscription subscription = observable.subscribe(observer);
    }

    private void rxPlayLists() {

        Observable<Integer> listObservable = Observable.from(new Integer[]{1, 2, 3, 4});

        listObservable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                log(String.valueOf(integer));
            }
        });


        listObservable.map(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                return integer * integer;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                log(String.valueOf(integer));
            }
        });


        listObservable.skip(1).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer % 2 == 0;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                log(String.valueOf(integer));
            }
        });

    }

    private void rxPlayThreads() {

        Observable<String> networkObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                // Call subscriber explicitly.
                try {
                    // Do long running operation that will execute on a thread specified in subscribeOn()
                    String data = "bejazzled";
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

        Observable<String> networkObservableDua = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                // Call subscriber explicitly.
                try {
                    // Do long running operation that will execute on a thread specified in subscribeOn()
                    String data = "jobazzled";
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

        networkObservable
                // Wait on a newThread
                .subscribeOn(Schedulers.newThread())
                // Execute functionality on the mainThread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        log(s + " We're on the MainThread! Use a view or something! ");
                    }
                });


        // Set each to run on a new thread
        networkObservable = networkObservable.subscribeOn(Schedulers.newThread());
        networkObservableDua = networkObservableDua.subscribeOn(Schedulers.newThread());

        // Do both simultaneously
        Observable.zip(networkObservable, networkObservableDua, new Func2<String, String, String>() {
            @Override
            public String call(String s, String s2) {
                return s + " " + s2;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                log(s);
            }
        });


        // Emit the results sequentially
        Observable.concat(networkObservable, networkObservableDua)
                .subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                // Expect 2 results...
                log(s);
            }
        });
    }

    /**
     * This shit won't work! But for demonstration purposes
     */
    private void rxPlayView() {
        Button button = new Button(PlaygroundApplication.app());

        Observable<OnClickEvent> clickObservable = ViewObservable.clicks(button);

        clickObservable
                // Skip first click...
                .skip(1)
                .subscribe(new Action1<OnClickEvent>() {
            @Override
            public void call(OnClickEvent onClickEvent) {
                log("Buttonwa Clicketh");
            }
        });

        // Ah maybe it will...
        button.callOnClick();
        button.callOnClick();
    }

    private void log(String message) {
        Log.d("Observer out", message);
    }
}
