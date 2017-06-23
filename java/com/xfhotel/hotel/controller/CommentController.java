package com.xfhotel.hotel.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.Comment;
import com.xfhotel.hotel.entity.Customer;
import com.xfhotel.hotel.entity.CustomerDetails;
import com.xfhotel.hotel.service.CommentService;
import com.xfhotel.hotel.service.CustomerService;
import com.xfhotel.hotel.support.Message;
import com.xfhotel.hotel.support.PageResults;

@Controller
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	CommentService commentService;
	
	@Autowired
	CustomerService customerService ;
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("CurrentPage", comments.getCurrentPage());
		map.put("PageCount", comments.getPageCount());
		map.put("PageNo", comments.getPageNo());
		map.put("PageSize",comments.getPageSize());
		list.add(map);
		return list;
	}
	
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public @ResponseBody Message comment(Long id , String reply){
		try {
			Comment comm =commentService.findById(id);
			comm.setReply(reply);
			commentService.update(comm);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new Message(Constants.MESSAGE_ERR_CODE, "回复失败");
				}
		return new Message(Constants.MESSAGE_SUCCESS_CODE, "回复成功");
	}
	
	@RequestMapping(value = "/getRoomRates", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getRoomRates(Long roomId){
		return commentService.getRoomRates(roomId);
	}
	
	@RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
	public @ResponseBody Message deleteComment(Long id ){
		try {
			Comment comm =commentService.findById(id);
			commentService.delete(comm);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new Message(Constants.MESSAGE_ERR_CODE, "删除失败");
				}
		return new Message(Constants.MESSAGE_SUCCESS_CODE, "删除成功");
	}
	
}
