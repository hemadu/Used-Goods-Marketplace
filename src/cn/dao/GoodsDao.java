package cn.dao;

import java.util.List;

import cn.entity.Goods;
import cn.util.PageBean;

public interface GoodsDao {
public int save(Goods goods);
public int update(Goods goods);
public int delete(Integer id);
public Goods getGoods(Integer id);
public List<Goods> getAll(String sql,Object[] params);
public int getMaxId();

public int getCount();
public List<Goods>getGoodsByPage(int start,int pageSize);
public int getCount(String sql,List<Object>params);
public List<Goods>getGoodsByPage(String sql,List<Object>params);
public int check(Integer id);
}
