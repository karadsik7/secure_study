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
			stmt = conn.prepareStatement("insert into users values(?, ?, ?, 0, null)");
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
	
	public int getLoginCount(String id) {
		
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = connector.getConnection();
			stmt = conn.prepareStatement("select login_count from users where id = ?");
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("login_count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public void plusLoginCount(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = connector.getConnection();
			stmt = conn.prepareStatement("update users set login_count = nvl(login_count, 0) + 1 where id = ?");
			stmt.setString(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public void setLoginFailCount(String id, int customCount) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = connector.getConnection();
			stmt = conn.prepareStatement("update users set login_count = ? where id = ?");
			stmt.setInt(1, customCount);
			stmt.setString(2, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setLoginFailTime(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = connector.getConnection();
			stmt = conn.prepareStatement("update users set login_fail_time = systimestamp where id = ?");
			stmt.setString(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isLockRealeased(String id) {
		
		boolean isRealeased = true;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = connector.getConnection();
			stmt = conn.prepareStatement("select id from users where id = ? and systimestamp > (login_fail_time + numtodsinterval(5, 'minute'))");
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				isRealeased = true;
			}else {
				isRealeased = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isRealeased;
	}
	
	public void update(UserVo uvo) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = connector.getConnection();
			stmt = conn.prepareStatement("update users set password = ?, name = ? where id = ?");
			stmt.setString(1, uvo.getPassword());
			stmt.setString(2, uvo.getName());
			stmt.setString(3, uvo.getId());
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
	
}
