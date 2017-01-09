package com.xfhotel.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.impl.CommentDAOImpl;
import com.xfhotel.hotel.entity.Comment;

@Service
public class CommentService implements com.xfhotel.hotel.service.CommentService {
	
	@Autowired
	CommentDAOImpl commentDAO;

	@Transactional
	@Override
	public void add(Comment t) {
		commentDAO.save(t);
	}

	@Transactional
	@Override
	public void delete(Comment t) {
		commentDAO.delete(t);
	}

	@Transactional
	@Override
	public void update(Comment t) {
		commentDAO.update(t);
	}

	@Transactional
	@Override
	public List<Comment> list() {
		return commentDAO.getListByHQL("from Comment", null);
	}
	
	@Transactional
	@Override
	public List<Comment> getCommentsByRoom(Long roomId) {
		String hql = "from Comment where roomId=?";
		Object[] v = {roomId};
		return commentDAO.getListByHQL(hql, v);
	}

}
