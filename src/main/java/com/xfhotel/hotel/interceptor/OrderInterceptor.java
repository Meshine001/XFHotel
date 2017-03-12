package com.xfhotel.hotel.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.service.OrderService;

public class OrderInterceptor implements HandlerInterceptor {

	@Autowired
	OrderService orderService;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String uri =  request.getRequestURI();
		Long orderId = Long.valueOf(uri.split("/")[4]);
//		System.out.println(orderId);
		Order o = orderService.get(orderId);
		int status = o.getStatus();
		//超时
		if(orderService.isTimeOut(o)){
			status = Order.STATUS_TIME_OUT;
			o.setStatus(status);
			orderService.update(o);
		}
		StringBuffer sb = new StringBuffer();
		
		switch (status) {
		case Order.STATUS_ON_PAY:
			return true;
		case Order.STATUS_TIME_OUT:
			sb.append("订单超时");
			break;
		case Order.STATUS_COMPLETE:
			sb.append("订单已完成");
			break;
		case Order.STATUS_CANCEL:
			sb.append("订单已取消");
			break;
		default:
			break;
		}
		//TODO 中文乱码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8");
		response.sendRedirect("/hotel/order/msg?msg="+status);
		return false;
	}

}
