package com.github.willjgriff.playground.ethereum.presenters;

import android.support.annotation.NonNull;

import com.github.willjgriff.playground.ethereum.views.BlockView;
import com.github.willjgriff.playground.mvp.RxMvp.RxPresenter.RxBasePresenter;
import com.github.willjgriff.playground.network.Etherchain;
import com.github.willjgriff.playground.network.model.ethereum.Block;
import com.github.willjgriff.playground.network.model.ethereum.BlockCount;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by Will on 18/04/2016.
 */
public class BlockPresenterImpl extends RxBasePresenter<BlockView> implements BlockPresenter {

    @Override
    public void bindView(@NonNull BlockView blockView) {
        super.bindView(blockView);

        addSubscription(Etherchain.blockCountCall(), new Action1<BlockCount>() {
            @Override
            public void call(BlockCount blockCount) {
                view().setBlockCount(blockCount.getCount());
            }
        });

        addSubscription(Etherchain.blockList(0, 3), new Action1<List<Block>>() {
            @Override
            public void call(List<Block> blocks) {
                view().setBlockHash(blocks.get(0).getHash());
            }
        });

    }
}
