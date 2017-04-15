package com.xfhotel.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.impl.LeavewordDaoImpl;
import com.xfhotel.hotel.entity.Leaveword;
import com.xfhotel.hotel.entity.Page;
import com.xfhotel.hotel.service.LeavewordService;
@Repository("LeavewordServiceImpl")
@Transactional
public class LeavewordServiceImpl implements LeavewordService {
        @Autowired
        public LeavewordDaoImpl LeaveworddaoImpl;
        /**
         * 分页查询 
         * @param currentPage 当前页号：现在显示的页数
         * @param pageSize 每页显示的记录条数
         * @return 封闭了分页信息(包括记录集list)的Bean
         * */
        public Page queryForPage(int currentPage,int pageSize) {
            Page page = new Page();       
            //总记录数
            int allRow = LeaveworddaoImpl.getAllRowCount();
            //当前页开始记录
            int offset = page.countOffset(currentPage,pageSize);  
            //分页查询结果集
            List<Leaveword> list = LeaveworddaoImpl.queryForPage(offset, pageSize); 
            page.setPageNo(currentPage);
            page.setPageSize(pageSize);
            page.setTotalRecords(allRow);
            page.setList(list);    
            return page;
        }
         public void Servicesave(Leaveword l){
        	 LeaveworddaoImpl.save(l);
         }
        public void Serviceupdate(Leaveword l){
        	LeaveworddaoImpl.update(l);
        }
        public void Servicedelete(Leaveword l){
        	LeaveworddaoImpl.delete(l);
        }
        public int ServicegetCount(){
            return LeaveworddaoImpl.getAllRowCount();
        }
        public  Leaveword QueryById(int id){
            return LeaveworddaoImpl.QueryById(id);
        }

}