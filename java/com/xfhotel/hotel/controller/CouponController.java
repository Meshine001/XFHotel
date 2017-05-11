package com.xfhotel.hotel.controller;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


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
	CustomerService customerService;
	
	
	@RequestMapping(value = "/sendlist", method = RequestMethod.POST)
	public @ResponseBody Message sendlist(String startTime,String endTime,int type,Double cValue,String rule,String Id[]) {
		System.out.println(startTime+endTime+type+cValue+rule+"+++"+Id);
		try {	
			String[] arr = new String[] {"1", "2"};
			List<String> list = Arrays.asList(arr);
			System.out.println(list);
			List<String> UserId = Arrays.asList(Id);
			for(String dd : list){
				System.out.println("ss");
				Long uId=Long.parseLong(dd);
				Coupon coupon = new Coupon();
				coupon.setcValue(cValue);
				coupon.setStartTime(startTime);
				coupon.setEndTime(endTime);
				coupon.setRule(rule);
				coupon.setuId(uId);
				couponService.add(coupon);
				System.out.println("dsdsdsd");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "添加失败");
		}
		return new Message(Constants.MESSAGE_SUCCESS_CODE, "添加成功");
	}
	@RequestMapping(value = "/dsendlist", method = RequestMethod.POST)
	public @ResponseBody Message dsendlist(Double money , String sex , Double time){
		Customer draw = new Customer();
		try{
			
			System.out.println(money);
			System.out.println(sex);
			System.out.println(time);
			if(money!=null){
				List<Customer> c = customerService.list();
				for(Customer customer : c){
					if(customer.getConsumptionCount()>money){
						 draw = customer;
					}
				}
			}else if(sex!=null){
				List<CustomerDetails> cd = customerService.getlist();
				
				for(CustomerDetails customer : cd){
					
					if(customer.getSex()==sex){
						System.out.println("dsds");
						draw=  customerService.getCustomer(customer.getId());
					}
				}
			}else if(time!=null){
				System.out.println("faf");
				List<Customer> c = customerService.list();
				if(time==0){
					for (Customer customer : c) {
						 Calendar calendar = Calendar.getInstance();
					        Date date = new Date(System.currentTimeMillis());
					        calendar.setTime(date);
//					        calendar.add(Calendar.WEEK_OF_YEAR, -1);
					        calendar.add(Calendar.YEAR, -1);
					        date = calendar.getTime();
					        long ddd = dateToLong(date);
						if(customer.getRegTime()>ddd){
							draw = customer;
						}
				    }
				}else{
					System.out.println("00dsd");
					for (Customer customer : c) {
						 Calendar calendar = Calendar.getInstance();
					        Date date = new Date(System.currentTimeMillis());
					        calendar.setTime(date);
					        calendar.add(Calendar.WEEK_OF_YEAR, -1);
//					        calendar.add(Calendar.YEAR, -1);
					        date = calendar.getTime();
					        long ddd = dateToLong(date);
						if(customer.getRegTime()<ddd){
							draw = customer;
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "查找失败");
			}
		return new Message(Constants.MESSAGE_SUCCESS_CODE, draw);
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
		
		
		

