package net.lovexq.example.disruptor.parking;

import com.lmax.disruptor.EventHandler;

/**
 * @author LuPindong
 * @time 2020-04-03 16:18
 */
public class InParkingData2SMSHandler implements EventHandler<InParkingDataEvent> {

    @Override
    public void onEvent(InParkingDataEvent event, long sequence, boolean endOfBatch) throws Exception {
        long threadId = Thread.currentThread().getId();
        String carLicense = event.getCarLicense();
        System.out.println(String.format("Thread Id %s 给  %s 的车主发送一条短信，并告知他计费开始了 ....", threadId, carLicense));
    }

}
