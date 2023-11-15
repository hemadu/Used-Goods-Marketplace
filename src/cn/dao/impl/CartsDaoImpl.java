package cn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.dao.BaseDao;
import cn.dao.CartsDao;
import cn.entity.Carts;

public class CartsDaoImpl extends BaseDao implements CartsDao{
	Connection conn=null;
	PreparedStatement pstm=null;
	ResultSet rs=null;
	public int save(Carts carts){
		String sql="insert into carts values(null,?,?,?,?,?,?)";
		Object[] params={carts.getType(),carts.getPrice(),carts.getUid(),carts.getSid(),carts.getPid(),carts.getQuantity()};
		return this.executeUpdate(sql, params);
	}
	public int update(Carts carts){
		String sql="update carts set type=?,price=?,uid=?,pid=?,quantity=? where id=?";
		Object[] params={carts.getType(),carts.getPrice(),carts.getUid(),carts.getPid(),carts.getQuantity(),carts.getId()};
		return this.executeUpdate(sql, params);
	}
	public int delete(Integer id){
		String sql="delete from carts where id=?";
		Object[] params={id};
		return this.executeUpdate(sql, params);
	}
	
	public int deleteByUid(Integer uid){
		String sql="delete from carts where uid=?";
		Object[] params={uid};
		return this.executeUpdate(sql, params);
	}

	public Carts getCarts(Integer id) {
	Carts carts = new Carts();
	conn=this.getConnection();
	String sql="select * from carts where id=?";
	
	try {
		pstm=conn.prepareStatement(sql);
		pstm.setInt(1, id);
		rs=pstm.executeQuery();
		if(rs.next()){
			carts.setType(rs.getString("type"));
			carts.setId(rs.getInt("id"));
			carts.setPrice(rs.getDouble("price"));
			carts.setUid(rs.getInt("uid"));
			carts.setSid(rs.getInt("sid"));
			carts.setPid(rs.getInt("pid"));
			carts.setQuantity(rs.getInt("quantity"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		this.closeAll(conn, pstm, rs);
	}
	return carts;
	}
	public List<Carts>getAll(String sql,Object[] params){
		List<Carts> cartsList=new ArrayList<Carts>();
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
				Carts carts = new Carts();
				carts.setType(rs.getString("type"));
				carts.setId(rs.getInt("id"));
				carts.setPrice(rs.getDouble("price"));
				carts.setUid(rs.getInt("uid"));
				carts.setSid(rs.getInt("sid"));
				carts.setPid(rs.getInt("pid"));
				carts.setQuantity(rs.getInt("quantity"));
				cartsList.add(carts);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstm, rs);
		}
		return cartsList;
	}


	public int getCount() {
		int count=0;
		conn=this.getConnection();
		String sql="select count(1) from carts";
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
	public List<Carts> getCartsByPage(int start, int pageSize) {
		
		List<Carts> cartsList=new ArrayList<Carts>();
		conn=this.getConnection();
		String sql="select * from carts limit ?,?";
		try {
			pstm=conn.prepareStatement(sql);
			pstm.setInt(1,start);
			pstm.setInt(2,pageSize);
			rs=pstm.executeQuery();
			while(rs.next()){
				Carts carts = new Carts();
				carts.setId(rs.getInt("id"));
				carts.setPrice(rs.getDouble("price"));
				carts.setUid(rs.getInt("uid"));
				carts.setSid(rs.getInt("sid"));
				carts.setPid(rs.getInt("pid"));
				carts.setQuantity(rs.getInt("quantity"));
				cartsList.add(carts);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstm, rs);
		}
		return cartsList;
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
	//public List<Carts>getNewsByPage(String sql,List<Object>params){
	public List<Carts> getCartsByPage(String sql, List<Object> params) {
		List<Carts>cartsList=new ArrayList<Carts>();
		conn=this.getConnection();
		try {
			pstm=conn.prepareStatement(sql);
			for(int i=0;i<params.size();i++){
				pstm.setObject(i+1,params.get(i));
			}
			rs=pstm.executeQuery();
			while(rs.next()){
				Carts carts = new Carts();
				carts.setId(rs.getInt("id"));
				carts.setPrice(rs.getDouble("price"));
				carts.setUid(rs.getInt("uid"));
				carts.setSid(rs.getInt("sid"));
				carts.setPid(rs.getInt("pid"));
				carts.setQuantity(rs.getInt("quantity"));
				cartsList.add(carts);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstm, rs);
		}
		return cartsList;
	}


}
