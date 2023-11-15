package cn.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.StringUtils;

import cn.dao.GoodsDao;
import cn.dao.GoodstypeDao;
import cn.dao.UsersDao;
import cn.dao.impl.GoodsDaoImpl;
import cn.dao.impl.GoodstypeDaoImpl;
import cn.dao.impl.UsersDaoImpl;
import cn.entity.Goods;
import cn.service.GoodsService;
import cn.util.PageBean;

public class GoodsServiceImpl implements GoodsService{
	GoodsDao goodsDao=new GoodsDaoImpl();
	UsersDao usersDao=new UsersDaoImpl();
	GoodstypeDao goodstypeDao=new GoodstypeDaoImpl();
	
	public int save(Goods goods){
		return goodsDao.save(goods);
	}
	public int update(Goods goods){
		return goodsDao.update(goods);
	}
	public int delete(Integer id){
		return goodsDao.delete(id);
	}
	public Goods getGoods(Integer id){
		Goods goods = goodsDao.getGoods(id);
		if(goods!=null){
			goods.setUsers(usersDao.getUsers(goods.getSid()));
			goods.setGoodstype(goodstypeDao.getGoodstype(goods.getCid()));
		}
		return goods;
	}
	public List<Goods> getAllGoods(String type,String name,String need){
		String sql="select * from goods where isdel=0 ";
		if(!StringUtils.isNullOrEmpty(name)){
			sql+=" and name like "+name;
		}
		if(!StringUtils.isNullOrEmpty(type)){
			sql+=" and cid="+type;
		}
		if(!StringUtils.isNullOrEmpty(need)){
			sql+=" and type="+need;
		}
		List<Goods> goodsList = goodsDao.getAll(sql, new Object[]{});
		for(Goods g : goodsList){
			g.setUsers(usersDao.getUsers(g.getSid()));
			g.setGoodstype(goodstypeDao.getGoodstype(g.getCid()));
		}
		return goodsList;
	}
	public List<Goods> getAllCheckGoods(String type,String name){
		String sql="select * from goods where isdel=2 ";
		if(type!=null&&!"".equals(type)){
			sql+=" and cid="+type;
		}
		if(name!=null&&!"".equals(name)){
			sql+=" and name like '%"+name+"%'";
		}
		List<Goods> goodsList = goodsDao.getAll(sql, new Object[]{});
		for(Goods g : goodsList){
			g.setUsers(usersDao.getUsers(g.getSid()));
			g.setGoodstype(goodstypeDao.getGoodstype(g.getCid()));
		}
		return goodsList;
	}
	@Override
	public int getCount() {
		return goodsDao.getCount();
	}
	@Override
	public List<Goods> getGoodsByPage(PageBean pagebean) {
		int start=(pagebean.getPageNo()-1)*pagebean.getPageSize();
		return  goodsDao.getGoodsByPage(start,pagebean.getPageSize());
	}
	@Override
	public int getCount(Map<String, Object> map) {
		String sql="select count(1) from goods where isdel=0  ";
		List<Object> params=new ArrayList<Object>();
		String name=(String)map.get("name");
		if(!StringUtils.isNullOrEmpty(name)){
			sql+=" and name like ?";
			params.add("%"+name+"%");
		}
		Integer type=(Integer)map.get("type");
		if(type!=null&&type!=0){
			sql+=" and cid=?";
			params.add(type);
		}
		Integer sid=(Integer)map.get("sid");
		if(sid!=null&&sid!=0){
			sql+=" and sid=?";
			params.add(sid);
		}
		String need=(String)map.get("need");
		if(!StringUtils.isNullOrEmpty(need)){
			sql+=" and type=?";
			params.add(need);
		}
		return goodsDao.getCount(sql,params);
	}

