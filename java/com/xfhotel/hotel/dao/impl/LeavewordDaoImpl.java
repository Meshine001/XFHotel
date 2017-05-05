package com.xfhotel.hotel.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xfhotel.hotel.entity.Leaveword;
//采用注解写，在配置文件中不用再写Dao层的bean
@Repository("leavewordDaoImpl")

public class LeavewordDaoImpl{
 @Autowired
    private SessionFactory sessionFactory;
    /**
  * 分页查询
  * @param hql 查询的条件
  * @param offset 开始记录
  * @param length 一次查询几条记录
  * @return 返回查询记录集合
  */
@SuppressWarnings("unchecked")
     public List<Leaveword> queryForPage(int offset, int length) {
       //查询所有的记录数
       Query query= (Query) sessionFactory.getCurrentSession().createQuery("from t_leavewordl");    
        query.setFirstResult(offset);
        query.setMaxResults(length);            
        return query.list(); 
       }

     public void save(Leaveword l){
     sessionFactory.getCurrentSession().save(l);
       }
     public void update(Leaveword l) {   
            sessionFactory.getCurrentSession().update(l);
       }
     public void delete(Leaveword l) {      
            sessionFactory.getCurrentSession().delete(l);
       }
     //查询记录总数
     public int getAllRowCount(){
         int count=((Long) sessionFactory.getCurrentSession()
                .createQuery( "select count(*) from t_leaveword").iterate().next()).intValue();
         return count;  
     }
     //根据id查询记录
    public  Leaveword QueryById(int id) {
    	Leaveword l =(Leaveword) sessionFactory.getCurrentSession().get(Leaveword.class, id);
       return l;
     }
}


