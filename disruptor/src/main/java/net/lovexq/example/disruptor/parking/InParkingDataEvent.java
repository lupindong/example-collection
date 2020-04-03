package net.lovexq.example.disruptor.parking;

import lombok.Data;

/**
 * 停车事件
 *
 * @author LuPindong
 * @time 2020-04-03 16:02
 */
@Data
public class InParkingDataEvent {

    private String carLicense;

}