	@Override
	public List<Goods> getGoodsByPage(PageBean pageBean, Map<String, Object> map) {
		String sql="select * from goods where isdel=0 ";
		List<Object>params=new ArrayList<Object>();
		String name=(String)map.get("name");
		if(!StringUtils.isNullOrEmpty(name)){
			sql+=" and name like ?";
			params.add("%"+name+"%");
		}
		Integer type=(Integer)map.get("type");
		if(type!=null&&type!=0){
			sql+=" and cid=?";
			params.add(type);
		}
		Integer sid=(Integer)map.get("sid");
		if(sid!=null&&sid!=0){
			sql+=" and sid=?";
			params.add(sid);
		}
		String need=(String)map.get("need");
		if(!StringUtils.isNullOrEmpty(need)){
			sql+=" and type=?";
			params.add(need);
		}
		sql+=" limit ?,?";
		int start=(pageBean.getPageNo()-1)*pageBean.getPageSize();
		params.add(start);
		params.add(pageBean.getPageSize());
		
		List<Goods> goodsList = goodsDao.getGoodsByPage(sql,params);
		for(Goods g : goodsList){
			g.setUsers(usersDao.getUsers(g.getSid()));
			g.setGoodstype(goodstypeDao.getGoodstype(g.getCid()));
		}
		return goodsList;
	}
	public List<Goods> getGoodsByPage1(PageBean pageBean, Map<String, Object> map) {
		String sql="select * from goods where isdel=0 ";
		List<Object>params=new ArrayList<Object>();
		String name=(String)map.get("name");
		if(!StringUtils.isNullOrEmpty(name)){
			sql+=" and name like ?";
			params.add("%"+name+"%");
		}
		Integer type=(Integer)map.get("type");
		if(type!=null&&type!=0){
			sql+=" and cid=?";
			params.add(type);
		}
		String need=(String)map.get("need");
		if(!StringUtils.isNullOrEmpty(need)){
			sql+=" and type=?";
			params.add(need);
		}
		sql+=" limit ?,?";
		int start=(pageBean.getPageNo()-1)*pageBean.getBpageSize();
		params.add(start);
		params.add(pageBean.getBpageSize());
		
		List<Goods> goodsList = goodsDao.getGoodsByPage(sql,params);
		for(Goods g : goodsList){
			g.setUsers(usersDao.getUsers(g.getSid()));
			g.setGoodstype(goodstypeDao.getGoodstype(g.getCid()));
		}
		
		return goodsList;
	}
	
	public int getCheckCount(Map<String, Object> map) {
		String sql="select count(1) from goods where isdel=2  ";
		List<Object> params=new ArrayList<Object>();
		String name=(String)map.get("name");
		if(!StringUtils.isNullOrEmpty(name)){
			sql+=" and name like ?";
			params.add("%"+name+"%");
		}
		Integer type=(Integer)map.get("type");
		if(type!=null&&type!=0){
			sql+=" and cid=?";
			params.add(type);
		}
		return goodsDao.getCount(sql,params);
	}
	@Override
	public List<Goods> getCheckGoodsByPage(PageBean pageBean, Map<String, Object> map) {
		String sql="select * from goods where isdel=2 ";
		List<Object>params=new ArrayList<Object>();
		String name=(String)map.get("name");
		if(!StringUtils.isNullOrEmpty(name)){
			sql+=" and name like ?";
			params.add("%"+name+"%");
		}
		Integer type=(Integer)map.get("type");
		if(type!=null&&type!=0){
			sql+=" and cid=?";
			params.add(type);
		}
		sql+=" limit ?,?";
		int start=(pageBean.getPageNo()-1)*pageBean.getPageSize();
		params.add(start);
		params.add(pageBean.getPageSize());
		
		List<Goods> goodsList = goodsDao.getGoodsByPage(sql,params);
		for(Goods g : goodsList){
			g.setUsers(usersDao.getUsers(g.getSid()));
			g.setGoodstype(goodstypeDao.getGoodstype(g.getCid()));
		}
		return goodsList;
	}
	@Override
	public int check(Integer id) {
		return goodsDao.check(id);
	}
	@Override
	public int getMaxId() {
		// TODO Auto-generated method stub
		return goodsDao.getMaxId();
	}
	
}
