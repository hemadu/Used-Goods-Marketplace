package cn.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.StringUtils;

import cn.dao.CartsDao;
import cn.dao.GoodsDao;
import cn.dao.UsersDao;
import cn.dao.impl.CartsDaoImpl;
import cn.dao.impl.GoodsDaoImpl;
import cn.dao.impl.UsersDaoImpl;
import cn.entity.Carts;
import cn.entity.Goods;
import cn.entity.Users;
import cn.service.CartsService;
import cn.util.PageBean;

public class CartsServiceImpl implements CartsService{
	CartsDao cartsDao=new CartsDaoImpl();
	UsersDao usersDao=new UsersDaoImpl();
	GoodsDao goodsDao=new GoodsDaoImpl();
	public int save(Carts carts){
		return cartsDao.save(carts);
	}
	public int update(Carts carts){
		return cartsDao.update(carts);
	}
	public int delete(Integer id){
		return cartsDao.delete(id);
	}
	public Carts getCarts(Integer id){
		Carts carts=cartsDao.getCarts(id);
		carts.setGoods(goodsDao.getGoods(carts.getPid()));
		return carts;
	}
	
	@Override
	public int getCount() {
		
		return cartsDao.getCount();
	}
	@Override
	public List<Carts> getCartsByPage(PageBean pagebean) {
		int start=(pagebean.getPageNo()-1)*pagebean.getPageSize();
		return  cartsDao.getCartsByPage(start,pagebean.getPageSize());
	}
	@Override
	public List<Carts> getAllCarts(Integer uid,String type) {
		String sql="select * from carts where uid="+uid;
		if(!StringUtils.isNullOrEmpty(type)){
			sql+="  and type="+type;
		}
		sql+="  order by sid";
		List<Carts> cartsList = cartsDao.getAll(sql, new Object[]{});
		for(Carts c : cartsList){
			c.setUsers(usersDao.getUsers(c.getUid()));
			c.setGoods(goodsDao.getGoods(c.getPid()));
		}
		return cartsList;
	}
	@Override
	public int getCount(Map<String, Object> map) {
		String sql="select count(1) from carts where  1=1 ";
		List<Object>params=new ArrayList<Object>();
		Integer uid=(Integer)map.get("uid");
		if(uid!=0){
			sql+=" and uid=?";
			params.add(uid);
		}
		return usersDao.getCount(sql,params);
	}

	@Override
	public List<Carts> getCartsByPage(PageBean pageBean, Map<String, Object> map) {
		String sql="select * from carts where 1=1 ";
		List<Object>params=new ArrayList<Object>();
		Integer uid=(Integer)map.get("uid");
		if(uid!=0){
			sql+=" and uid=?";
			params.add(uid);
		}
		
		sql+=" limit ?,?";
		int start=(pageBean.getPageNo()-1)*pageBean.getPageSize();
		params.add(start);
		params.add(pageBean.getPageSize());
		List<Carts>cartsList=cartsDao.getCartsByPage(sql,params);
		for(Carts c : cartsList){
			c.setUsers(usersDao.getUsers(c.getUid()));
			c.setGoods(goodsDao.getGoods(c.getPid()));
		}
		return cartsList;
	}
	@Override
	public int deleteByUid(Integer uid) {
		// TODO Auto-generated method stub
		return cartsDao.deleteByUid(uid);
	}
	
}
