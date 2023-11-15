package cn.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.StringUtils;

import cn.dao.UsersDao;
import cn.dao.impl.UsersDaoImpl;
import cn.entity.Users;
import cn.service.UsersService;
import cn.util.PageBean;

public class UsersServiceImpl implements UsersService{
	UsersDao usersDao =new UsersDaoImpl();
	@Override
	public int save(Users users) {
		// TODO Auto-generated method stub
		return usersDao.save(users);
	}

	@Override
	public int update(Users users) {
		// TODO Auto-generated method stub
		return usersDao.update(users);
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return usersDao.delete(id);
	}

	@Override
	public Users login(String phone, String password) {
		Users users=null;
		String sql="select * from users where phone=? and password=? and isdel=0 ";
		List<Users>usersList=usersDao.getAll(sql, new Object[] {phone,password});
		if(usersList!=null&&usersList.size()>0){
			users=usersList.get(0);
		}
		return users;
	}

	@Override
	public List<Users> getAllUsers(Integer type,String name) {
		String sql="select * from users where isdel=0 ";
		if(name!=null&&!"".equals(name)){
			sql+=" and name like '%"+name+"%'";
		}
		if(type!=3&&type!=null){
			sql+=" and role="+type;
		}
		return usersDao.getAll(sql, new Object[] {});
	}
	
	public List<Users> getAllCheckUsers(Integer type,String name) {
		String sql="select * from users where isdel=2 ";
		if(name!=null&&!"".equals(name)){
			sql+=" and name like '%"+name+"%'";
		}
		if(type!=3&&type!=null){
			sql+=" and role="+type;
		}
		return usersDao.getAll(sql, new Object[] {});
	}
	
	public List<Users> getAllUsers() {
		String sql="select * from users where isdel=0 ";
		return usersDao.getAll(sql, new Object[] {});
	}

	@Override
	public Users getUsers(Integer id) {
		
		return usersDao.getUsers(id);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return usersDao.getCount();
	}

	@Override
	public List<Users> getUsersByPage(PageBean pageBean) {
		int start =(pageBean.getPageNo()-1)*pageBean.getPageSize();
		
		return usersDao.getUsersByPage(start,pageBean.getPageSize());
	}

	@Override
	public int getCount(Map<String, Object> map) {
		String sql="select count(1) from users where isdel=0  ";
		List<Object>params=new ArrayList<Object>();
		String name=(String)map.get("name");
		if(!StringUtils.isNullOrEmpty(name)){
			sql+=" and name like ?";
			params.add("%"+name+"%");
		}
		Integer type=(Integer)map.get("type");
		if(type!=null&&type!=3){
			sql+=" and role=?";
			params.add(type);
		}
		return usersDao.getCount(sql,params);
	}

	@Override
	public List<Users> getUsersByPage(PageBean pageBean, Map<String, Object> map) {
		String sql="select * from users where  isdel=0 ";
		List<Object>params=new ArrayList<Object>();
		String name=(String)map.get("name");
		if(!StringUtils.isNullOrEmpty(name)){
			sql+=" and name like ?";
			params.add("%"+name+"%");
		}
		Integer type=(Integer)map.get("type");
		if(type!=null&&type!=3){
			sql+=" and role=?";
			params.add(type);
		}
		sql+=" limit ?,?";
		int start=(pageBean.getPageNo()-1)*pageBean.getPageSize();
		params.add(start);
		params.add(pageBean.getPageSize());
		return usersDao.getUsersByPage(sql,params);
	}

	public int getCheckCount(Map<String, Object> map) {
		String sql="select count(1) from users where isdel=2  ";
		List<Object>params=new ArrayList<Object>();
		String name=(String)map.get("name");
		if(!StringUtils.isNullOrEmpty(name)){
			sql+=" and name like ?";
			params.add("%"+name+"%");
		}
		Integer type=(Integer)map.get("type");
		if(type!=null&&type!=3){
			sql+=" and role=?";
			params.add(type);
		}
		return usersDao.getCount(sql,params);
	}

	@Override
	public List<Users> getCheckUsersByPage(PageBean pageBean, Map<String, Object> map) {
		String sql="select * from users where  isdel=2 ";
		List<Object>params=new ArrayList<Object>();
		String name=(String)map.get("name");
		if(!StringUtils.isNullOrEmpty(name)){
			sql+=" and name like ?";
			params.add("%"+name+"%");
		}
		Integer type=(Integer)map.get("type");
		if(type!=null&&type!=3){
			sql+=" and role=?";
			params.add(type);
		}
		sql+=" limit ?,?";
		int start=(pageBean.getPageNo()-1)*pageBean.getPageSize();
		params.add(start);
		params.add(pageBean.getPageSize());
		return usersDao.getUsersByPage(sql,params);
	}

	@Override
	public int check(Integer id) {
		return usersDao.check(id);
	}
	


}
