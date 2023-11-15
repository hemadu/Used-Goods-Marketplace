package cn.dao;

import java.util.List;

import cn.entity.Orders;
import cn.entity.Ordertail;

public interface OrdertailDao {
	public int save(Ordertail ordertail);
	public int update(Ordertail ordertail);
	public int delete(Integer id);
	public Ordertail getOrdertail(Integer id);
	public List<Ordertail> getAll(String sql,Object[] parmas);
	public int getCount();
	public List<Ordertail>getOrdertailByPage(int start,int pageSize);
	public int getCount(String sql,List<Object>params);
	public List<Ordertail>getOrdertailByPage(String sql,List<Object>parmas);
}
