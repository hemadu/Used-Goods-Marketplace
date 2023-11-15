package cn.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.StringUtils;

import cn.dao.OrdersDao;
import cn.dao.impl.OrdersDaoImpl;
import cn.entity.Orders;
import cn.service.OrdersService;
import cn.util.PageBean;

public class OrdersServiceImpl implements OrdersService{
	OrdersDao ordersDao =new OrdersDaoImpl();
	public int save(Orders orders){
		ordersDao.save(orders);
		return ordersDao.getMaxId();
	}
	public int update(Orders orders){
		return ordersDao.update(orders);
	}
	public int delete(Integer id){
		return ordersDao.delete(id);
	}
	public Orders getOrders(Integer id){
		return ordersDao.getOrders(id);
	}
	public List<Orders>getAllOrders(Integer sid) {
		String sql="select * from orders where isdel<>1 and sid="+sid;
		return ordersDao.getAll(sql, new Object[] {});
	}
	@Override
	public int getCount() {
		return ordersDao.getCount();
	}
	@Override
	public List<Orders> getOrdersByPage(PageBean pageBean) {
		int start=(pageBean.getPageNo()-1)*pageBean.getPageSize();
		return ordersDao.getOrdersByPage(start,pageBean.getPageSize());
	}
	@Override
	public int getCount(Map<String, Object> map) {
		String sql="select count(1) from orders where isdel<>1 ";
		List<Object> params=new ArrayList<Object>();
		Integer uid=(Integer)map.get("uid");
		if(uid!=0){
			sql+=" and uid=?";
			params.add(uid);
		}
		Integer sid=(Integer)map.get("sid");
		if(sid!=0){
			sql+=" and sid=?";
			params.add(sid);
		}
		String no=(String)map.get("no");
		if(!StringUtils.isNullOrEmpty(no)){
			sql+=" and no like ?";
			params.add("%"+no+"%");
		}
		return ordersDao.getCount(sql,params);
	}
	@Override
	public List<Orders> getOrdersByPage(PageBean pageBean, Map<String, Object> map) {
		String sql="select * from orders where isdel<>1 ";
		List<Object>params=new ArrayList<Object>();
		Integer uid=(Integer)map.get("uid");
		if(uid!=0){
			sql+=" and uid=?";
			params.add(uid);
		}
		Integer sid=(Integer)map.get("sid");
		if(sid!=0){
			sql+=" and sid=?";
			params.add(sid);
		}
		String no=(String)map.get("no");
		if(!StringUtils.isNullOrEmpty(no)){
			sql+=" and no like ?";
			params.add("%"+no+"%");
		}
		sql+=" order by optime desc limit ?,? ";
		int start=(pageBean.getPageNo()-1)*pageBean.getPageSize();
		params.add(start);
		params.add(pageBean.getPageSize());
		return ordersDao.getOrdersByPage(sql,params);
	}
	@Override
	public int updatePriceById(Integer oid, Double price) {
		// TODO Auto-generated method stub
		return ordersDao.updatePriceById(oid, price);
	}


}
