package com.ztt.disrupter.demo3;

import com.lmax.disruptor.EventHandler;
import com.ztt.disrupter.demo1.TradeTransaction;

public class TradeTransactionVasConsumer implements EventHandler<TradeTransaction> {

    @Override
    public void onEvent(TradeTransaction event, long sequence, boolean endOfBatch) throws Exception {
        //do something....
    }

}
