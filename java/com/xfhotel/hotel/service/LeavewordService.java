package com.xfhotel.hotel.service;

import com.xfhotel.hotel.entity.Leaveword;
import com.xfhotel.hotel.entity.Page;

public interface LeavewordService {
	
	 public Page queryForPage(int currentPage,int pageSize);
	 
	 public void Servicesave(Leaveword l);
     
    public void Serviceupdate(Leaveword l);
      
    public void Servicedelete(Leaveword l);
    
    public int ServicegetCount();
   
    public  Leaveword QueryById(int id);


	
}
