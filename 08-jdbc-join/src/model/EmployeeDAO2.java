package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import common.DbConfig;
public class EmployeeDAO2 {
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DbConfig.URL, DbConfig.USER, DbConfig.PASS);
	}
	public void closeAll(PreparedStatement pstmt , Connection conn) throws SQLException {
		if(pstmt != null) 
			pstmt.close();
		if(conn != null)
			conn.close();
	}
	public void closeAll(ResultSet rs , PreparedStatement pstmt , Connection conn) throws SQLException {
		if(rs != null) {
			rs.close();
		}
		closeAll(pstmt,conn);
	}
	public EmployeeVO findEmployeeByNo3(int empNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmployeeVO vo2 = null;
		try {
			con = getConnection();
			StringBuilder sb = new StringBuilder("select e.empno,e.ename,e.sal,e.job,e.deptno,d.deptno,d.dname,d.loc,d.tel ");
			sb.append("from K_EMPLOYEE e ");
			sb.append("inner join k_department d ");
			sb.append("on e.deptno = d.deptno ");
			sb.append("where empno = ?");
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, empNo);
			rs =  pstmt.executeQuery();
			
			if(rs.next()) {
				DepartmentVO vo1 = new DepartmentVO(
						rs.getInt(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9)
						);
				vo2 = new EmployeeVO(
						rs.getInt(1),
						rs.getString(2),
						rs.getLong(3),
						rs.getString(4),
						vo1
					);
				}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return vo2;
	}
}
