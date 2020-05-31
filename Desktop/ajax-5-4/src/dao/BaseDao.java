package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Dao数据操作的基类
 * 
 * @author
 */
public class BaseDao {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;

	static String dvr;
	static String url;
	static String usr;
	static String pwd;

	// static String dvr="com.mysql.jdbc.Driver";
	// static String url="jdbc:mysql://localhost:3306/myschool";
	// static String usr="root";
	// static String pwd="root";

	// static String dvr="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	// static String
	// url="jdbc:sqlserver://localhost:1433;databaseName=myschool";
	// static String usr="sa";
	// static String pwd="123";

	static {
		try {
			init();
			Class.forName(dvr);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 执行查询语句返回首行首列
	public Object getOne(String sql, Object... args) {
		try {
			conn = DriverManager.getConnection(url, usr, pwd);
			ps = conn.prepareStatement(sql);
			// 循环将参数设置到ps对象中
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getObject(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}

	protected List<Map<String, Object>> getMap(String sql, Object... args) {
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(url, usr, pwd);
			ps = conn.prepareStatement(sql);
			// 循环将参数设置到ps对象中
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			rs = ps.executeQuery();
			// 遍历结果集的每一行
			while (rs.next()) {
				// 将每一行的数据取出，组成一个键值对集合Map
				// 获取结果集的列数
				int cols = rs.getMetaData().getColumnCount();
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= cols; i++) {
					map.put(rs.getMetaData().getColumnLabel(i), rs.getObject(i));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;
	}

	/**
	 * 读取配置文件，初始化数据库配置项
	 * 
	 * @throws IOException
	 */
	private static void init() throws IOException {
		Properties p = new Properties();
		// 获取配置文件的输入流
		InputStream is = BaseDao.class.getClassLoader().getResourceAsStream(
				"db.properties");
		// 从输入流加载配置项
		p.load(is);
		// 获取配置项的值初始化数据库连接信息
		dvr = p.getProperty("driver");
		url = p.getProperty("url");
		usr = p.getProperty("user");
		pwd = p.getProperty("password");
	}

	protected int executeUpdate(String sql, Object... args) {
		int ret = 0;
		try {
			conn = DriverManager.getConnection(url, usr, pwd);
			ps = conn.prepareStatement(sql);
			// 循环将参数设置到ps对象中
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			ret = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return ret;
	}

	protected void closeAll() {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (ps != null)
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
