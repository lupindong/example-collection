package net.lovexq.example.disruptor.parking;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.CountDownLatch;

/**
 * @author LuPindong
 * @time 2020-04-03 16:57
 */
public class InParkingDataEventPublisher implements Runnable {
    // 用于监听初始化操作，等初始化执行完毕后，通知主线程继续工作
    private CountDownLatch countDownLatch;
    private Disruptor<InParkingDataEvent> disruptor;
    // 1,10,100,1000
    private static final Integer NUM = 1;

    public InParkingDataEventPublisher(CountDownLatch countDownLatch, Disruptor<InParkingDataEvent> disruptor) {
        this.countDownLatch = countDownLatch;
        this.disruptor = disruptor;
    }

    @Override
    public void run() {
        InParkingDataEventTranslator eventTranslator = new InParkingDataEventTranslator();
        try {
            for (int i = 0; i < NUM; i++) {
                disruptor.publishEvent(eventTranslator);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("异常信息：" + e.getMessage());
        } finally {
            countDownLatch.countDown();
            System.out.println(NUM + "辆车已经全部进入进入停车场！");
        }
    }

}

class InParkingDataEventTranslator implements EventTranslator<InParkingDataEvent> {

    @Override
    public void translateTo(InParkingDataEvent inParkingDataEvent, long sequence) {
        this.generateData(inParkingDataEvent);
    }

    private InParkingDataEvent generateData(InParkingDataEvent inParkingDataEvent) {
        // 随机生成一个车牌号
        inParkingDataEvent.setCarLicense("车牌号： 粤-" + (int) (Math.random() * 100000));
        System.out.println("Thread Id " + Thread.currentThread().getId() + " 写完一个event");
        return inParkingDataEvent;
    }

}