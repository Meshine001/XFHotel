package com.xfhotel.hotel.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.entity.Room;
import com.xfhotel.hotel.service.OrderService;
import com.xfhotel.hotel.service.RoomService;

@Component
public class OrderTask{
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	RoomService roomService;
	
	/**
	 * 定时刷新订单对应的房间的状态，
	 * 将过期订单的房间恢复搜索
	 * 每20分钟执行一次
	 */
	@Scheduled(cron = "0 0/20 * * * ?")
	public void refreshRoomStatus(){
		List<Order> diedOrders = orderService.listDiedOrders(Order.TYPE_HOTEL);
		for(Order o:diedOrders){
			Room room = roomService.findById(o.getRoomId());
			room.setStatus(Room.STATUS_IDLE);
			roomService.update(room);
		}
	}
}
