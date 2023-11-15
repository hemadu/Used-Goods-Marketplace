package cn.dao;

import java.util.List;

import cn.entity.Reply;

public interface ReplyDao {
public int save(Reply reply);
public int update(Reply reply);
public int delete(Integer id);
public Reply getReply(Integer id);
public List<Reply> getAll(String sql,Object[] params);
public int getCount();
public List<Reply> getReplyByPage(int start,int pageSize);
public int getCount(String sql,List<Object>params);
public List<Reply>getReplyByPage(String sql,List<Object> params);
public int deletemg(Integer mid,Integer uid);
}
