package cn.dao;

import java.util.List;

import cn.entity.Goodstype;
import cn.entity.Message;

public interface MessageDao {
public int save(Message message);
public int update(Message message);
public int delete(Integer id);
public Message getMessage(Integer id);
public List<Message> getAll(String sql,Object[] params);

public int getCount();
public List<Message> getMessageByPage(int start,int pageSize);
public int getCount(String sql,List<Object>params);
public List<Message>getMessageByPage(String sql,List<Object> params);
}
