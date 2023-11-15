package cn.dao;

import java.util.List;

import cn.entity.Goodstype;

public interface GoodstypeDao {
public int save(Goodstype goodstype);
public int update(Goodstype goodstype);
public int delete(Integer id);
public Goodstype getGoodstype(Integer id);
public List<Goodstype> getAll(String sql,Object[] params);

public int getCount();
public List<Goodstype> getGoodstypeByPage(int start,int pageSize);
public int getCount(String sql,List<Object>params);
public List<Goodstype>getGoodstypeByPage(String sql,List<Object> params);
}
