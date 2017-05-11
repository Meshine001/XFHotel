package com.xfhotel.hotel.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.Coupon;
import com.xfhotel.hotel.entity.Customer;
import com.xfhotel.hotel.entity.CustomerDetails;
import com.xfhotel.hotel.service.CustomerService;
import com.xfhotel.hotel.service.impl.CouponService;
import com.xfhotel.hotel.support.Message;

@Controller
@RequestMapping("/coupon")
public class CouponController {
	@Autowired
	CouponService couponService;
	@Autowired
	HttpSession session;
	@Autowired 
	CustomerService customerService;
	
	
	@RequestMapping(value = "/creation", method = RequestMethod.POST)
	public @ResponseBody Message creation(Long startTime,Long endTime,int type,Double cValue,String rule, String[] Id) {
		try {
			for(String dd : Id){
				Long uId=Long.parseLong(dd);
				Coupon coupon = new Coupon();
				coupon.setcValue(cValue);
				coupon.setStartTime(startTime);
				coupon.setEndTime(endTime);
				coupon.setRule(rule);
				coupon.setuId(uId);
				couponService.add(coupon);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "添加失败");
		}
		return new Message(Constants.MESSAGE_SUCCESS_CODE, "添加成功");
	}
	@RequestMapping(value = "/sendlist", method = RequestMethod.POST)
	public @ResponseBody Message dd(Double money , String sex , Double time){
		try{
			if(money!=null){
				List<Customer> c = customerService.list();
				for(Customer customer : c){
					if(customer.getConsumptionCount()>money){
						session.setAttribute("customer", customer);
					}
				}
			}else if(sex!=null){
				List<CustomerDetails> cd = customerService.getlist();
				for(CustomerDetails customer : cd){
					if(customer.getSex()==sex){
						Customer customer2=  customerService.getCustomer(customer.getId());
						session.setAttribute("customer2", customer2);
					}
				}
			}else if(time!=null){
				List<Customer> c = customerService.list();
				if(time==1){
					for (Customer customer : c) {
						 Calendar calendar = Calendar.getInstance();
					        Date date = new Date(System.currentTimeMillis());
					        calendar.setTime(date);
//					        calendar.add(Calendar.WEEK_OF_YEAR, -1);
					        calendar.add(Calendar.YEAR, -1);
					        date = calendar.getTime();
					        long ddd = dateToLong(date);
						if(customer.getRegTime()>ddd){
							session.setAttribute("customer", customer);
						}
				    }
				}else{
					for (Customer customer : c) {
						 Calendar calendar = Calendar.getInstance();
					        Date date = new Date(System.currentTimeMillis());
					        calendar.setTime(date);
					        calendar.add(Calendar.WEEK_OF_YEAR, -1);
//					        calendar.add(Calendar.YEAR, -1);
					        date = calendar.getTime();
					        long ddd = dateToLong(date);
						if(customer.getRegTime()<ddd){
							session.setAttribute("customer", customer);
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "查找失败");
				}
		
		return new Message(Constants.MESSAGE_SUCCESS_CODE, "查找成功");
	} 

	private long dateToLong(Date date) {
		// TODO Auto-generated method stub
		return 0;
	}
	@RequestMapping(value = "/seek", method = RequestMethod.POST)
	public @ResponseBody List<Coupon> seek(Long uId) {
		return couponService.getCoupon(uId) ;
		
	}
	@RequestMapping(value = "/0",method = RequestMethod.GET)
	public @ResponseBody Coupon getCoupon(){
		Coupon c =  (Coupon) couponService.list();
		return c;
		}
	}
		
		
		

