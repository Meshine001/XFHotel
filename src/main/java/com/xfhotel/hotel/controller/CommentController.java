package com.xfhotel.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.Comment;
import com.xfhotel.hotel.service.CommentService;
import com.xfhotel.hotel.support.Message;

@Controller
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	CommentService commentService;
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody Message getRoomComments(Long roomId){
		try {
			List<Comment> comments = commentService.getCommentsByRoom(roomId);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, comments);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "服务器错误");
		}
	}
}
