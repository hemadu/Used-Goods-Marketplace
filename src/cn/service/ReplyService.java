package cn.service;

import java.util.List;
import java.util.Map;

import cn.entity.Message;
import cn.entity.Reply;
import cn.util.PageBean;

public interface ReplyService {
	public int save(Reply reply);
	public int update(Reply reply);
	public int delete(Integer id);
	public Reply getReply(Integer id);
	public List<Reply> getAllReply(Integer mid);
	public List<Reply> getAllUsersReply(Integer uid);
	
	public int getCount();
	
	public List<Reply>getReplyByPage(PageBean pageBean);
	
	public int getCount(Map<String,Object>map);
	public List<Reply> getReplyByPage(PageBean pageBean,Map<String,Object>map);
	public int deletemg(Integer mid,Integer uid);
	public List<Reply> getAllChat(Integer mid);
}
