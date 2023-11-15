package cn.service;

import java.util.List;
import java.util.Map;

import cn.entity.Carts;
import cn.entity.Users;
import cn.util.PageBean;

public interface CartsService {
	public int save(Carts carts);
	public int update(Carts carts);
	public int delete(Integer id);
	public Carts getCarts(Integer id);
	public int deleteByUid(Integer uid);
	public List<Carts> getAllCarts(Integer uid,String type);
	
	public int getCount();
	
	public List<Carts>getCartsByPage(PageBean pagebean);
	public int getCount(Map<String,Object>map);
	public List<Carts>getCartsByPage(PageBean pageBean,Map<String,Object>map);

}
