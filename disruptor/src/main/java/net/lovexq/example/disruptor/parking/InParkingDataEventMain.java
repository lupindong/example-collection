package net.lovexq.example.disruptor.parking;

import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author LuPindong
 * @time 2020-04-03 17:16
 */
public class InParkingDataEventMain {
    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();
        int bufferSize = 2048; // 2的N次方
        try {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            // 初始化一个 Disruptor
            Disruptor<InParkingDataEvent> disruptor = new Disruptor<>(InParkingDataEvent::new, bufferSize, DaemonThreadFactory.INSTANCE);

            // 使用disruptor创建消费者组 ParkingData2DbHandler 和 ParkingDataToKafkaHandler
            EventHandlerGroup<InParkingDataEvent> handlerGroup = disruptor.handleEventsWith(
                    new InParkingData2DBHandler(), new InParkingData2KafkaHandler());

            // 当上面两个消费者处理结束后在消耗 smsHandler
            InParkingData2SMSHandler inParkingDataSmsHandler = new InParkingData2SMSHandler();
            handlerGroup.then(inParkingDataSmsHandler);

            // 启动Disruptor
            disruptor.start();

            CountDownLatch countDownLatch = new CountDownLatch(10);
            // 生产者生成数据
            executor.submit(new InParkingDataEventPublisher(countDownLatch, disruptor));
            countDownLatch.await(); // 等待生产者结束

            disruptor.shutdown();
            executor.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("总耗时:" + (System.currentTimeMillis() - beginTime));
    }
}
