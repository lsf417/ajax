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
 * Dao���ݲ����Ļ���
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

	// ִ�в�ѯ��䷵����������
	public Object getOne(String sql, Object... args) {
		try {
			conn = DriverManager.getConnection(url, usr, pwd);
			ps = conn.prepareStatement(sql);
			// ѭ�����������õ�ps������
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
			// ѭ�����������õ�ps������
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			rs = ps.executeQuery();
			// �����������ÿһ��
			while (rs.next()) {
				// ��ÿһ�е�����ȡ�������һ����ֵ�Լ���Map
				// ��ȡ�����������
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
	 * ��ȡ�����ļ�����ʼ�����ݿ�������
	 * 
	 * @throws IOException
	 */
	private static void init() throws IOException {
		Properties p = new Properties();
		// ��ȡ�����ļ���������
		InputStream is = BaseDao.class.getClassLoader().getResourceAsStream(
				"db.properties");
		// ������������������
		p.load(is);
		// ��ȡ�������ֵ��ʼ�����ݿ�������Ϣ
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
			// ѭ�����������õ�ps������
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
