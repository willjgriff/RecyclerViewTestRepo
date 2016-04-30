package com.github.willjgriff.playground.ethereum.views;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.will.Playground.R;
import com.github.willjgriff.playground.ethereum.presenters.BlockPresenter;
import com.github.willjgriff.playground.ethereum.presenters.BlockPresenterImpl;
import com.github.willjgriff.playground.mvp.RxMvp.RxView.RxMvpFragment;

/**
 * Created by Will on 18/04/2016.
 */
public class BlockFragment extends RxMvpFragment<BlockPresenter> implements BlockView {

    LabelTextView mTotalBlockCount;
    TextView mBlockHash;
    ImageView mPickaxeLeft;
    ImageView mPickaxeRight;


    @Override
    protected BlockPresenter createPresenter() {
        return new BlockPresenterImpl();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_block, container, false);
        mTotalBlockCount = (LabelTextView) view.findViewById(R.id.fragment_block_total_block_count);
        mBlockHash = (TextView) view.findViewById(R.id.fragment_block_current_hash);
        mPickaxeLeft = (ImageView) view.findViewById(R.id.fragment_block_pickaxe_left);
        mPickaxeRight = (ImageView) view.findViewById(R.id.fragment_block_pickaxe_right);

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                Animator animLeft = AnimatorInflater.loadAnimator(getContext(), R.animator.pickaxe_left_animator);
                animLeft.setTarget(mPickaxeLeft);
                animLeft.start();

                Animator animRight = AnimatorInflater.loadAnimator(getContext(), R.animator.pickaxe_right_animator);
                animRight.setTarget(mPickaxeRight);
                animRight.setStartDelay(200);
                animRight.start();
            }
        });

        return view;
    }

    @Override
    public void setBlockCount(long count) {
        mTotalBlockCount.setDescription(String.valueOf(count));
    }

    @Override
    public void setBlockHash(String hash) {
        mBlockHash.setText(hash);
    }


}
