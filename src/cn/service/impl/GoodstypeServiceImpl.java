package cn.service.impl;

import java.util.List;

import cn.dao.GoodstypeDao;
import cn.dao.impl.GoodstypeDaoImpl;
import cn.entity.Goodstype;
import cn.service.GoodstypeService;
import cn.util.PageBean;

public class GoodstypeServiceImpl implements GoodstypeService{
	GoodstypeDao goodstypeDao=new GoodstypeDaoImpl();
	
	public int save(Goodstype goodstype){
		return goodstypeDao.save(goodstype);
	}
	public int update(Goodstype goodstype){
		return goodstypeDao.update(goodstype);
	}
	public int delete(Integer id){
		return goodstypeDao.delete(id);
	}
	public Goodstype getGoodstype(Integer id){
		return goodstypeDao.getGoodstype(id);
	}
	public List<Goodstype> getAllGoodstype(){
		String sql="select * from goodstype where isdel<>1 ";
		return goodstypeDao.getAll(sql, new Object[] {});
	}
	@Override
	public int getCount() {
		return goodstypeDao.getCount();
	}
	@Override
	public List<Goodstype> getGoodstypeByPage(PageBean pageBean) {
		int start=(pageBean.getPageNo()-1)*pageBean.getPageSize();
		return goodstypeDao.getGoodstypeByPage(start,pageBean.getPageSize());
	}



}
