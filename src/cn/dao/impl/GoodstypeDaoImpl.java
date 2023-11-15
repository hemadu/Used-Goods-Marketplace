package cn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.dao.BaseDao;
import cn.dao.GoodstypeDao;
import cn.entity.Goodstype;

public class GoodstypeDaoImpl extends BaseDao implements GoodstypeDao{
	Connection conn=null;
	PreparedStatement pstm=null;
	ResultSet rs=null;
public int save(Goodstype goodstype){
	String sql="insert into goodstype values(null,?,0)";
	Object[] params={goodstype.getName()};
	return this.executeUpdate(sql, params);
}
public int update(Goodstype goodstype){
	String sql="update goodstype set name=? where id=?";
	Object[] params={goodstype.getName(),goodstype.getId()};
	return this.executeUpdate(sql, params);
}
public int delete(Integer id){
	String sql="update goodstype set isdel=1 where id=?";
	Object[] params={id};
	return this.executeUpdate(sql, params);
}
public Goodstype getGoodstype(Integer id){
	Goodstype goodstype=new Goodstype();
	conn=this.getConnection();
	String sql="select * from goodstype where id=? and isdel<>1";
	try {
		pstm=conn.prepareStatement(sql);
		pstm.setInt(1, id);
		rs=pstm.executeQuery();
		if(rs.next()){
			goodstype.setId(rs.getInt("id"));
			goodstype.setName(rs.getString("name"));
			goodstype.setIsdel(rs.getInt("isdel"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		this.closeAll(conn, pstm, rs);
	}
	return goodstype;
}

public List<Goodstype> getAll(String sql,Object[] params){
	List<Goodstype> goodstypeList=new ArrayList<Goodstype>();
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
			Goodstype goodstype=new Goodstype();
			goodstype.setId(rs.getInt("id"));
			goodstype.setName(rs.getString("name"));
			goodstype.setIsdel(rs.getInt("isdel"));
			goodstypeList.add(goodstype);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		this.closeAll(conn, pstm, rs);
	}
	return goodstypeList;
}


public int getCount() {
	int count=0;
	conn=this.getConnection();
	String sql="select count(1) from goodstype where isdel<>1 ";
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
public List<Goodstype> getGoodstypeByPage(int start, int pageSize) {
	
	List<Goodstype>goodstypeList=new ArrayList<Goodstype>();
	conn=this.getConnection();
	String sql="select * from goodstype where isdel<>1 limit ?,?";
	try {
		pstm=conn.prepareStatement(sql);
		pstm.setInt(1,start);
		pstm.setInt(2,pageSize);
		rs=pstm.executeQuery();
		while(rs.next()){
			Goodstype goodstype=new Goodstype();
			goodstype.setId(rs.getInt("id"));
			goodstype.setName(rs.getString("name"));
			goodstype.setIsdel(rs.getInt("isdel"));
			goodstypeList.add(goodstype);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		this.closeAll(conn, pstm, rs);
	}
	return goodstypeList;
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
public List<Goodstype> getGoodstypeByPage(String sql, List<Object> params) {
	List<Goodstype>goodstypeList=new ArrayList<Goodstype>();
	conn=this.getConnection();
	try {
		pstm=conn.prepareStatement(sql);
		for(int i=0;i<params.size();i++){
			pstm.setObject(i+1,params.get(i));
		}
		rs=pstm.executeQuery();
		while(rs.next()){
			Goodstype goodstype=new Goodstype();
			goodstype.setId(rs.getInt("id"));
			goodstype.setName(rs.getString("name"));
			goodstype.setIsdel(rs.getInt("isdel"));
			goodstypeList.add(goodstype);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		this.closeAll(conn, pstm, rs);
	}
	return goodstypeList;
}



}
