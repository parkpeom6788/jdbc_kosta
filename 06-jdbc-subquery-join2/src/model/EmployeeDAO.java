package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import common.DbConfig;
public class EmployeeDAO {
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DbConfig.URL, DbConfig.USER, DbConfig.PASS);
	}
	public void closeAll(Connection con , PreparedStatement pstmt) throws SQLException {
		if(con!=null)
			con.close();
		if(pstmt!=null)
			pstmt.close();
	}
	public void closeAll(Connection con , PreparedStatement pstmt,ResultSet rs) throws SQLException {
		if(rs!=null)
			rs.close();
		closeAll(con,pstmt);
	}
	// job의 종류를 list로 
	public ArrayList<String> findJobKindList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			con =  getConnection();
			String query = "select distinct job from jdbc_employee";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String s = rs.getString(1);
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeAll(con, pstmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	// 총무 직업만 구하기
	public ArrayList<EmployeeVO> TestFindEmployeeListByHighestSalaryAndJob(String job) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<EmployeeVO> list = new ArrayList<EmployeeVO>();
		try {
			con = getConnection();
			String query = "select empno,name,job,salary from jdbc_employee where job = ? ";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, job);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmployeeVO vo = new EmployeeVO(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4)
						);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeAll(con, pstmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
