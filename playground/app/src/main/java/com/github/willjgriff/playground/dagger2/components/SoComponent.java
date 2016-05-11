package com.github.willjgriff.playground.dagger2.components;

import com.github.willjgriff.playground.dagger2.modules.AppModule;
import com.github.willjgriff.playground.dagger2.modules.StackOverflowModule;
import com.github.willjgriff.playground.soquestions.StackOverflowQuestionsFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Will on 11/05/2016.
 */
@Singleton
@Component(modules={AppModule.class, StackOverflowModule.class})
public interface SoComponent {

    // Injection targets.
    void inject(StackOverflowQuestionsFragment fragment);
}
