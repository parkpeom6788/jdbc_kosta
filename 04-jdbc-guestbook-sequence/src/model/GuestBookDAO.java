package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DbConfig;
/*
 * DAO : Data Access Object , VO : Value Object
 */
public class GuestBookDAO {
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DbConfig.URL, DbConfig.USER ,DbConfig.PASS);
	}
	public void closeAll(PreparedStatement pstmt , Connection con) throws SQLException {
			if(pstmt!=null)
			pstmt.close();
			con.close();
	}
	public void closeAll(PreparedStatement pstmt , ResultSet rs , Connection con) throws SQLException {
		if(rs != null) // 역순으로 ResultSet 먼저 닫음 
				rs.close();
		closeAll(pstmt,con);
	}
	public void registerGuestBook(GuestBookVO guestBookVO) {
		// nextval 사용
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String query = "insert into guestbook (GUESTBOOK_NO,TITLE,CONTENT) values(guestbook_seq.nextval,?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,guestBookVO.getTitle());
			pstmt.setString(2, guestBookVO.getContent());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeAll(pstmt,con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public ArrayList<GuestBookVO> findAllGuestBookListOrderByNoDesc() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ArrayList<GuestBookVO> list = new ArrayList<>();
		ResultSet rs = null;
		try {
			con = getConnection();
			String query = "select GUESTBOOK_NO,title,content from guestbook order by GUESTBOOK_NO desc";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GuestBookVO vo = new GuestBookVO(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3)
						);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeAll(pstmt,rs,con);
			//	con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public ArrayList<GuestBookVO> findGuestBookListLikeKeyword(String keyword) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<GuestBookVO> list = new ArrayList<GuestBookVO>();
		
		try {
			con = getConnection();
			String query = "select guestbook_no,title,content from guestbook where title like '%'||?||'%'";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				GuestBookVO vo = new GuestBookVO(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3)
						);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeAll(pstmt,rs,con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}


