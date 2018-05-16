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
		PreparedStatement stmt = null;
		try {
			conn = connector.getConnection();
			stmt = conn.prepareStatement("insert into users values(?, ?, ?)");
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPassword());
			stmt.setString(3, vo.getName());
			stmt.executeUpdate();
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
		PreparedStatement stmt = null;
		ResultSet rs = null;
		UserVo vo = null;
		try {
			conn = connector.getConnection();
			stmt = conn.prepareStatement("select * from users where id = ? and password = ?");
			/*String sql = String.format(
							"select * from users where id = '%s' and password = '%s'",
						     id, password);*/
			stmt.setString(1, id);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
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
