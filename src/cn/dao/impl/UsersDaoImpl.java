package cn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.dao.BaseDao;
import cn.dao.ReplyDao;
import cn.dao.UsersDao;
import cn.entity.Users;
import cn.entity.Users;
import cn.entity.Reply;



public class UsersDaoImpl  extends BaseDao implements UsersDao{
	Connection conn=null;
	PreparedStatement pstm=null;
	ResultSet rs=null;
	public int save(Users users){
		String sql="insert into users values(null,?,0,?,?,?,?,?,?,?,2)";
		Object[] params={users.getPic(),users.getPhone(),users.getPassword(),users.getName(),users.getSex(),users.getGrade(),users.getSchool(),users.getMajor()};
		return this.executeUpdate(sql, params);
	}
	public int update(Users users){
		String sql="update users set pic=?,role=?,phone=?,password=?,name=?,sex=?,grade=?,school=?,major=? where id=?";
		Object[] params={users.getPic(),users.getRole(),users.getPhone(),users.getPassword(),users.getName(),users.getSex(),users.getGrade(),users.getSchool(),users.getMajor(),users.getId()};

		return this.executeUpdate(sql, params);
	}
	public int delete(Integer id){
		String sql="update users set isdel=1 where id=?";
		Object[] params={id};
		return this.executeUpdate(sql, params);
	}
	public Users getUsers(Integer id){
		 Users users=null;
		conn=this.getConnection();
		String sql="select * from users where id=? and isdel=0 ";
		try {
			pstm=conn.prepareStatement(sql);
			pstm.setObject(1, id);
			rs=pstm.executeQuery();
			if(rs.next()){
				users=new Users();
				users.setId(rs.getInt("id"));
				users.setPic(rs.getString("pic"));
				users.setRole(rs.getInt("role"));
				users.setPhone(rs.getString("phone"));
				users.setPassword(rs.getString("password"));
				users.setName(rs.getString("name"));
				users.setSex(rs.getString("sex"));
				users.setGrade(rs.getString("grade"));
				users.setSchool(rs.getString("school"));
				users.setMajor(rs.getString("major"));
				users.setIsdel(rs.getInt("isdel"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstm, rs);
		}
		return users;
	}
	public List<Users>getAll(String sql,Object[] params){
		List<Users>usersList=new ArrayList<Users>();
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
				Users users=new Users();
				users.setId(rs.getInt("id"));
				users.setPic(rs.getString("pic"));
				users.setRole(rs.getInt("role"));
				users.setPhone(rs.getString("phone"));
				users.setPassword(rs.getString("password"));
				users.setName(rs.getString("name"));
				users.setSex(rs.getString("sex"));
				users.setGrade(rs.getString("grade"));
				users.setSchool(rs.getString("school"));
				users.setMajor(rs.getString("major"));
				users.setIsdel(rs.getInt("isdel"));
				usersList.add(users);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstm, rs);
		}
		return usersList;
	}
	
	public int getCount() {
		int count=0;
		conn=this.getConnection();
		String sql="select count(1) from users where isdel=0  ";
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
	public List<Users> getUsersByPage(int start, int pageSize) {
		
		List<Users>usersList=new ArrayList<Users>();
		conn=this.getConnection();
		String sql="select * from users  where isdel=0  limit ?,?";
		try {
			pstm=conn.prepareStatement(sql);
			pstm.setInt(1,start);
			pstm.setInt(2,pageSize);
			rs=pstm.executeQuery();
			while(rs.next()){
				Users users=new Users();
				users.setId(rs.getInt("id"));
				users.setPic(rs.getString("pic"));
				users.setRole(rs.getInt("role"));
				users.setPhone(rs.getString("phone"));
				users.setPassword(rs.getString("password"));
				users.setName(rs.getString("name"));
				users.setSex(rs.getString("sex"));
				users.setGrade(rs.getString("grade"));
				users.setSchool(rs.getString("school"));
				users.setMajor(rs.getString("major"));
				users.setIsdel(rs.getInt("isdel"));
				usersList.add(users);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstm, rs);
		}
		return usersList;
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
	public List<Users> getUsersByPage(String sql, List<Object> params) {
		List<Users>usersList=new ArrayList<Users>();
		conn=this.getConnection();
		try {
			pstm=conn.prepareStatement(sql);
			for(int i=0;i<params.size();i++){
				pstm.setObject(i+1,params.get(i));
			}
			rs=pstm.executeQuery();
			while(rs.next()){
				Users users=new Users();
				users.setId(rs.getInt("id"));
				users.setPic(rs.getString("pic"));
				users.setRole(rs.getInt("role"));
				users.setPhone(rs.getString("phone"));
				users.setPassword(rs.getString("password"));
				users.setName(rs.getString("name"));
				users.setSex(rs.getString("sex"));
				users.setGrade(rs.getString("grade"));
				users.setSchool(rs.getString("school"));
				users.setMajor(rs.getString("major"));
				users.setIsdel(rs.getInt("isdel"));
				usersList.add(users);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstm, rs);
		}
		return usersList;
	}
	@Override
	public int check(Integer id) {
		String sql="update users set isdel=0 where id=?";
		Object[] params={id};

		return this.executeUpdate(sql, params);
	}
 }
