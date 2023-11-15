package cn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.dao.BaseDao;
import cn.dao.ReplyDao;
import cn.entity.Reply;
import cn.entity.Reply;



public class ReplyDaoImpl extends BaseDao implements ReplyDao{
	Connection conn=null;
	PreparedStatement pstm=null;
	ResultSet rs=null;
	public int save(Reply reply){
		String sql="insert into reply values(null,?,?,?,?)";
		Object[] params={reply.getMid(),reply.getUid(),reply.getContent(),reply.getOptime()};
		return this.executeUpdate(sql, params);
	}
	public int update(Reply reply){
		String sql="update reply set mid=?,name=?,content=?,optime=? where id=?";
		Object[] params={reply.getMid(),reply.getUid(),reply.getContent(),reply.getOptime(),reply.getId()};
		return this.executeUpdate(sql, params);
	}
	public int delete(Integer id){
		String sql="delete from reply where id=?";
		Object[] params={id};
		return this.executeUpdate(sql, params);
	}
	public int deletemg(Integer mid,Integer uid){
		String sql="delete from reply where mid=? and uid=?";
		Object[] params={mid,uid};
		return this.executeUpdate(sql, params);
	}
	public Reply getReply(Integer id){
		Reply reply=null;
		conn=this.getConnection();
		String sql="select * from reply where id=?";
		try {
			pstm=conn.prepareStatement(sql);
			pstm.setObject(1, id);
			rs=pstm.executeQuery();
			if(rs.next()){
				reply=new Reply();
				reply.setId(rs.getInt("id"));
				reply.setContent(rs.getString("content"));
				reply.setOptime(rs.getString("optime"));
				reply.setMid(rs.getInt("mid"));
				reply.setUid(rs.getInt("uid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstm, rs);
		}
		return reply;
	}
	public List<Reply>getAll(String sql,Object[] params){
		List<Reply>replys=new ArrayList<Reply>();
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
				Reply reply=new Reply();
				reply=new Reply();
				reply=new Reply();
				reply.setId(rs.getInt("id"));
				reply.setContent(rs.getString("content"));
				reply.setOptime(rs.getString("optime"));
				reply.setMid(rs.getInt("mid"));
				reply.setUid(rs.getInt("uid"));
				replys.add(reply);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstm, rs);
		}
		return replys;
	}
	public int getCount() {
		int count=0;
		conn=this.getConnection();
		String sql="select count(1) from reply ";
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
	public List<Reply> getReplyByPage(int start, int pageSize) {
		
		List<Reply>replys=new ArrayList<Reply>();
		conn=this.getConnection();
		String sql="select * from reply where limit ?,?";
		try {
			pstm=conn.prepareStatement(sql);
			pstm.setInt(1,start);
			pstm.setInt(2,pageSize);
			rs=pstm.executeQuery();
			while(rs.next()){
				Reply reply=new Reply();
				reply=new Reply();
				reply.setId(rs.getInt("id"));
				reply.setUid(rs.getInt("uid"));
				reply.setMid(rs.getInt("mid"));
				reply.setContent(rs.getString("content"));
				reply.setOptime(rs.getString("optime"));
				replys.add(reply);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstm, rs);
		}
		return replys;
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
	public List<Reply> getReplyByPage(String sql, List<Object> params) {
		List<Reply>replys=new ArrayList<Reply>();
		conn=this.getConnection();
		try {
			pstm=conn.prepareStatement(sql);
			for(int i=0;i<params.size();i++){
				pstm.setObject(i+1,params.get(i));
			}
			rs=pstm.executeQuery();
			while(rs.next()){
				Reply reply=new Reply();
				reply=new Reply();
				reply.setId(rs.getInt("id"));
				reply.setUid(rs.getInt("uid"));
				reply.setMid(rs.getInt("mid"));
				reply.setContent(rs.getString("content"));
				reply.setOptime(rs.getString("optime"));
				replys.add(reply);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstm, rs);
		}
		return replys;
	}
 }
