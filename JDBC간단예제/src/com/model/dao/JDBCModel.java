package com.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCModel {
	// Statement 객체 사용
	// 1. Emp 테이블 전체 회원정보 조회
	
	public void empSelectAll() {
		
		// 객체 사용후 close 하기 위해 지역변수로 선언
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		// 오라클 드라이버를 사용하겠다는 의미
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			stmt = con.createStatement();
			// Emp 테이블 전체조회 하기 위한 쿼리문
			String query = "select * from emp";
			// select 문이기 때문에 executeQuery 으로 사용
			rs =  stmt.executeQuery(query);
			
			// 값 뽑아내기 위한 반복문 
			while(rs.next()) {
				int empNo = rs.getInt("empno");
				String empName = rs.getString("ename");
				String job = rs.getString("job");
				int mgr = rs.getInt("mgr");
				Date hireDate = rs.getDate("hiredate"); // date 는 sql로 import 
				int sal = rs.getInt("sal");
				int comm = rs.getInt("comm");
				int deptNo = rs.getInt("deptno");
				// 출력 
				System.out.println(empNo + "  ,  " + empName + ", " + job + " , "
						+ mgr + " , " + hireDate + " , " + sal + " , " + comm + " , " + deptNo);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 항상 사용후 무조건 닫아주자!
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// Statement 객체 사용
	// 2. EMP 테이블 회원 추가
	public void empMemberAdd(Employee emp) {
		Connection con = null;
		Statement stmt = null;
		int result = 0;
		// 오라클에서 만든 jdbc 드라이버는 오라클 드라이버
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "scott", "tiger");
			stmt = con.createStatement();
			// 매개변수로 들어온 emp 참조변수를 이용하여 회원정보 추가
			// 쿼리의 문자열 계열을 '' 안에 작성해야함 
			String query = "insert into emp " + "values("+emp.getEmpNo() + ", '" +
			emp.getEmpName() + "', '" + emp.getJob() + "'," + emp.getMgr() + ", " + "sysdate" + ", " + emp.getSal() + ", " + emp.getComm() + ", " + emp.getDeptNo() + ")";
			
			// executeUpdate 의 반환값은 0
			// 추가에 성공하면 1 이라는 값 반환 실패 시 0 이라는 값 반환
			// 그리고 DML 구문은 항상 COMMIT 작업을 해줘야 한다 
			result = stmt.executeUpdate(query);
			System.out.println(result + "개의 행을 추가하였습니다.");
			if(result > 0) { 
				// 1 성공했을시 
				con.commit();
			} else {
				// 0 실패했을시
				con.rollback();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void empSelectOne(int emp_No) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "scott", "tiger");
			String query = "select * from emp where empNo = ?";
			pstmt =  con.prepareStatement(query);
			pstmt.setInt(1, emp_No);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				Employee emp = new Employee(rs.getInt("empNo"),
						rs.getString("ename") ,  rs.getString("job") , rs.getInt("mgr")
						, rs.getDate("hiredate") , rs.getInt("sal") ,  rs.getInt("comm")
						, rs.getInt("deptno"));
				System.out.println(emp);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void empUpdate(Employee emp) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "scott", "tiger");
			String query = "update emp set  job = ? , sal = ? , comm = ? where empno=? ";
			
			pstmt =  con.prepareStatement(query);
			pstmt.setString(1, emp.getJob());
			pstmt.setInt(2, emp.getSal());
			pstmt.setInt(3, emp.getComm());
			pstmt.setInt(4, emp.getEmpNo());
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				con.commit();
			} else {
				con.rollback();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
	}
}
