package cn.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.StringUtils;

import cn.dao.GoodsDao;
import cn.dao.MessageDao;
import cn.dao.UsersDao;
import cn.dao.impl.GoodsDaoImpl;
import cn.dao.impl.MessageDaoImpl;
import cn.dao.impl.UsersDaoImpl;
import cn.entity.Message;
import cn.service.MessageService;
import cn.util.PageBean;

public class MessageServiceImpl implements MessageService{
	MessageDao messageDao=new MessageDaoImpl();
	UsersDao usersDao=new UsersDaoImpl();
	GoodsDao goodsDao=new GoodsDaoImpl();
	public int save(Message job){
		return messageDao.save(job);
	}
	public int update(Message job){
		return messageDao.update(job);
	}
	public int delete(Integer id){
		return messageDao.delete(id);
	}
	public Message getMessage(Integer id){
		Message message=messageDao.getMessage(id);
		if(message==null){
			return message;
		}else{
			message.setUsers(usersDao.getUsers(message.getUid()));
			return message;
		}
	}
	public List<Message> getAllMessage(Integer pid){
		String sql="select * from Message where isdel<>1 and pid=?";
		List<Message>messageList=messageDao.getAll(sql, new Object[] {pid});
		for(Message m:messageList){
			m.setUsers(usersDao.getUsers(m.getUid()));
		}
		return messageList;
	}
	@Override
	public int getCount() {
	return messageDao.getCount();
	}
	@Override
	public List<Message> getMessageByPage(PageBean pageBean) {
		int start=(pageBean.getPageNo()-1)*pageBean.getPageSize();
		return messageDao.getMessageByPage(start,pageBean.getPageSize());
	}
	@Override
	public int getCount(Map<String, Object> map) {
		String sql="select count(1) from message where isdel<>1 ";
		List<Object>params=new ArrayList<Object>();
		if(map.get("pid")!=null){
			Integer pid=(Integer)map.get("pid");
			sql+=" and pid=?";
			params.add(pid);
		}
		if(map.get("uid")!=null){
			Integer uid=(Integer)map.get("uid");
			sql+=" and uid=?";
			params.add(uid);
		}
		return messageDao.getCount(sql,params);
	}
	@Override
	public List<Message> getMessageByPage(PageBean pageBean,Map<String, Object> map) {
		String sql="select * from message where isdel<>1 ";
		List<Object>params=new ArrayList<Object>();
		if(map.get("pid")!=null){
			Integer pid=(Integer)map.get("pid");
			sql+=" and pid=?";
			params.add(pid);
		}
		if(map.get("uid")!=null){
			Integer uid=(Integer)map.get("uid");
			sql+=" and uid=?";
			params.add(uid);
		}
		sql+=" limit ?,?";
		int start=(pageBean.getPageNo()-1)*pageBean.getPageSize();
		params.add(start);
		params.add(pageBean.getPageSize());
		List<Message> messageList=messageDao.getMessageByPage(sql,params);
		for(Message m:messageList){
			m.setUsers(usersDao.getUsers(m.getUid()));
			m.setGoods(goodsDao.getGoods(m.getPid()));
		}
		return messageList;
	}
}
