package com.xfhotel.hotel.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.entity.Comment;
import com.xfhotel.hotel.entity.Customer;
import com.xfhotel.hotel.entity.CustomerDetails;
import com.xfhotel.hotel.service.CommentService;
import com.xfhotel.hotel.service.CustomerService;
import com.xfhotel.hotel.support.PageResults;

@Controller
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	CommentService commentService;
	
	@Autowired
	CustomerService customerService;
	/**
	 * 获取某房间的评价
	 * @param roomId 房间Id
	 * @param page 第几页
	 * @return
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody PageResults<Comment> getRoomComments(Long roomId,Integer page){
		return commentService.getComments(roomId, page);
	}
	@RequestMapping(value = "/getComments", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Object> getComments(Long roomId,Integer page){
		ArrayList<Object> list = new ArrayList<Object>();
		PageResults<Comment> comments = commentService.getComments(roomId, page);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("CurrentPage", comments.getCurrentPage());
		map1.put("PageCount", comments.getPageCount());
		map1.put("PageNo", comments.getPageNo());
		map1.put("PageSize",comments.getPageSize());
		list.add(map1);
		for(Comment comment1:comments.getResults()){
			Customer customer = customerService.getCustomer(comment1.getFromWho());
			CustomerDetails f = customer.getDetails();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tel", customer.getTel());
			map.put("comment", comment1);
			map.put("Avatar", f.getAvatar());
			map.put("nick", f.getNick());
			list.add(map);
		}
		return list;
	}
	@RequestMapping(value = "/getRoomRates", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getRoomRates(Long roomId){
		return commentService.getRoomRates(roomId);
	}
}
