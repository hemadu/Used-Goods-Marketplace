package cn.dao;

import java.util.List;

import cn.entity.Carts;

public interface CartsDao {
public int save(Carts carts);
public int update(Carts carts);
public int delete(Integer id);
public int deleteByUid(Integer uid);
public Carts getCarts(Integer id);
public List<Carts> getAll(String sql,Object[] parmas);
public int getCount();
public List<Carts>getCartsByPage(int start,int pageSize);
public int getCount(String sql,List<Object>params);
public List<Carts>getCartsByPage(String sql,List<Object>parmas);
}
