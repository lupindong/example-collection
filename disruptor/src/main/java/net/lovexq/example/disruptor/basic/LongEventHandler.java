package net.lovexq.example.disruptor.basic;

import com.lmax.disruptor.EventHandler;

/**
 * @author LuPindong
 * @time 2020-04-03 16:42
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        System.out.println("Event: " + event + ", sequence:" + sequence + ", endOfBatch: " + endOfBatch);
    }
}