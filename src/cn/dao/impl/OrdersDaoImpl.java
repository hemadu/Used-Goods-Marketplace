package cn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.dao.BaseDao;
import cn.dao.OrdersDao;
import cn.entity.Orders;

public class OrdersDaoImpl extends BaseDao implements OrdersDao{
	Connection conn=null;
	PreparedStatement pstm=null;
	ResultSet rs=null;
	public int save(Orders orders){
		String sql="insert into orders values(null,?,?,?,?,?,?,0)";
		Object[] params={orders.getUid(),orders.getSid(),orders.getNo(),orders.getTotalprice(),orders.getOptime(),orders.getStatus()};
		return this.executeUpdate(sql, params);
	}
	public int update(Orders orders){
		String sql="update orders set sid=?,no=?,totalprice=?,optime=?,status=? where id=?";
		Object[] params={orders.getSid(),orders.getNo(),orders.getTotalprice(),orders.getOptime(),orders.getStatus(),orders.getId()};
		return this.executeUpdate(sql, params);
	}
	public int delete(Integer id){
		String sql="update orders set isdel=1 where id=?";
		Object[] params={id};
		return this.executeUpdate(sql, params);
	}
	public Orders getOrders(Integer id){
	Orders orders = new Orders();
	conn=this.getConnection();
	String sql="select * from orders where id=? and isdel<>1";
	
	try {
		pstm=conn.prepareStatement(sql);
		pstm.setInt(1, id);
		rs=pstm.executeQuery();
		if(rs.next()){
			orders.setId(rs.getInt("id"));
			orders.setUid(rs.getInt("uid"));
			orders.setSid(rs.getInt("sid"));
			orders.setNo(rs.getString("no"));
			orders.setTotalprice(rs.getDouble("totalprice"));
			orders.setOptime(rs.getString("optime"));
			orders.setStatus(rs.getString("status"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		this.closeAll(conn, pstm, rs);
	}
	return orders;
	}
	public List<Orders>getAll(String sql,Object[] params){
		List<Orders> ordersList=new ArrayList<Orders>();
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
				Orders orders=new Orders();
				orders.setId(rs.getInt("id"));
				orders.setUid(rs.getInt("uid"));
				orders.setSid(rs.getInt("sid"));
				orders.setNo(rs.getString("no"));
				orders.setTotalprice(rs.getDouble("totalprice"));
				orders.setOptime(rs.getString("optime"));
				orders.setStatus(rs.getString("status"));
				ordersList.add(orders);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstm, rs);
		}
		return ordersList;
	}


	public int getCount() {
		int count=0;
		conn=this.getConnection();
		String sql="select count(1) from orders where isdel<>1";
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
	public List<Orders> getOrdersByPage(int start, int pageSize) {
		
		List<Orders>ordersList=new ArrayList<Orders>();
		conn=this.getConnection();
		String sql="select * from orders where isdel<>1 limit ?,?";
		try {
			pstm=conn.prepareStatement(sql);
			pstm.setInt(1,start);
			pstm.setInt(2,pageSize);
			rs=pstm.executeQuery();
			while(rs.next()){
				Orders orders=new Orders();
				orders.setId(rs.getInt("id"));
				orders.setUid(rs.getInt("uid"));
				orders.setSid(rs.getInt("sid"));
				orders.setNo(rs.getString("no"));
				orders.setTotalprice(rs.getDouble("totalprice"));
				orders.setOptime(rs.getString("optime"));
				orders.setStatus(rs.getString("status"));
				ordersList.add(orders);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstm, rs);
		}
		return ordersList;
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
	//public List<Orders>getNewsByPage(String sql,List<Object>params){
	public List<Orders> getOrdersByPage(String sql, List<Object> params) {
		List<Orders>ordersList=new ArrayList<Orders>();
		conn=this.getConnection();
		try {
			pstm=conn.prepareStatement(sql);
			for(int i=0;i<params.size();i++){
				pstm.setObject(i+1,params.get(i));
			}
			rs=pstm.executeQuery();
			while(rs.next()){
				Orders orders=new Orders();
				orders.setId(rs.getInt("id"));
				orders.setUid(rs.getInt("uid"));
				orders.setSid(rs.getInt("sid"));
				orders.setNo(rs.getString("no"));
				orders.setTotalprice(rs.getDouble("totalprice"));
				orders.setOptime(rs.getString("optime"));
				orders.setStatus(rs.getString("status"));
				ordersList.add(orders);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstm, rs);
		}
		return ordersList;
	}
	@Override
	public int getMaxId() {
		int count=0;
		conn=this.getConnection();
		String sql="select max(id) from orders";
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
	public int updatePriceById(Integer oid, Double price) {
		String sql="update orders set totalprice=? where id=?";
		Object[] params={price,oid};
		return this.executeUpdate(sql, params);
	}


}

