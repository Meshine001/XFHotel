package com.xfhotel.hotel.service.impl;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.impl.CommentDAOImpl;
import com.xfhotel.hotel.entity.Comment;
import com.xfhotel.hotel.service.CommentService;
import com.xfhotel.hotel.support.PageResults;

@Service
public class CommentServiceImpl implements CommentService{

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

	@Transactional
	@Override
	public PageResults<Comment> getComments(Long roomId, int page) {
		StringBuffer s = new StringBuffer();
		s.append("from Comment comment where comment.roomId=");
		s.append(roomId).append("order by comment.time desc");
		StringBuffer s1 = new StringBuffer();
		s1.append("select count(*) from Comment comment where comment.roomId=").append(roomId);
		return commentDAO.findPageByFetchedHql(s.toString(), s1.toString(), page, 5,null);
	}

	@Transactional
	@Override
	public Map<String, Object> getRoomRates(Long roomId) {
		StringBuffer sb = new StringBuffer("from Comment where roomId=");
		sb.append(roomId);
		List<Comment> comments = commentDAO.getListByHQL(sb.toString(), null);
		Double pingjun = 5.0;
		Double weisheng = 5.0;
		Double miaoshu = 5.0;
		Double jiaotong = 5.0;
		Double anquan = 5.0;
		Double xingjiabi = 5.0;
		for(Comment c:comments){
			String[] scores = c.getScore();
			weisheng = (weisheng+Double.valueOf(scores[0]))/2;
			miaoshu = (miaoshu+Double.valueOf(scores[1]))/2;
			System.out.println(miaoshu);
			jiaotong = (jiaotong+Double.valueOf(scores[2]))/2;
			System.out.println(jiaotong);
			anquan = (anquan+Double.valueOf(scores[3]))/2;
			
			xingjiabi = (xingjiabi+Double.valueOf(scores[4]))/2;
		}
		pingjun = (weisheng + miaoshu+jiaotong+anquan+xingjiabi)/5;
		DecimalFormat df = new DecimalFormat ("#.#");//显示一位小数?好像不起作用
		Map<String, Object> rates = new HashMap<String, Object>();
		rates.put("pingjun", df.format(pingjun));
		rates.put("weisheng", df.format(weisheng));
		rates.put("miaoshu", df.format(miaoshu));
		rates.put("jiaotong", df.format(jiaotong));
		rates.put("anquan", df.format(anquan));
		rates.put("xingjiabi", df.format(xingjiabi));
		return rates;
	}

	@Override
	public Comment findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


}
