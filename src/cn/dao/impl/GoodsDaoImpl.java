package cn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.dao.BaseDao;
import cn.dao.GoodsDao;
import cn.entity.Goods;
import cn.entity.Goods;
import cn.util.PageBean;

public class GoodsDaoImpl extends BaseDao implements GoodsDao{
	Connection conn=null;
	PreparedStatement pstm=null;
	ResultSet rs=null;
	public int save(Goods goods){
		String sql="insert into goods values(null,?,?,?,?,?,?,?,?,?)";
		Object[] params={goods.getSid(),goods.getCid(),goods.getName(),goods.getPrice(),goods.getQuantity(),goods.getPic(),goods.getContent(),goods.getType(),goods.getIsdel()};
		return this.executeUpdate(sql, params);
	}
	public int update(Goods goods){
		String sql="update goods set sid=?,cid=?,name=?,price=?,quantity=?,pic=?,type=?,content=? where id=?";
		Object[] params={goods.getSid(),goods.getCid(),goods.getName(),goods.getPrice(),goods.getQuantity(),goods.getPic(),goods.getType(),goods.getContent(),goods.getId()};
		return this.executeUpdate(sql, params);
	}
	public int delete(Integer id){
		String sql="update goods set isdel=1 where id=?";
		Object[] params={id};
		return this.executeUpdate(sql, params);
	}
	public Goods getGoods(Integer id){
	Goods goods = new Goods();
	conn=this.getConnection();
	String sql="select * from goods where id=? and isdel=0 ";
	
	try {
		pstm=conn.prepareStatement(sql);
		pstm.setInt(1, id);
		rs=pstm.executeQuery();
		if(rs.next()){
			goods.setId(rs.getInt("id"));
			goods.setSid(rs.getInt("sid"));
			goods.setCid(rs.getInt("cid"));
			goods.setName(rs.getString("name"));
			goods.setPrice(rs.getDouble("price"));
			goods.setQuantity(rs.getInt("quantity"));
			goods.setPic(rs.getString("pic"));
			goods.setContent(rs.getString("content"));
			goods.setType(rs.getInt("type"));
		
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		this.closeAll(conn, pstm, rs);
	}
	return goods;
	}
	public List<Goods>getAll(String sql,Object[] params){
		List<Goods> goodsList=new ArrayList<Goods>();
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
				Goods goods=new Goods();
				goods.setId(rs.getInt("id"));
				goods.setSid(rs.getInt("sid"));
				goods.setCid(rs.getInt("cid"));
				goods.setName(rs.getString("name"));
				goods.setPrice(rs.getDouble("price"));
				goods.setQuantity(rs.getInt("quantity"));
				goods.setPic(rs.getString("pic"));
				goods.setContent(rs.getString("content"));
				goods.setType(rs.getInt("type"));
				goodsList.add(goods);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstm, rs);
		}
		return goodsList;
	}


	public int getCount() {
		int count=0;
		conn=this.getConnection();
		String sql="select count(1) from goods where isdel=0  ";
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
	public List<Goods> getGoodsByPage(int start, int pageSize) {
		
		List<Goods>goodsList=new ArrayList<Goods>();
		conn=this.getConnection();
		String sql="select * from goods where isdel=0 limit ?,?";
		try {
			pstm=conn.prepareStatement(sql);
			pstm.setInt(1,start);
			pstm.setInt(2,pageSize);
			rs=pstm.executeQuery();
			while(rs.next()){
				Goods goods=new Goods();
				goods.setId(rs.getInt("id"));
				goods.setSid(rs.getInt("sid"));
				goods.setCid(rs.getInt("cid"));
				goods.setName(rs.getString("name"));
				goods.setPrice(rs.getDouble("price"));
				goods.setQuantity(rs.getInt("quantity"));
				goods.setPic(rs.getString("pic"));
				goods.setContent(rs.getString("content"));
				goods.setType(rs.getInt("type"));
				goodsList.add(goods);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstm, rs);
		}
		return goodsList;
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
	//public List<Goods>getNewsByPage(String sql,List<Object>params){
	public List<Goods> getGoodsByPage(String sql, List<Object> params) {
		List<Goods>goodsList=new ArrayList<Goods>();
		conn=this.getConnection();
		try {
			pstm=conn.prepareStatement(sql);
			for(int i=0;i<params.size();i++){
				pstm.setObject(i+1,params.get(i));
			}
			rs=pstm.executeQuery();
			while(rs.next()){
				Goods goods=new Goods();
				goods.setId(rs.getInt("id"));
				goods.setSid(rs.getInt("sid"));
				goods.setCid(rs.getInt("cid"));
				goods.setName(rs.getString("name"));
				goods.setPrice(rs.getDouble("price"));
				goods.setQuantity(rs.getInt("quantity"));
				goods.setPic(rs.getString("pic"));
				goods.setContent(rs.getString("content"));
				goods.setType(rs.getInt("type"));
				goodsList.add(goods);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstm, rs);
		}
		return goodsList;
	}
	@Override
	public int check(Integer id) {
			String sql="update goods set isdel=0 where id=?";
			Object[] params={id};
			return this.executeUpdate(sql, params);
	}
	@Override
	public int getMaxId() {
		int count=0;
		conn=this.getConnection();
		String sql="select max(id) from goods";
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

}
