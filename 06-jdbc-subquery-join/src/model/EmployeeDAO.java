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
	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException {
		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();
	}
	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException {
		// close는 역순으로 한다
		if (rs != null)
			rs.close();
		closeAll(pstmt, con);
	}
	// 총무랑 개발 나오면 됨
	public ArrayList<String> findJobKindList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			con = getConnection();
			String query = "select distinct job from jdbc_employee";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {	
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeAll(rs,pstmt,con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public ArrayList<EmployeeVO> TestFindEmployeeListByHighestSalaryAndJob(String job) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<EmployeeVO> list = new ArrayList<EmployeeVO>();
		StringBuilder sb = new StringBuilder();
		sb.append("select empno,name,job,salary ");
		sb.append("from jdbc_employee where job= ? and salary = " );
		sb.append("(select max(salary) from jdbc_employee where job= ? )");
		try {
			con = getConnection();
			pstmt =  con.prepareStatement(sb.toString());
			pstmt.setString(1, job);
			pstmt.setString(2, job);
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
				closeAll(rs,pstmt,con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return  list;
	}
}
