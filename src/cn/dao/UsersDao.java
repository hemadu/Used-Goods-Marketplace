package cn.dao;

import java.util.List;

import cn.entity.Users;

public interface UsersDao {
public int save(Users users);
public int update(Users users);
public int delete(Integer id);
public Users getUsers(Integer id);
public List<Users> getAll(String sql,Object[] params);

public int getCount();
public List<Users> getUsersByPage(int start,int pageSize);
public int getCount(String sql,List<Object>params);
public List<Users>getUsersByPage(String sql,List<Object>params);
public int check(Integer id);
}
