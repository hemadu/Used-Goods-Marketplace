package cn.service;

import java.util.List;
import java.util.Map;

import cn.entity.Orders;
import cn.entity.Ordertail;
import cn.util.PageBean;

public interface OrdertailService {
	public int save(Ordertail ordertail);
	public int update(Ordertail ordertail);
	public int delete(Integer id);
	public Ordertail getOrdertail(Integer oid);
	
	public int getCount();
	
	public List<Ordertail>getOrdertailByPage(PageBean pagebean);
	public int getCount(Map<String,Object>map);
	public List<Ordertail> getOrdertailByPage(PageBean pageBean,Map<String,Object>map);
}
