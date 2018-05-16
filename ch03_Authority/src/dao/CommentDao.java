package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DbConnector;
import vo.CommentVo;

public class CommentDao {
	
	DbConnector connector = DbConnector.getInstance();
	
	public List<CommentVo> selectList() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<CommentVo> list = new ArrayList<>();
		try {
			conn = connector.getConnection();
			stmt = conn.prepareStatement("select * from comments order by idx desc");
			rs = stmt.executeQuery();
			while(rs.next()) {
				CommentVo vo = new CommentVo();
				vo.setIdx(rs.getInt("idx"));
				vo.setU_id(rs.getString("u_id"));
				vo.setContent(rs.getString("content"));
				list.add(vo);
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
		return list;
	}

	public void insert(CommentVo vo) {
		System.out.println(vo.getU_id());
		System.out.println(vo.getContent());
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = connector.getConnection();
			stmt = conn.prepareStatement("insert into comments values(seq_comments_idx.nextval, ?, ?)");
			stmt.setString(1, vo.getU_id());
			stmt.setString(2, vo.getContent());
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
