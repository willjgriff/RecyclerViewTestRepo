package com.github.willjgriff.playground.ethereum.presenters;

import android.support.annotation.NonNull;
import android.util.Log;

import com.github.willjgriff.playground.ethereum.views.BlockView;
import com.github.willjgriff.playground.mvp.RxMvp.RxPresenter.RxBasePresenter;
import com.github.willjgriff.playground.network.api.Etherchain.EtherchainApiCalls;
import com.github.willjgriff.playground.network.model.ethereum.Block;
import com.github.willjgriff.playground.network.model.ethereum.BlockCount;
import com.github.willjgriff.playground.network.utils.PlaygroundSubscriber;

import java.util.List;

/**
 * Created by Will on 18/04/2016.
 */
public class BlockPresenterImpl extends RxBasePresenter<BlockView> implements BlockPresenter {

    @Override
    public void bindView(@NonNull BlockView blockView) {
        super.bindView(blockView);

        addSubscription(EtherchainApiCalls.blockCountCall(), new PlaygroundSubscriber<BlockCount>() {
            @Override
            public void onError(Throwable e) {
                Log.e("ERROR", "Etherchain block count request failed", e);
            }

            @Override
            public void onNext(BlockCount blockCount) {
                view().setBlockCount(blockCount.getCount());
            }
        });

        addSubscription(EtherchainApiCalls.blockList(0, 3), new PlaygroundSubscriber<List<Block>>() {
            @Override
            public void onError(Throwable e) {
                Log.e("ERROR", "Etherchain block list request failed", e);
            }

            @Override
            public void onNext(List<Block> blocks) {
                view().setBlockHash(blocks.get(0).getHash());
            }
        });
    }
}
