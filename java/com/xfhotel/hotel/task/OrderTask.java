package com.xfhotel.hotel.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xfhotel.hotel.service.OrderService;

@Component
public class OrderTask{
	
	@Autowired
	OrderService orderService;
	

	
	/**
	 * 定时刷新订单对应的房间的状态，
	 * 将过期订单的房间恢复搜索
	 * 每20分钟执行一次
	 */
	@Scheduled(cron = "0 0/20 * * * ?")
	public void refreshRoomStatus(){
		
	}
}
