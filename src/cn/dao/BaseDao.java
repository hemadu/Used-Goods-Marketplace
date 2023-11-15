package cn.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 工具类,父类
 */

public class BaseDao {
	private static String DRIVER="com.mysql.jdbc.Driver";
	private static String URL="jdbc:mysql://localhost:3306/db_trade?characterEncoding=utf8";
	private static String USERNAME="root";
	private static String PASSWORD="root";
	static DataSource ds;
	/*static{
		try {
			Context ctx=new InitialContext();
			ds=(DataSource)ctx.lookup("java:comp/env/jdbc/trade");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	//静态块
//	static{
//		Properties pros=new Properties();
//		try {
//			//加载属性文件 
//			pros.load(new FileInputStream("bin/db.properties"));
//			//InputStream is=BaseDao.class.getResourceAsStream("db.properties");
//			//pros.load(is);
//			DRIVER=pros.getProperty("driver");
//			URL=pros.getProperty("url");
//			USERNAME=pros.getProperty("username");
//			PASSWORD=pros.getProperty("password");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	// 获取连接

	public Connection getConnection() {
		Connection conn = null;
//		try {
//			if(ds!=null){
//				conn=ds.getConnection();
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
		try {
			if(ds!=null){
				conn=ds.getConnection();
			}
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 执行增删改
	public int executeUpdate(String sql, Object[] params) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = this.getConnection();
			pstm = conn.prepareStatement(sql);
			// 给参数赋值
			if (params != null && params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					pstm.setObject(i + 1, params[i]);
				}
			}
			count = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstm, null);
		}
		return count;
	}

	// 关闭所有
	public void closeAll(Connection conn, PreparedStatement pstm, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstm != null) {
				pstm.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
