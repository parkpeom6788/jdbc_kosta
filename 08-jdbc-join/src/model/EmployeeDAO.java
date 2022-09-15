package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.DbConfig;
// 사원의 기준에 맞추어저 있어서 EmployeeDAO
// Data Access Object
public class EmployeeDAO {
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DbConfig.URL, DbConfig.USER, DbConfig.PASS);
	}
	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException {
		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();
	}
	// select e.empno,e.ename,e.sal,d.dname,d.deptno,d.dname,d.loc,d.tel
	// from k_employee e
	// inner join k_department d
	// on e.deptno = d.deptno
	// where e.empno = ? ;
	public EmployeeVO findEmployeeByNo(int empNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmployeeVO vo = null;

		try {
			con = getConnection();
			StringBuilder sb = new StringBuilder("select e.empno,e.ename,e.sal,e.job,d.deptno,d.dname,d.loc,d.tel ");
			sb.append("from k_employee e  ");
			sb.append("inner join k_department d  ");
			sb.append("on e.deptno = d.deptno  ");
			sb.append("where e.empno = ? ");
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, empNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new EmployeeVO(rs.getInt(1), rs.getString(2), rs.getLong(3), rs.getString(4),
						new DepartmentVO(rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, con);
		}
		return vo;
	}

	// 규빈이가 푼거
	public EmployeeVO findEmployeeByNo2(int empNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmployeeVO vo = null;
		DepartmentVO vo1 = null;
		try {
			con = getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT e.empNo,e.ename,e.sal,e.job,d.deptNo,d.dname,d.tel,d.loc ");
			sql.append("FROM k_department d,k_employee e ");
			sql.append("WHERE e.empno= ? ");
			sql.append("AND d.deptno=e.deptno");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, empNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// 규빈이네가 푼거
				//vo1 = new DepartmentVO(rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8));
				//vo = new EmployeeVO(rs.getInt(1), rs.getString(2), rs.getLong(3), rs.getString(4), vo1);
				
				// 강사님이 푼거
				DepartmentVO departmentVO = new DepartmentVO();
				departmentVO.setDeptNo(rs.getInt("deptno"));
				departmentVO.setDname(rs.getString("dname"));
				departmentVO.setLoc(rs.getString("loc"));
				departmentVO.setTel(rs.getString("tel"));
				
				vo = new EmployeeVO();
				vo.setEmpNo(rs.getInt("empno"));
				vo.setEname(rs.getString("ename"));
				vo.setSal(rs.getLong("sal"));
				vo.setJob(rs.getString("job"));
				vo.setDepartmentVO(departmentVO);
				}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return vo;
	}
	// 내가 다시 풀어보기 
	//	select e.empno , e.ename , e.sal ,  e.job , e.deptno , d.dname , d.loc , d.tel
	//	from k_employee e
	//	inner join k_department d
	//	on e.deptno = d.deptno
	//	where e.deptno = 10;
	public EmployeeVO findEmployeeByNo3(int empNo) throws SQLException  {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmployeeVO employee = null;
		DepartmentVO department = null;
		try {
			con = getConnection();
			StringBuilder sb = new StringBuilder("select e.empno , e.ename , e.sal ,  e.job , e.deptno , d.dname , d.loc , d.tel ");
			sb.append("from k_employee e ");
			sb.append("inner join k_department d ");
			sb.append("on e.deptno = d.deptno ");
			sb.append("where e.deptno = ? ");
			
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, empNo);
			rs = pstmt.executeQuery();
			
			// empno는 중복될일이 없으므로 
			if(rs.next()) {
				department = new DepartmentVO(
						rs.getInt(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8)
						);	
				employee = new EmployeeVO(
						rs.getInt(1),
						rs.getString(2),
						rs.getLong(3),
						rs.getString(4),
						department
						);
				}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return employee;
	}
}
