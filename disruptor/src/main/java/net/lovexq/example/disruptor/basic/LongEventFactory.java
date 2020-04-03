package net.lovexq.example.disruptor.basic;

import com.lmax.disruptor.EventFactory;

/**
 * @author LuPindong
 * @time 2020-04-03 16:41
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}