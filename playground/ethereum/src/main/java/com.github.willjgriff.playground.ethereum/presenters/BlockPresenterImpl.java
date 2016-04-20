package com.github.willjgriff.playground.ethereum.presenters;

import com.github.willjgriff.playground.ethereum.views.BlockView;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter.BasePresenter;
import com.github.willjgriff.playground.network.Etherchain;
import com.github.willjgriff.playground.network.model.ethereum.Block;
import com.github.willjgriff.playground.network.model.ethereum.BlockCount;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Will on 18/04/2016.
 */
public class BlockPresenterImpl extends BasePresenter<Block, BlockView> implements BlockPresenter {

    private BlockCount mBlockCount;

    public BlockPresenterImpl() {
//        Etherchain.blockList().enqueue(new Callback<EtherchainResponse>() {
//            @Override
//            public void onResponse(Response<EtherchainResponse> response, Retrofit retrofit) {
//                response.body().getData().get(0);
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//
//            }
//        });

        Etherchain.blockCountCall().enqueue(new Callback<BlockCount>() {
            @Override
            public void onResponse(Response<BlockCount> response, Retrofit retrofit) {
                mBlockCount = response.body();
                setModel(new Block());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @Override
    protected void updateView() {
        view().setBlockCount(mBlockCount.getCount());
//        view().setBlockHash(mModel.getHash());
    }
}
