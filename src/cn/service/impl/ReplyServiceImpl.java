package cn.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.StringUtils;

import cn.dao.MessageDao;
import cn.dao.ReplyDao;
import cn.dao.UsersDao;
import cn.dao.impl.MessageDaoImpl;
import cn.dao.impl.ReplyDaoImpl;
import cn.dao.impl.UsersDaoImpl;
import cn.entity.Reply;
import cn.service.ReplyService;
import cn.util.PageBean;

public class ReplyServiceImpl implements ReplyService{
	ReplyDao replyDao=new ReplyDaoImpl();
	MessageDao messageDao=new MessageDaoImpl();
	UsersDao usersDao=new UsersDaoImpl();
	public int save(Reply reply){
		return replyDao.save(reply);
	}
	public int update(Reply reply){
		return replyDao.update(reply);
	}
	public int delete(Integer id){
		return replyDao.delete(id);
	}
	public Reply getReply(Integer id){
		return replyDao.getReply(id);
	}
	public List<Reply> getAllReply(Integer mid){
		String sql="select * from reply where mid=? ORDER BY optime ";
		List<Reply> replyList=replyDao.getAll(sql, new Object[] {mid});
		for(Reply m:replyList){
			m.setUsers(usersDao.getUsers(m.getUid()));
		}
		return replyList;
	}
	public List<Reply> getAllChat(Integer mid){
		String sql="select * from reply where mid=?  ORDER BY optime ";
		List<Reply> replyList=replyDao.getAll(sql, new Object[] {mid});
		for(Reply m:replyList){
			m.setUsers(usersDao.getUsers(m.getUid()));
			m.setMessage(messageDao.getMessage(m.getMid()));
		}
		return replyList;
	}
	public List<Reply> getAllUsersReply(Integer uid){
		String sql="SELECT * FROM(SELECT * FROM reply where uid=? ORDER BY optime DESC)as A GROUP BY mid ORDER BY optime  ";
		return replyDao.getAll(sql, new Object[] {uid});
	}
	@Override
	public int getCount() {
	return replyDao.getCount();
	}
	@Override
	public List<Reply> getReplyByPage(PageBean pageBean) {
		int start=(pageBean.getPageNo()-1)*pageBean.getPageSize();
		return replyDao.getReplyByPage(start,pageBean.getPageSize());
	}
	@Override
	public int getCount(Map<String, Object> map) {
		String sql="SELECT COUNT(1) FROM (SELECT * FROM(SELECT * FROM reply WHERE 1=1  ";
		List<Object>params=new ArrayList<Object>();
		Integer uid=(Integer)map.get("uid");
		if(uid!=0){
			sql+=" and uid=?";
			params.add(uid);
			
		}
//		String mid=(String)map.get("mid");
//		if(!StringUtils.isNullOrEmpty(mid)){
//			sql+=" and mid=?";
//			params.add(mid);
//			
//		}
		sql+="  ORDER BY optime DESC)as A GROUP BY mid ORDER BY optime DESC)AS B";
		return replyDao.getCount(sql,params);
	}
	@Override
	public List<Reply> getReplyByPage(PageBean pageBean,Map<String, Object> map) {
		String sql="SELECT * FROM(SELECT * FROM reply WHERE 1=1 ";
		List<Object>params=new ArrayList<Object>();
		Integer uid=(Integer)map.get("uid");
		if(uid!=0){
			sql+=" and uid=?";
			params.add(uid);
			
		}
//		String mid=(String)map.get("mid");
//		if(!StringUtils.isNullOrEmpty(mid)){
//			sql+=" and mid=?";
//			params.add(mid);
//			
//		}
		sql+="  ORDER BY optime DESC)as A GROUP BY mid ORDER BY optime DESC limit ?,?";
		int start=(pageBean.getPageNo()-1)*pageBean.getPageSize();
		params.add(start);
		params.add(pageBean.getPageSize());
		List<Reply> replyList=replyDao.getReplyByPage(sql,params);
		for(Reply m:replyList){
			m.setUsers(usersDao.getUsers(m.getUid()));
			m.setMessage(messageDao.getMessage(m.getMid()));
		}
		return replyList;
	}
	@Override
	public int deletemg(Integer mid,Integer uid){
		return replyDao.deletemg(mid,uid);
	}
}
