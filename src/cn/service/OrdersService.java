package cn.service;

import java.util.List;
import java.util.Map;

import cn.entity.Orders;
import cn.util.PageBean;

public interface OrdersService {
	public int save(Orders orders);
	public int update(Orders orders);
	public int delete(Integer id);
	public Orders getOrders(Integer id);
	public List<Orders> getAllOrders(Integer sid);
	
	public int getCount();
	public int updatePriceById(Integer oid, Double price) ;
	public List<Orders>getOrdersByPage(PageBean pagebean);
	
	public int getCount(Map<String,Object>map);
	public List<Orders> getOrdersByPage(PageBean pageBean,Map<String,Object>map);
}
