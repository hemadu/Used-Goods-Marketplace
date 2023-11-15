package cn.dao.impl;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.dao.BaseDao;
import cn.dao.MessageDao;
import cn.entity.Message;
import cn.entity.Message;

public class MessageDaoImpl extends BaseDao implements MessageDao{
	Connection conn=null;
	PreparedStatement pstm=null;
	ResultSet rs=null;
	public int save(Message message){
		String sql="insert into message values(null,?,?,?,?,0)";
		Object[] params={message.getUid(),message.getPid(),message.getContent(),message.getOptime()};
		return this.executeUpdate(sql, params);
	}
	public int update(Message message){
		String sql="update message set uid=?,pid=?,content=?,optime=? where id=?";
		Object[] params={message.getUid(),message.getPid(),message.getContent(),message.getOptime(),message.getId()};
		return this.executeUpdate(sql, params);
	}
	public int delete(Integer id){
		String sql="update message set isdel=1 where id=?";
		Object[] params={id};
		return this.executeUpdate(sql, params);
	}
	public Message getMessage(Integer id){
		Message message=null;
		conn=this.getConnection();
		String sql="select * from message where id=? and isdel<>1 ";
		try {
			pstm=conn.prepareStatement(sql);
			pstm.setObject(1, id);
			rs=pstm.executeQuery();
			if(rs.next()){
				message=new Message();
				message.setId(rs.getInt("id"));
				message.setUid(rs.getInt("uid"));
				message.setPid(rs.getInt("pid"));
				message.setIsdel(rs.getInt("isdel"));
				message.setContent(rs.getString("content"));
				message.setOptime(rs.getString("optime"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstm, rs);
		}
		return message;
	}
	public List<Message>getAll(String sql,Object[] params){
		List<Message>messages=new ArrayList<Message>();
		conn=this.getConnection();
		try {
			pstm=conn.prepareStatement(sql);
			if(params!=null&&params.length>0){
			for(int i=0;i<params.length;i++){
				pstm.setObject(i+1, params[i]);
			}
			}
			rs=pstm.executeQuery();
			while(rs.next()){
				Message message=new Message();
				message=new Message();
				message.setId(rs.getInt("id"));
				message.setUid(rs.getInt("uid"));
				message.setPid(rs.getInt("pid"));
				message.setIsdel(rs.getInt("isdel"));
				message.setContent(rs.getString("content"));
				message.setOptime(rs.getString("optime"));
				messages.add(message);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstm, rs);
		}
		return messages;
	}
	public int getCount() {
		int count=0;
		conn=this.getConnection();
		String sql="select count(1) from message where isdel<>1 ";
		try {
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			if(rs.next()){
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn,pstm,rs);
		}
		return count;
	}
	@Override
	public List<Message> getMessageByPage(int start, int pageSize) {
		
		List<Message>messages=new ArrayList<Message>();
		conn=this.getConnection();
		String sql="select * from message where isdel<>1 limit ?,?";
		try {
			pstm=conn.prepareStatement(sql);
			pstm.setInt(1,start);
			pstm.setInt(2,pageSize);
			rs=pstm.executeQuery();
			while(rs.next()){
				Message message=new Message();
				message=new Message();
				message.setId(rs.getInt("id"));
				message.setUid(rs.getInt("uid"));
				message.setPid(rs.getInt("pid"));
				message.setIsdel(rs.getInt("isdel"));
				message.setContent(rs.getString("content"));
				message.setOptime(rs.getString("optime"));
				messages.add(message);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstm, rs);
		}
		return messages;
	}
	@Override
	public int getCount(String sql, List<Object> params) {
		int count=0;
		conn=this.getConnection();
		try {
			pstm=conn.prepareStatement(sql);
			for(int i=0;i<params.size();i++){
				pstm.setObject(i+1,params.get(i));
			}
			rs=pstm.executeQuery();
			if(rs.next()){
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstm, rs);
		}
		return count;
	}
	@Override
	public List<Message> getMessageByPage(String sql, List<Object> params) {
		List<Message>messages=new ArrayList<Message>();
		conn=this.getConnection();
		try {
			pstm=conn.prepareStatement(sql);
			for(int i=0;i<params.size();i++){
				pstm.setObject(i+1,params.get(i));
			}
			rs=pstm.executeQuery();
			while(rs.next()){
				Message message=new Message();
				message=new Message();
				message.setId(rs.getInt("id"));
				message.setUid(rs.getInt("uid"));
				message.setPid(rs.getInt("pid"));
				message.setIsdel(rs.getInt("isdel"));
				message.setContent(rs.getString("content"));
				message.setOptime(rs.getString("optime"));
				messages.add(message);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstm, rs);
		}
		return messages;
	}
 }
