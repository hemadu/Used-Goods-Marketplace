package cn.service;

import java.util.List;
import java.util.Map;

import cn.entity.Users;
import cn.util.PageBean;

public interface UsersService {
	public int save(Users users);
	public int update(Users users);
	public int delete(Integer id);
	public Users getUsers(Integer id);
	public Users login(String phone,String password);
	public List<Users> getAllUsers(Integer type,String name);
	public List<Users> getAllUsers();	
	public List<Users> getAllCheckUsers(Integer type,String name);
	
	public int getCount();
	
	public List<Users>getUsersByPage(PageBean pagebean);
	
	public int getCount(Map<String,Object>map);
	public List<Users> getUsersByPage(PageBean pageBean,Map<String,Object>map);
	
	public int getCheckCount(Map<String,Object>map);
	public List<Users> getCheckUsersByPage(PageBean pageBean,Map<String,Object>map);
	public int check(Integer id);
}
