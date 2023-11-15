package cn.service;

import java.util.List;
import java.util.Map;

import cn.entity.Goods;
import cn.util.PageBean;

public interface GoodsService {

	public int save(Goods goods);
	public int update(Goods goods);
	public int delete(Integer id);
	public Goods getGoods(Integer id);
	public List<Goods> getAllGoods(String type,String name,String need);
	public List<Goods> getAllCheckGoods(String type,String name);
	public int getMaxId();
	public int getCount();
	
	public List<Goods>getGoodsByPage(PageBean pagebean);
	
	public int getCount(Map<String,Object>map);
	public List<Goods> getGoodsByPage(PageBean pageBean,Map<String,Object>map);
	
	public int getCheckCount(Map<String,Object>map);
	public List<Goods> getCheckGoodsByPage(PageBean pageBean,Map<String,Object>map);
	public int check(Integer id);
	public List<Goods> getGoodsByPage1(PageBean pageBean, Map<String, Object> map);
}
