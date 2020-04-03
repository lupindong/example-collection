package net.lovexq.example.disruptor.parking;

import com.lmax.disruptor.EventHandler;

/**
 *
 * @author LuPindong
 * @time 2020-04-03 16:18
 */
public class InParkingData2KafkaHandler implements EventHandler<InParkingDataEvent> {

    @Override
    public void onEvent(InParkingDataEvent event, long sequence, boolean endOfBatch) throws Exception {
        long threadId = Thread.currentThread().getId();
        String carLicense = event.getCarLicense();
        System.out.println(String.format("Thread Id %s 推送 %s 到Kafka ....", threadId, carLicense));
    }

}
