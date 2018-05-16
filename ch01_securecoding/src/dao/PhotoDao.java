package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DbConnector;
import vo.PhotoVo;

public class PhotoDao {
	
	DbConnector connector = DbConnector.getInstance();
	
	public List<PhotoVo> selectList() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<PhotoVo> list = new ArrayList<>();
		try {
			conn = connector.getConnection();
			stmt = conn.prepareStatement("select * from photos order by idx desc");
			rs = stmt.executeQuery();
			while(rs.next()) {
				PhotoVo vo = new PhotoVo();
				vo.setIdx(rs.getInt("idx"));
				vo.setDescription(rs.getString("description"));
				vo.setUrl(rs.getString("url"));
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

	public void insert(PhotoVo vo) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = connector.getConnection();
			stmt = conn.prepareStatement("insert into photos values(seq_photos_idx.nextval,?,?)");
			stmt.setString(1, vo.getDescription());
			stmt.setString(2, vo.getUrl());
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
