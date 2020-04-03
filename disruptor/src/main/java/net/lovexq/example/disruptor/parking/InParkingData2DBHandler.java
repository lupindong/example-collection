package net.lovexq.example.disruptor.parking;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 *
 * @author LuPindong
 * @time 2020-04-03 16:18
 */
public class InParkingData2DBHandler implements EventHandler<InParkingDataEvent>, WorkHandler<InParkingDataEvent> {

    @Override
    public void onEvent(InParkingDataEvent event, long sequence, boolean endOfBatch) throws Exception {
        this.onEvent(event);
    }

    @Override
    public void onEvent(InParkingDataEvent event) throws Exception {
        long threadId = Thread.currentThread().getId();
        String carLicense = event.getCarLicense();
        System.out.println(String.format("Thread Id %s 保存 %s 到数据库中 ....", threadId, carLicense));
    }
}
