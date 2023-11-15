package cn.service;

import java.util.List;
import java.util.Map;

import cn.entity.Message;
import cn.util.PageBean;

public interface MessageService {
	public int save(Message message);
	public int update(Message message);
	public int delete(Integer id);
	public Message getMessage(Integer id);
	public List<Message> getAllMessage(Integer pid);
	
	public int getCount();
	
	public List<Message>getMessageByPage(PageBean pagebean);
	
	public int getCount(Map<String,Object>map);
	public List<Message> getMessageByPage(PageBean pageBean,Map<String,Object>map);
}
