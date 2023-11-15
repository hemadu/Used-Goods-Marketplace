package cn.service;

import java.util.List;
import java.util.Map;

import cn.entity.Goodstype;
import cn.entity.Users;
import cn.util.PageBean;


public interface GoodstypeService {
	public int save(Goodstype goodstype);
	public int update(Goodstype goodstype);
	public int delete(Integer id);
	public Goodstype getGoodstype(Integer id);
	public List<Goodstype> getAllGoodstype();
	
	public int getCount();
	
	public List<Goodstype>getGoodstypeByPage(PageBean pagebean);
	
}
