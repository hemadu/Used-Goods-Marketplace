package cn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.dao.BaseDao;
import cn.dao.OrdertailDao;
import cn.entity.Orders;
import cn.entity.Ordertail;

public class OrdertailDaoImpl extends BaseDao implements OrdertailDao{
	Connection conn=null;
	PreparedStatement pstm=null;
	ResultSet rs=null;
	public int save(Ordertail ordertail){
		String sql="insert into ordertail values(null,?,?,?)";
		Object[] params={ordertail.getOid(),ordertail.getPid(),ordertail.getQuantity()};
		return this.executeUpdate(sql, params);
	}
	public int update(Ordertail ordertail){
		String sql="update ordertail set oid=?,pid=?,quantity=? where id=?";
		Object[] params={ordertail.getOid(),ordertail.getPid(),ordertail.getQuantity(),ordertail.getId()};
		return this.executeUpdate(sql, params);
	}
	public int delete(Integer id){
		String sql="delete from ordertail where id=?";
		Object[] params={id};
		return this.executeUpdate(sql, params);
	}
	public Ordertail getOrdertail(Integer id){
	Ordertail ordertail = new Ordertail();
	conn=this.getConnection();
	String sql="select * from ordertail where id=?";
	
	try {
		pstm=conn.prepareStatement(sql);
		pstm.setInt(1, id);
		rs=pstm.executeQuery();
		if(rs.next()){
			ordertail.setId(rs.getInt("id"));
			ordertail.setOid(rs.getInt("oid"));
			ordertail.setPid(rs.getInt("pid"));
			ordertail.setQuantity(rs.getInt("quantity"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		this.closeAll(conn, pstm, rs);
	}
	return ordertail;
	}
	public List<Ordertail>getAll(String sql,Object[] params){
		List<Ordertail> ordertailList=new ArrayList<Ordertail>();
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
				Ordertail ordertail=new Ordertail();
				ordertail.setId(rs.getInt("id"));
				ordertail.setOid(rs.getInt("oid"));
				ordertail.setPid(rs.getInt("pid"));
				ordertail.setQuantity(rs.getInt("quantity"));
				ordertailList.add(ordertail);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstm, rs);
		}
		return ordertailList;
	}


	public int getCount() {
		int count=0;
		conn=this.getConnection();
		String sql="select count(1) from ordertail";
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
	public List<Ordertail> getOrdertailByPage(int start, int pageSize) {
		
		List<Ordertail>ordertailList=new ArrayList<Ordertail>();
		conn=this.getConnection();
		String sql="select count(1) from ordertail limit ?,?";
		try {
			pstm=conn.prepareStatement(sql);
			pstm.setInt(1,start);
			pstm.setInt(2,pageSize);
			rs=pstm.executeQuery();
			while(rs.next()){
				Ordertail ordertail=new Ordertail();
				ordertail.setId(rs.getInt("id"));
				ordertail.setOid(rs.getInt("oid"));
				ordertail.setPid(rs.getInt("pid"));
				ordertail.setQuantity(rs.getInt("quantity"));
				ordertailList.add(ordertail);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstm, rs);
		}
		return ordertailList;
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
	//public List<Ordertail>getNewsByPage(String sql,List<Object>params){
	public List<Ordertail> getOrdertailByPage(String sql, List<Object> params) {
		List<Ordertail>ordertailList=new ArrayList<Ordertail>();
		conn=this.getConnection();
		try {
			pstm=conn.prepareStatement(sql);
			for(int i=0;i<params.size();i++){
				pstm.setObject(i+1,params.get(i));
			}
			rs=pstm.executeQuery();
			while(rs.next()){
				Ordertail ordertail=new Ordertail();
				ordertail.setId(rs.getInt("id"));
				ordertail.setOid(rs.getInt("oid"));
				ordertail.setPid(rs.getInt("pid"));
				ordertail.setQuantity(rs.getInt("quantity"));
				ordertailList.add(ordertail);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstm, rs);
		}
		return ordertailList;
	}

}

