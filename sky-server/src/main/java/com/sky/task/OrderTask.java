package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 定时任务类，定时处理订单状态
 */
@Component
@Slf4j
public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 处理超时订单的方法
     */
    @Scheduled(cron = "0 * * * * ? ")   //每分钟执行一次
    public void processTimeoutOrder() {

        LocalDateTime time = LocalDateTime.now().plusMinutes(-15); // 当前时间减去15分钟
        List<Orders> list = orderMapper.
                getByStatusAndOrderTimeLT(Orders.PENDING_PAYMENT, time);

        if(list != null && list.size() > 0) {
            for (Orders order : list) {
                order.setStatus(Orders.CANCELLED);
                order.setCancelReason("订单超时未支付，自动取消...");
                order.setCancelTime(LocalDateTime.now());
                // orderMapper.update(order);
            }
        }
    }

}
