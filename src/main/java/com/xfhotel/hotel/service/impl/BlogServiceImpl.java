package com.xfhotel.hotel.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.impl.BlogDAOImpl;
import com.xfhotel.hotel.entity.Blog;
import com.xfhotel.hotel.entity.Customer;
import com.xfhotel.hotel.service.BaseService;
import com.xfhotel.hotel.service.BlogService;
import com.xfhotel.hotel.support.BlogUploadUtil;
import com.xfhotel.hotel.support.PageResults;

@Service
public class BlogServiceImpl implements BlogService{
	@Autowired
	BlogDAOImpl blogDAO;

	@Override
	public void add(Blog t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Blog t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Blog t) {
		// TODO Auto-generated method stub
		
	}
	@Transactional
	@Override
	public List<Blog> list() {
		// TODO Auto-generated method stub
		return blogDAO.getListByHQL("from Blog", null);
	}

	@Override
	@Transactional
	public int publish(Blog blog, String content, String basepath) {
		// TODO Auto-generated method stub
		UUID uuid = UUID.randomUUID();
		String path = basepath + "blog\\";
		String filename = uuid.toString() + ".html";
		InputStream in = new ByteArrayInputStream(content.getBytes());
		try {
			BlogUploadUtil.upload4Stream(filename, path ,in);
			blog.setPath(filename);
			blog.setDate(System.currentTimeMillis());
			blogDAO.save(blog);
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	@Transactional
	public Blog find(Long id) {
		// TODO Auto-generated method stub
		return blogDAO.get(id);
	}

	@Override
	@Transactional
	public int update(Blog blog, String content, String basepath) {
		// TODO Auto-generated method stub
		String path = basepath + "blog\\";
		String filename = blog.getPath();
		InputStream in = new ByteArrayInputStream(content.getBytes());
		try {
			BlogUploadUtil.upload4Stream(filename, path ,in);
			blog.setDate(System.currentTimeMillis());
			blogDAO.update(blog);
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	@Transactional
	public PageResults<Blog> list(int page) {
		// TODO Auto-generated method stub
		return blogDAO.findPageByFetchedHql("from Blog blog order by blog.date desc", "select count(*) from Blog", page, 2, null);
	}

	@Override
	@Transactional
	public void delete(long id) {
		// TODO Auto-generated method stub
		blogDAO.deleteById(id);
	}

	@Override
	@Transactional
	public int change(Blog blog) {
		// TODO Auto-generated method stub
		blogDAO.update(blog);
		return 0;
	}

	@Override
	@Transactional
	public PageResults<Blog> show_blog(int page) {
		// TODO Auto-generated method stub
		return blogDAO.findPageByFetchedHql("from Blog blog where blog.status=0 order by blog.date desc ", "select count(*) from Blog blog where blog.status=0", page, 2, null);
	}

}
