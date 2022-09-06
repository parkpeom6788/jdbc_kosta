package step4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import common.DbConfig;
/*
  	id 가 spring 인 회원의 address 를 미금에서 오리로 수정하고자 한다 
 */
public class TestJdbcUpdate {
	public static void main(String[] args) {
		/*Oracle JDBC Driver loading
		 * Connection
		 * String sql
		 * PreparedStatement
		 */
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			Class.forName(DbConfig.DRIVER); // 클래스 로딩
			con = DriverManager.getConnection(DbConfig.URL, "scott", "tiger"); // 연결 객체 생성
			String sql = "update member set address = ? where id=?"; 
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, "하늘");
			pstmt.setString(2, "spring");
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("수정이 완료 되었습니다.");
			} else if(result == -1) {
				System.out.println("id가 없음");
			} 
			pstmt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}
