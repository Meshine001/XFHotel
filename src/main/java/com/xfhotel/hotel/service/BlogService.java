package com.xfhotel.hotel.service;

import com.xfhotel.hotel.entity.Blog;
import com.xfhotel.hotel.entity.Customer;
import com.xfhotel.hotel.support.PageResults;

public interface BlogService extends BaseService<Blog>{
	public int publish(Blog blog, String content, String basepath);
	public Blog find(Long id);
	public int update(Blog blog, String content, String basepath);
	public PageResults<Blog> list(int page);
	public void delete(long id);
}
