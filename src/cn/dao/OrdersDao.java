package cn.dao;

import java.util.List;

import cn.entity.Orders;


public interface OrdersDao {
	public int save(Orders orders);
	public int update(Orders orders);
	public int delete(Integer id);
	public Orders getOrders(Integer id);
	public List<Orders> getAll(String sql,Object[] parmas);
	public int getCount();
	public int updatePriceById(Integer oid,Double price);
	public int getMaxId();
	public List<Orders>getOrdersByPage(int start,int pageSize);
	public int getCount(String sql,List<Object>params);
	public List<Orders>getOrdersByPage(String sql,List<Object>parmas);
}
