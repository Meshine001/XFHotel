package com.xfhotel.hotel.service;

import java.util.List;
import com.xfhotel.hotel.entity.Fault;
public interface FaultService extends BaseService<Fault, Long> {
	public Fault get(Long id);
	public List<Fault> getFault(Long oederId);
	public List<Fault> getFault1(Long roomId);
	public List<Fault> list();
}
