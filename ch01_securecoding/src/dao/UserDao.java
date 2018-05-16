package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.DbConnector;
import vo.UserVo;

public class UserDao {
	DbConnector connector = DbConnector.getInstance();
	public void insert(UserVo vo) {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = connector.getConnection();
			stmt = conn.createStatement();
			stmt.executeQuery(
				String.format(
						"insert into users values('%s','%s','%s')", 
						vo.getId(), vo.getPassword(), vo.getName()));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public UserVo selectOne(String id, String password) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		UserVo vo = null;
		try {
			conn = connector.getConnection();
			stmt = conn.createStatement();
			String sql = String.format(
							"select * from users where id = '%s' and password = '%s'",
						     id, password);
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				vo = new UserVo();
				vo.setId(rs.getString("id"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vo;
	}
	
}
