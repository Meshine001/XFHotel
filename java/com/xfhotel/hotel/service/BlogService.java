package com.xfhotel.hotel.service;

import com.xfhotel.hotel.entity.Blog;
import com.xfhotel.hotel.entity.Customer;
import com.xfhotel.hotel.support.PageResults;

public interface BlogService extends BaseService<Blog>{
	public int publish(Blog blog, String content, String basepath);
	public Blog find(Long id);
	public int update(Blog blog, String content, String basepath);
	public int change(Blog blog);
	public PageResults<Blog> list(int page);
	public PageResults<Blog> list(int page,int pageSize);
	public void delete(long id);
	public PageResults<Blog> show_blog(int page);
}
