package cn.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.StringUtils;

import cn.dao.GoodsDao;
import cn.dao.OrdersDao;
import cn.dao.OrdertailDao;
import cn.dao.impl.GoodsDaoImpl;
import cn.dao.impl.OrdersDaoImpl;
import cn.dao.impl.OrdertailDaoImpl;
import cn.entity.Orders;
import cn.entity.Ordertail;
import cn.service.OrdertailService;
import cn.util.PageBean;

public class OrdertailServiceImpl implements OrdertailService{
	OrdertailDao ordertailDao=new OrdertailDaoImpl();
	OrdersDao ordersDao=new OrdersDaoImpl();
	GoodsDao goodsDao=new GoodsDaoImpl();
	public int save(Ordertail ordertail){
		return ordertailDao.save(ordertail);
	}
	public int update(Ordertail ordertail){
		return ordertailDao.update(ordertail);
	}
	public int delete(Integer id){
		return ordertailDao.delete(id);
	}
	public Ordertail getOrdertail(Integer id){
		return ordertailDao.getOrdertail(id);
	}
	public List<Ordertail> getAllOrdertail(){
		String sql="select * from ordertail ";
		return ordertailDao.getAll(sql, new Object[] {});
	}
	@Override
	public int getCount() {
		
		return ordertailDao.getCount();
	}
	@Override
	public List<Ordertail> getOrdertailByPage(PageBean pageBean) {
		int start=(pageBean.getPageNo()-1)*pageBean.getPageSize();
		return ordertailDao.getOrdertailByPage(start,pageBean.getPageSize());
	}
	public int getCount(Map<String, Object> map) {
		String sql="select count(1) from ordertail where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		String oid=(String)map.get("oid");
		if(!StringUtils.isNullOrEmpty(oid)){
			sql+=" and oid=?";
			params.add(oid);
		}
		return ordertailDao.getCount(sql,params);
	}
	@Override
	public List<Ordertail> getOrdertailByPage(PageBean pageBean, Map<String, Object> map) {
		String sql="select * from ordertail where 1=1 ";
		List<Object>params=new ArrayList<Object>();
		String oid=(String)map.get("oid");
		if(!StringUtils.isNullOrEmpty(oid)){
			sql+=" and oid=?";
			params.add(oid);
		}
		sql+=" limit ?,?";
		int start=(pageBean.getPageNo()-1)*pageBean.getPageSize();
		params.add(start);
		params.add(pageBean.getPageSize());
		List<Ordertail>ordertailList=ordertailDao.getOrdertailByPage(sql,params);
		for(Ordertail o:ordertailList){
			o.setGoods(goodsDao.getGoods(o.getPid()));
			o.setOrders(ordersDao.getOrders(o.getOid()));
		}
		return ordertailList;
	}
}
